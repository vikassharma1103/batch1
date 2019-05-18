package com.capstore.model;

import java.util.Date;

public class DispatchAnalysis {
	
	private String productName;
	private String merchantName;
	private Date expectedDispatchDate;
	private Date actualDispatchDate;
	private Date deliveryDate;
	public DispatchAnalysis(String productName, String merchantName, Date expectedDispatchDate, Date actualDispatchDate,
			Date deliveryDate) {
		super();
		this.productName = productName;
		this.merchantName = merchantName;
		this.expectedDispatchDate = expectedDispatchDate;
		this.actualDispatchDate = actualDispatchDate;
		this.deliveryDate = deliveryDate;
	}
	
	public DispatchAnalysis()	{}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Date getExpectedDispatchDate() {
		return expectedDispatchDate;
	}

	public void setExpectedDispatchDate(Date expectedDispatchDate) {
		this.expectedDispatchDate = expectedDispatchDate;
	}

	public Date getActualDispatchDate() {
		return actualDispatchDate;
	}

	public void setActualDispatchDate(Date actualDispatchDate) {
		this.actualDispatchDate = actualDispatchDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public String toString() {
		return "DispatchAnalysis [productName=" + productName + ", merchantName=" + merchantName
				+ ", expectedDispatchDate=" + expectedDispatchDate + ", actualDispatchDate=" + actualDispatchDate
				+ ", deliveryDate=" + deliveryDate + "]";
	}
	
	

}
