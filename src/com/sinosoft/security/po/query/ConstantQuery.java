package com.sinosoft.security.po.query;

import java.io.Serializable;

import com.sinosoft.po.query.BasePaginationQuery;


public class ConstantQuery extends BasePaginationQuery implements Serializable {
    private Long id;

    private String name;

    private String description;

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
}