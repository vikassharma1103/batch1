package com.capstore.model;

public class SalesAnalysis {

	private String productCategory;
	private String merchant;
	private double productQuantity;
	private double productSales;
	private double salesPercent;
	private double totalRevenue;
	
	
	public SalesAnalysis(String productCategory, String merchant, double productQuantity, double productSales,
			double salesPercent, double totalRevenue) {
		super();
		this.productCategory = productCategory;
		this.merchant = merchant;
		this.productQuantity = productQuantity;
		this.productSales = productSales;
		this.salesPercent = salesPercent;
		this.totalRevenue = totalRevenue;
	}

	public SalesAnalysis()	{}
	
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public double getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Double object) {
		this.productQuantity = object;
	}
	public double getProductSales() {
		return productSales;
	}
	public void setProductSales(double productSales) {
		this.productSales = productSales;
	}
	public double getSalesPercent() {
		return salesPercent;
	}
	public void setSalesPercent(double salesPercent) {
		this.salesPercent = salesPercent;
	}

	@Override
	public String toString() {
		return "SalesAnalysis [productCategory=" + productCategory + ", merchant=" + merchant + ", productQuantity="
				+ productQuantity + ", productSales=" + productSales + ", salesPercent=" + salesPercent
				+ ", totalRevenue=" + totalRevenue + "]";
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public void setProductQuantity(double productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	
	
	
	
	
}
