package com.sinosoft.business.po.query;

import java.util.Date;

import com.sinosoft.po.query.BasePaginationQuery;

/**
 * 主订单信息查询实体类
 * 
 * @author Dylan
 * @date 16-08-20 16:33:45
 */
public class OrderMainQuery extends BasePaginationQuery {

	private Long orderMainId; // 订单ID号ORDER_MAIN_ID
	private String orderer; // 订购人ORDERER
	private String orderName; // 订单名称ORDERNAME
	private String remark; // 备注REMARK
	private Date finishTime; // 主订单完成时间FINISHTIME
	private String orderState; // 订单状态0未完成，1完成ORDERSTATE
	private Date orderTime; // 订购时间ORDERTIME
	private Integer ispay;
	
	
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

	public String getOrderer() {
		return orderer;
	}

	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
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

}
