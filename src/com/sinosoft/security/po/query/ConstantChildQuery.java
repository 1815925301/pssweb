package com.sinosoft.security.po.query;

import java.io.Serializable;
import java.util.Date;

import com.sinosoft.po.query.BasePaginationQuery;


public class ConstantChildQuery extends BasePaginationQuery implements Serializable  {
    private Long id;

    private Long constant_id;

    private String code;

    private String value;

    private Date update_time;
    
    private String tableName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 

    public Long getConstant_id() {
		return constant_id;
	}

	public void setConstant_id(Long constant_id) {
		this.constant_id = constant_id;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}