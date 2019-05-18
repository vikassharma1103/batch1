package com.capstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductImage {
	
	@Id
	@Column(name="imageId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int imageId;
	
//	@Column(name="productId")
	@OneToOne(targetEntity=Product.class)
	private Product product;
	private String imageUrl;
	private String imageStatus;
	
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getImageStatus() {
		return imageStatus;
	}
	public void setImageStatus(String imageStatus) {
		this.imageStatus = imageStatus;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getString() {
		return imageStatus;
	}
	public void setString(String imageStatus) {
		this.imageStatus = imageStatus;
	}
	@Override
	public String toString() {
		return "ProductImage [imageId=" + imageId + ", product=" + product + ", imageUrl=" + imageUrl + ", imageStatus="
				+ imageStatus + "]";
	}
	public ProductImage(int imageId, Product product, String imageUrl, String imageStatus) {
		super();
		this.imageId = imageId;
		this.product = product;
		this.imageUrl = imageUrl;
		this.imageStatus = imageStatus;
	}
	public ProductImage() {
		super();
	}
}