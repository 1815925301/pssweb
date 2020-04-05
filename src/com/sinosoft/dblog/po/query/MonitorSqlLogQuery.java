package com.sinosoft.dblog.po.query;

import java.sql.Timestamp;

import com.sinosoft.po.query.BasePaginationQuery;

/**
 * @Package com.sinosoft.dblog.po.query
 * @ClassName: MonitorSqlLogQuery
 * @Description: 系统SQL执行监控日志查询对象
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 下午04:21:17
 */
public class MonitorSqlLogQuery extends BasePaginationQuery{
	private Long id; // Pk

	private String innerSerialId; // 内部流水号

	private String sqlCommandType; // sql操作类型

	private String sqlStr; // sql语句

	private String parameters; // 参数

	private String mybatisSqlId; // 来源类方法

	private String fileResource; // sql来源mybatis文件

	private String dataSource; // 数据库

	private Integer timeCost; // 执行耗时

	private Timestamp sqlStartTime; // 开始时间

	private Timestamp sqlEndTime; // 结束时间

	private Timestamp createTime; // 创建记录的时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInnerSerialId() {
		return innerSerialId;
	}

	public void setInnerSerialId(String innerSerialId) {
		this.innerSerialId = innerSerialId;
	}

	public String getSqlCommandType() {
		return sqlCommandType;
	}

	public void setSqlCommandType(String sqlCommandType) {
		this.sqlCommandType = sqlCommandType;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getMybatisSqlId() {
		return mybatisSqlId;
	}

	public void setMybatisSqlId(String mybatisSqlId) {
		this.mybatisSqlId = mybatisSqlId;
	}

	public String getFileResource() {
		return fileResource;
	}

	public void setFileResource(String fileResource) {
		this.fileResource = fileResource;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Integer getTimeCost() {
		return timeCost;
	}

	public void setTimeCost(Integer timeCost) {
		this.timeCost = timeCost;
	}

	public Timestamp getSqlStartTime() {
		return sqlStartTime;
	}

	public void setSqlStartTime(Timestamp sqlStartTime) {
		this.sqlStartTime = sqlStartTime;
	}

	public Timestamp getSqlEndTime() {
		return sqlEndTime;
	}

	public void setSqlEndTime(Timestamp sqlEndTime) {
		this.sqlEndTime = sqlEndTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
