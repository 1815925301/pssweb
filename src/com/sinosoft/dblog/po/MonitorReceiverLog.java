package com.sinosoft.dblog.po;

import java.sql.Timestamp;

/**
 * @Package com.sinosoft.dblog.po
 * @ClassName: MonitorReceiverLog
 * @Description: 监控来自外部请求的处理情况
 * @author zzq
 * @Version V1.0
 * @date 2013-9-17 上午09:53:38
 */
public class MonitorReceiverLog {

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

	
	
	public Timestamp getCreateTiem() {
		return createTiem;
	}

	public void setCreateTiem(Timestamp createTiem) {
		this.createTiem = createTiem;
	}

	public void setInvokeTime(Timestamp invokeTime) {
		this.invokeTime = invokeTime;
	}

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

	public void setServerIp(String localIp) {
		this.serverIp = localIp;
	}

	public Timestamp getInvokeTime() {
		return invokeTime;
	}

	public void setInvokeDate(Timestamp invokeTime) {
		this.invokeTime = invokeTime;
	}

	public Timestamp getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Timestamp responseTime) {
		this.responseTime = responseTime;
	}

	public Timestamp getCreateTime() {
		return createTiem;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTiem = createTime;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("MonitorReceiverLog [");
		sb.append("innerSerialId=" + innerSerialId);
		sb.append(", serviceName=" + serviceName);
		sb.append(", fullUrl=" + fullUrl + ", serverIp=" + serverIp);
		sb.append(", remoteIp=" + remoteIp + ", userName=" + userName);
		sb.append(", userId=" + userId + ", orgId=" + orgId + ", roleIds=" + roleIds);
		sb.append(", timeCost=" + timeCost + "ms");
		sb.append(", resultCode=" + resultCode + ", resultMsg=" + resultMsg);
		sb.append(", invokeTime=" + invokeTime + ", responseTime="
				+ responseTime+ " ]");
		return sb.toString();
	}
}
