package com.sinosoft.security.controller;

import java.util.HashSet;
import java.util.Set;

public class Channel {
	private Integer id;
	private String name;
	private Set<Channel> child = new HashSet<Channel>();
	
	public Set<Channel> getChild() {
		return child;
	}

	public void setChild(Set<Channel> child) {
		this.child = child;
	}

	public Channel(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}