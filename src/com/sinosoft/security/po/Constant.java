package com.sinosoft.security.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Constant implements Serializable {
    private Long id;

    private String name;

    private String description;
    
    private Date update_time;

    private List<ConstantChild> constantChildList;



	public List<ConstantChild> getConstantChildList() {
		return constantChildList;
	}

	public void setConstantChildList(List<ConstantChild> constantChildList) {
		this.constantChildList = constantChildList;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    public void trim() {
		if (this.name != null)
			this.name = this.name.trim();
		if (this.description != null)
			this.description = this.description.trim();
	}
}