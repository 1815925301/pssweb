package com.sinosoft.common.mybatis.interceptor;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.sinosoft.common.constant.Constant;
import com.sinosoft.common.spring.SpringContextUtils;
import com.sinosoft.common.util.GenerateSequenceUtils;
import com.sinosoft.common.util.ObjectToString;
import com.sinosoft.common.util.ThreadContainer;
import com.sinosoft.dblog.DBLog;
import com.sinosoft.dblog.DBLogContext;
import com.sinosoft.dblog.po.MonitorSqlLog;

/**
 * @Package com.sinosoft.common.mybatis.interceptor
 * @ClassName: StaticsSqlInterceptor
 * @Description: 执行SQL语句时的拦截器 集成MyBatis拦截器
 * @author zzq
 * @Version V1.0
 * @date 2013-9-10 下午08:39:27
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "query", args = { Statement.class, ResultHandler.class }),
	@Signature(type = StatementHandler.class, method = "update", args = { Statement.class }) })
public class StaticsSqlInterceptor implements Interceptor {
	
	private static Pattern pattern = Pattern.compile("\\d+[^\\?]*");

    private Logger loggerSql = LoggerFactory.getLogger(StaticsSqlInterceptor.class);

    private Logger logger = LoggerFactory.getLogger(Interceptor.class);
    
    private DBLog dblog = null;
    
    private boolean isdblogInit = false;
    
    private ThreadLocal<String> serivalThreadLocal;

    private static Lock lock = new ReentrantLock();
    
    public void initDblog() {
        if (isdblogInit)
            return;
        lock.lock();
        try {
        	if (this.serivalThreadLocal == null)
        		this.serivalThreadLocal = ThreadContainer.getMyThreadLocal(Constant.INNER_SERIAL_THREAD_LOCAL);
        	if (this.serivalThreadLocal == null) {
        		this.serivalThreadLocal = new ThreadLocal<String>();
                ThreadContainer.addMyThreadLocal(Constant.INNER_SERIAL_THREAD_LOCAL, this.serivalThreadLocal);
        	}
            ApplicationContext ctx = SpringContextUtils.getCurrentContext();
            if (ctx != null && ctx.getBean("dblog") != null && dblog == null)
                dblog = (DBLog) ctx.getBean("dblog");
            if(dblog == null)
                dblog = DBLogContext.getDBLog();
            isdblogInit = true;
            if (dblog == null && isdblogInit)
                logger.error("DBLog未实例化!无法记录数据库日志信息!");
        } finally {
            lock.unlock();
        }
    }
    
    /* (non-Javadoc)
     * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
     * 实现拦截器
     */
    public Object intercept(Invocation invocation) throws Throwable {
        // 初始化log
        initDblog();

        long start = System.currentTimeMillis();
        Timestamp startTime = new Timestamp(start);
        
        //执行需要执行的SQL语句
        Object result = invocation.proceed();
        
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        Long costTime = System.currentTimeMillis() - start;
        
        // 得到执行的sql的ID,对于该部分的异常不做任何处理;
        String mybatisSqlId = null;
        MonitorSqlLog sqlLog = new MonitorSqlLog();
        String begin = "";
        if (this.serivalThreadLocal != null) {
        	if (this.serivalThreadLocal.get() == null) {
        		 this.serivalThreadLocal.set(GenerateSequenceUtils.generateSequenceNo());
        	} 
        	begin = Constant.INNER_SERIAL + "->" + this.serivalThreadLocal.get();
            sqlLog.setInnerSerialId(this.serivalThreadLocal.get());
        }
        try {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);
            // 得到sql
            String originalSql = removeBreakingWhitespace((String) metaStatementHandler.getValue("delegate.boundSql.sql"));
            mybatisSqlId = (String) metaStatementHandler.getValue("delegate.mappedStatement.id");
            // 得到执行的mybatis配置文件
            String fileResource = (String) metaStatementHandler.getValue("delegate.mappedStatement.resource");
            // 得到select update delete
            String sqlCommandType = metaStatementHandler.getValue("delegate.mappedStatement.sqlCommandType").toString().toLowerCase();
            
            String datasource = null;
            try {
                datasource = (String) metaStatementHandler.getValue("delegate.configuration.environment.dataSource.url");
                //得到参数
                String parameters = getParameterValueString(metaStatementHandler, "delegate.parameterHandler");
                sqlLog.setParameters(parameters);
            } catch (Exception e) {
                logger.error("拦截器中分析SQL数据源URL和SQL参数的操作出现异常：{} -- {}", e);
            }
            if (datasource != null) {
                datasource = getDataSource(datasource);
            }
            sqlLog.setSqlStr(originalSql);
            sqlLog.setFileResource(fileResource);
            sqlLog.setMybatisSqlId(mybatisSqlId);
            sqlLog.setDataSource(datasource);
            if (sqlCommandType != null)
                sqlLog.setSqlCommandType(sqlCommandType.toLowerCase());
        } catch (Exception e) {
        	logger.error(begin + " 拦截器中分析所执行SQL的操作出现异常：{} -- {}", e);
        }
        try {
            sqlLog.setTimeCost(costTime.intValue());
            sqlLog.setSqlStartTime(startTime);
            sqlLog.setSqlEndTime(endTime);
             if (dblog != null) {
            	 //如果是写入到monitor_sql_log、monitor_receiver_log表中的SQL语句，则过滤，不用记录执行情况日志
            	 if (!((sqlLog.getSqlStr().toLowerCase().contains("monitor_sql_log") 
            			 || sqlLog.getSqlStr().toLowerCase().contains("monitor_receiver_log"))
            			 && sqlLog.getSqlCommandType().toLowerCase().equals("insert"))) {
            		 dblog.monitorSqlLog(sqlLog);
            	 }
             }
            if (logger.isDebugEnabled()) {
            	logger.debug(begin + "本次sql执行SQL:=====>" + sqlLog.getSqlStr());
                logger.debug(begin + "本次sql执行parameters:=====>" + sqlLog.getParameters());
                logger.debug(begin + "本次sql执行mybatisId:=====>" + sqlLog.getMybatisSqlId());
                logger.debug(begin + "本次sql执行startTime:=====>" + sqlLog.getSqlStartTime());
                logger.debug(begin + "本次sql执行endTime:=====>" + sqlLog.getSqlEndTime());
                logger.debug(begin + "本次sql执行用时:=====>" + sqlLog.getTimeCost() + "ms");
                logger.debug(begin + "本次sql执行结果:=====>" + ObjectToString.convertToString(result));
            }
            loggerSql.info(begin + "本次sql执行信息:=====>" + sqlLog.toString());
        } catch (Exception ex) {
            logger.error(begin + " 拦截器中分析所执行SQL耗费时间与保存SQL执行情况的操作出现异常：{} -- {}", ex);
        }
        return result;
    }
    
    
    
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties arg0) {}
    
    /**
     * 去除字符串中的空格
     * @param original
     * @return String
     * @throws
     * @author zzq
     * @date 2013-9-14 下午09:42:15
     * @version V1.0
     */
    private static String removeBreakingWhitespace(String original) {
        StringTokenizer whitespaceStripper = new StringTokenizer(original);
        StringBuilder builder = new StringBuilder();
        while (whitespaceStripper.hasMoreTokens()) {
            builder.append(whitespaceStripper.nextToken());
            builder.append(" ");
        }
        return builder.toString();
    }
    
    /**
     * 得到jdbc连接中的数据库地址信息
     * @param data
     * @return String
     * @throws
     * @author zzq
     * @date 2013-9-14 下午09:47:02
     * @version V1.0
     */
    private static String getDataSource(String data) {
        if (data == null)
            return data;
        Matcher m = pattern.matcher(data.trim());
        if (m.find()) {
            return m.group();
        }
        return data;
    }
    
    /**
     * 得到所执行SQL中的参数信息 并格式化输出参数信息 
     * @param metaStatementHandler
     * @param begin
     * @return String
     * @throws
     * @author zzq
     * @date 2013-9-14 下午09:46:02
     * @version V1.0
     */
    private String getParameterValueString(MetaObject metaStatementHandler, String begin) throws SQLException {

        // 还原对象信息
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue(begin + ".boundSql");
        Object parameterObject = metaStatementHandler.getValue(begin + ".parameterObject");
        TypeHandlerRegistry typeHandlerRegistry = 
        	(TypeHandlerRegistry) metaStatementHandler.getValue(begin + ".typeHandlerRegistry");
        Configuration configuration = (Configuration) metaStatementHandler.getValue(begin + ".configuration");

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        if (parameterMappings == null)
            return "";
        // 取得元素信息
        List<String> typeList = new ArrayList<String>(parameterMappings.size());
        if (parameterMappings != null) {
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) 
                    		&& boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    if (value == null) {
                        typeList.add("null");
                    } else {
                        typeList.add(value + "(" + value.getClass().getSimpleName() + ")");
                    }
                }
            }
        }
        if (typeList.size() > 0) {
            final String parameters = typeList.toString();
            return parameters.substring(1, parameters.length() - 1);
        } else
            return "";
    }

}
