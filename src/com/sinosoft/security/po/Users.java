package com.sinosoft.security.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package com.sinosoft.security.po
 * @ClassName: Users
 * @Description: 系统用户信息 实体类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-2 下午08:34:45
 */
public class Users implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	public Users() {}
	
	public Users(Long id, String password) {
		this.id = id;
		this.password = password;
	}

	private Long id;// 用户ID
	private String userName;// 用户登录名
	private String password; // 用户登录密码
	private String userEmail;// 用户电子邮箱地址
	private String trueName;// 用户真实姓名
	private Long orgId;// 用户所属机构id
	private String userTitle; // 用户职称
	private String userMobile;// 用户手机号码
	private String userPhone;// 用户座机号码
	private Integer isLock;// 当前用户的状态，是否被锁定
	private Integer editable; // 该系统用户是否可以编辑和删除
	private String createTime;// 用户被创建的时间
	private Long createUserId; // 创建者的用户ID
	private String updateTime; // 修改信息的时间
	private Long updateUserId; // 修改者的用户ID
	private String note;// 备注
	private Integer roleId;
	private Long deptId;// 部门ID
	private String identityCard;//身份证ID
	private Integer userSex;//性别
	private String address; //通讯地址
	private String RegisterTime;//用户注册时间
	private String starttime;//前台开始时间
	private String endtime;//前台结束时间
	private String counts;
	private String remark;//审核不通过原因
	private String activatetime; // 激活时间
	
	private String passprompt; //密码提示问题
	private String passanswer; // 密码提示问题答案
	private Integer postcode; // 邮编
	private String fax;//传真
	private String ipAddress;//个人ip地址
	private String company;// 公司名称
	private String companyAddress;//公司地址
	private String lawyerName;//法人姓名
	private String lawyerPhone;//法人电话
	private String lawyerMail;//法人邮箱
	private String companyCode; // 公司邮编
	private String companyFax; // 公司传真
	private String companyIp;//公司IP地址
	private String country;//国家
	
	
	private String province;//省份
	private String city;//市
	private String county;//县
	private Integer downloads;//免费下载景数
	private Integer identifier;//区别找回密码和找回用户名
	
	
	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}

	public String getActivatetime() {
		return activatetime;
	}

	public void setActivatetime(String activatetime) {
		this.activatetime = activatetime;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getRegisterTime() {
		return RegisterTime;
	}

	public void setRegisterTime(String registerTime) {
		RegisterTime = registerTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String msn; //MSN
	private Integer nsms; //手机短信
	private Integer nmsn; //MSN Messenger
	private Integer nemail; //接收通知
	
	private Integer status=0;//激活状态
	private String validateCode;//激活码
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public Integer getNsms() {
		return nsms;
	}

	public void setNsms(Integer nsms) {
		this.nsms = nsms;
	}

	public Integer getNmsn() {
		return nmsn;
	}

	public void setNmsn(Integer nmsn) {
		this.nmsn = nmsn;
	}

	public Integer getNemail() {
		return nemail;
	}

	public void setNemail(Integer nemail) {
		this.nemail = nemail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Integer getEditable() {
		return editable;
	}

	public void setEditable(Integer editable) {
		this.editable = editable;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "userName: " + userName + ",password: " + password
				+ ",userEmail: " + userEmail + ",trueName: " + trueName
				+ ",orgId: " + orgId + ",userTitle: " + userTitle;
	}

	/**
	 * 过滤掉用户信息中各个属性值的前后空格
	 * @return void
	 * @throws
	 * @author mrajian
	 * @date 2013-10-13 上午09:49:26
	 * @version V1.0
	 */
	public void trim() {
		if (this.userName != null)
			this.userName = this.userName.trim();
		if (this.userEmail != null)
			this.userEmail = this.userEmail.trim();
		if (this.trueName != null)
			this.trueName = this.trueName.trim();
		if (this.userTitle != null)
			this.userTitle = this.userTitle.trim();
		if (this.userMobile != null)
			this.userMobile = this.userMobile.trim();
		if (this.userPhone != null)
			this.userPhone = this.userPhone.trim();
		if (this.createTime != null)
			this.createTime = this.createTime.trim();
		if (this.updateTime != null)
			this.updateTime = this.updateTime.trim();
		if (this.note != null)
			this.note = this.note.trim();
	}

	public String getCompanyIp() {
		return companyIp;
	}

	public void setCompanyIp(String companyIp) {
		this.companyIp = companyIp;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getLawyerName() {
		return lawyerName;
	}

	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}

	public String getLawyerMail() {
		return lawyerMail;
	}

	public void setLawyerMail(String lawyerMail) {
		this.lawyerMail = lawyerMail;
	}

	public String getLawyerPhone() {
		return lawyerPhone;
	}

	public void setLawyerPhone(String lawyerPhone) {
		this.lawyerPhone = lawyerPhone;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	public String getPassprompt() {
		return passprompt;
	}

	public void setPassprompt(String passprompt) {
		this.passprompt = passprompt;
	}

	public String getPassanswer() {
		return passanswer;
	}

	public void setPassanswer(String passanswer) {
		this.passanswer = passanswer;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}


}
