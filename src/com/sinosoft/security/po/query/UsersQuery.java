package com.sinosoft.security.po.query;

import com.sinosoft.po.query.BasePaginationQuery;

/**
 * @Package com.sinosoft.security.po.query
 * @ClassName: UsersQuery
 * @Description: 查询用户信息 Query类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-3 下午06:56:03
 */
public class UsersQuery extends BasePaginationQuery {

	private Long id;// 用户ID
	private String userName;// 用户登录名
	private String userEmail;// 用户电子邮箱地址
	private String trueName;// 用户真实姓名
	private Long orgId;// 用户所属机构id
	private String userMobile;// 用户手机号码
	private String isLock;// 当前用户的状态，是否被锁定
	private String password;
	private String identityCard;//证件号
    private String remark;//审核不通过原因
    
    private String starttime;//前台开始时间
	private String endtime;//前台结束时间
	private String amount;//数量
	private String timeType;//时间类型(年,月,日)
	private String RegisterTime;//用户注册时间
	
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



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public String getTimeType() {
		return timeType;
	}



	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}



	public String getRegisterTime() {
		return RegisterTime;
	}



	public void setRegisterTime(String registerTime) {
		RegisterTime = registerTime;
	}



	public String getIdentityCard() {
		return identityCard;
	}



	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	private String sqlWhere;
	
	public String getSqlWhere() {
		return sqlWhere;
	}

	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
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

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}

}
