package com.sinosoft.business.po;

import java.util.Date;
import java.util.List;

/**
 * 主订单信息实体类
 * 
 * @author Dylan
 * @date 16-08-20 16:33:45
 */
public class OrderMain {

	private Long orderMainId; // 订单ID号ORDER_MAIN_ID
	private String orderName; // 订单名称ORDERNAME
	private String orderer; // 订购人ORDERER
	private String orderState; // 订单状态0未完成，1完成ORDERSTATE
	private Date orderTime; // 订购时间ORDERTIME
	private Date finishTime; // 主订单完成时间FINISHTIME
	private String remark; // 备注REMARK
	private List<PssOrderInfo> orderInfoList; //子订单实体类
	private Integer ispay;
	private Integer price;
	private Integer isrefund; //是否退款
	private String uuit;
	public String getUuit() {
		return uuit;
	}
	public void setUuit(String uuit) {
		this.uuit = uuit;
	}
	public Integer getIsrefund() {
		return isrefund;
	}
	public void setIsrefund(Integer isrefund) {
		this.isrefund = isrefund;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getIspay() {
		return ispay;
	}
	public void setIspay(Integer ispay) {
		this.ispay = ispay;
	}
	public Long getOrderMainId() {
		return orderMainId;
	}
	public void setOrderMainId(Long orderMainId) {
		this.orderMainId = orderMainId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<PssOrderInfo> getOrderInfoList() {
		return orderInfoList;
	}
	public void setOrderInfoList(List<PssOrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}

	/**
	 * 获取子订单条数
	 * @author Dylan
	 * @return String 返回子订单条数，子订单不存在时返回""。
	 */
	public String getCount(){
		return null != orderInfoList ? orderInfoList.size()+"" : "";
	}
}
