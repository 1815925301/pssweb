package com.sinosoft.security.po;

import java.io.Serializable;
import java.util.Date;

public class ConstantChild implements Serializable {
    private Long id;

    private Long constant_id;

    private String code;

    private String value;

    private Date update_time;
    
    private String tableName;
    
    private String name;
    
    private String description;
    
    private String code_old;

   
	
	public String getCode_old() {
		return code_old;
	}

	public void setCode_old(String code_old) {
		this.code_old = code_old;
	}
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
    
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	 public void trim() {
		if (this.code != null)
			this.code = this.code.trim();
		if (this.value != null)
			this.value = this.value.trim();
	}
}