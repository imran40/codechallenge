package com.codechallenge;

public class Product {

	private String productName = null;
	private Integer productPosition = null;
	private Integer productPrice = null;
	private Integer productCount = null;
	private Boolean productOOS = false;

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductPosition() {
		return productPosition;
	}
	public void setProductPosition(Integer productPosition) {
		this.productPosition = productPosition;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public Boolean isProductOOS() {
		return productOOS;
	}
	public void setProductOOS(Boolean productOOS) {
		this.productOOS = productOOS;
	}
	
}
