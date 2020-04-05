package com.sinosoft.dblog.po.query;

import java.sql.Timestamp;

import com.sinosoft.po.query.BasePaginationQuery;

/**
 * @Package com.sinosoft.dblog.po.query
 * @ClassName: MonitorReceiverLogQuery
 * @Description: 系统服务访问监控日志查询对象
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 下午04:21:21
 */
public class MonitorReceiverLogQuery extends BasePaginationQuery{
	private Long id; // Pk

	private String innerSerialId; // 内部流水号

	private String serviceName; // 接口名称

	private Long userId; // 用户ID
	
	private String userName; //用户登录名

	private Long orgId; // 用户所属机构ID

	private String roleIds; // 用户角色ID

	private String resultCode; // 错误码

	private String resultMsg; // 错误消息

	private Integer timeCost; // 调用耗时

	private String fullUrl; // 调用全路径

	private String remoteIp; // 访问者IP

	private String serverIp; // 服务器IP地址

	private Timestamp invokeTime; // 调用时间

	private Timestamp responseTime; // 返回时间

	private Timestamp createTiem; // 记录创建时间
	
	private Timestamp invokeTimeStart; //
	
	private Timestamp invokeTimeEnd; // 
	
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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Integer getTimeCost() {
		return timeCost;
	}

	public void setTimeCost(Integer timeCost) {
		this.timeCost = timeCost;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Timestamp getInvokeTime() {
		return invokeTime;
	}

	public void setInvokeTime(Timestamp invokeTime) {
		this.invokeTime = invokeTime;
	}

	public Timestamp getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Timestamp responseTime) {
		this.responseTime = responseTime;
	}

	public Timestamp getCreateTiem() {
		return createTiem;
	}

	public void setCreateTiem(Timestamp createTiem) {
		this.createTiem = createTiem;
	}

	public Timestamp getInvokeTimeStart() {
		return invokeTimeStart;
	}

	public void setInvokeTimeStart(Timestamp invokeTimeStart) {
		this.invokeTimeStart = invokeTimeStart;
	}

	public Timestamp getInvokeTimeEnd() {
		return invokeTimeEnd;
	}

	public void setInvokeTimeEnd(Timestamp invokeTimeEnd) {
		this.invokeTimeEnd = invokeTimeEnd;
	}
	
}
