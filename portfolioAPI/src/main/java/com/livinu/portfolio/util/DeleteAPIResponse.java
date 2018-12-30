package com.livinu.portfolio.util;

import java.io.Serializable;

public class DeleteAPIResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	Long id;
	boolean isDeleted = true;

	public DeleteAPIResponse() {
		super();
	}

	public DeleteAPIResponse(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
