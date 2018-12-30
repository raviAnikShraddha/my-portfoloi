package com.livinu.portfolio.dto;

import java.io.Serializable;

public class ProductRequset implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long productId;

	private String productName;

	private String productDesc;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	
	

}
