package com.sinosoft.dblog.po;

import java.sql.Timestamp;

/**
 * @Package com.sinosoft.dblog.po
 * @ClassName: MonitorSqlLog
 * @Description: 监控日志实体类
 * @author zzq
 * @Version V1.0
 * @Time 2013-9-14 下午09:19:07
 */
public class MonitorSqlLog {

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
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSqlStr() {
		return this.sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getParameters() {
		return this.parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getFileResource() {
		return this.fileResource;
	}

	public void setFileResource(String fileResource) {
		this.fileResource = fileResource;
	}

	public String getMybatisSqlId() {
		return this.mybatisSqlId;
	}

	public void setMybatisSqlId(String mybatisSqlId) {
		this.mybatisSqlId = mybatisSqlId;
	}

	public String getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getInnerSerialId() {
		return this.innerSerialId;
	}

	public void setInnerSerialId(String innerSerialId) {
		this.innerSerialId = innerSerialId;
	}

	public String getSqlCommandType() {
		return this.sqlCommandType;
	}

	public void setSqlCommandType(String sqlCommandType) {
		this.sqlCommandType = sqlCommandType;
	}

	public Integer getTimeCost() {
		return this.timeCost;
	}

	public void setTimeCost(Integer timeCost) {
		this.timeCost = timeCost;
	}

	public Timestamp getSqlStartTime() {
		return this.sqlStartTime;
	}

	public void setSqlStartTime(Timestamp sqlStartTime) {
		this.sqlStartTime = sqlStartTime;
	}

	public Timestamp getSqlEndTime() {
		return this.sqlEndTime;
	}

	public void setSqlEndTime(Timestamp sqlEndTime) {
		this.sqlEndTime = sqlEndTime;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("MonitorSqlLog [sqlStr=" + sqlStr + ", parameters="
				+ parameters);
		sb.append(", fileResource=" + fileResource + ", mybatisSqlId=");
		sb.append(mybatisSqlId + ", DataSource=" + dataSource);
		sb.append(", innerSerialId=" + innerSerialId + ", sqlCommandType=");
		sb.append(sqlCommandType + ", TimeCost=" + timeCost
				+ "ms, sqlStartTime=");
		sb.append(sqlStartTime + ", sqlEndTime=" + sqlEndTime + "]");
		return sb.toString();
	}

}
