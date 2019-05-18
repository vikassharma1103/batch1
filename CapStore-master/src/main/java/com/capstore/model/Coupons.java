package com.capstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Coupons {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int couponId;
	private String couponImageUrl;
	private double maxDiscount;
	@Column(unique = true)
	private String couponCode;
	private int discountPercentage;
	private boolean isCouponAvailable=true;
	
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public String getCouponImageUrl() {
		return couponImageUrl;
	}
	public void setCouponImageUrl(String couponImageUrl) {
		this.couponImageUrl = couponImageUrl;
	}
	public double getMaxDiscount() {
		return maxDiscount;
	}
	public void setMaxDiscount(double maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public int getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public boolean isCouponAvailable() {
		return isCouponAvailable;
	}
	public void setCouponAvailable(boolean isCouponAvailable) {
		this.isCouponAvailable = isCouponAvailable;
	}
	@Override
	public String toString() {
		return "Coupons [couponId=" + couponId + ", couponImageUrl=" + couponImageUrl + ", maxDiscount=" + maxDiscount
				+ ", couponCode=" + couponCode + ", discountPercentage=" + discountPercentage + ", isCouponAvailable="
				+ isCouponAvailable + "]";
	}
	public Coupons(int couponId, String couponImageUrl, double maxDiscount, String couponCode, int discountPercentage,
			boolean isCouponAvailable) {
		super();
		this.couponId = couponId;
		this.couponImageUrl = couponImageUrl;
		this.maxDiscount = maxDiscount;
		this.couponCode = couponCode;
		this.discountPercentage = discountPercentage;
		this.isCouponAvailable = isCouponAvailable;
	}
	public Coupons() {
		super();
	}
}