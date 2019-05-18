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
public class Product {
	
	@Id
	@Column(name="productId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productId;
	private String productName;
	private String productCategory;
	
//	@Column(name="inventoryId")
	@OneToOne(targetEntity=Inventory.class)
	private Inventory inventory;
	
	private double productPrice;
	/*
	@Column(name="merchantId")
	@OneToOne(targetEntity=Merchant.class)
	private Merchant merchant;
	*/
	@OneToOne(targetEntity=Promos.class)
	private Promos promo;
	private int productsSold;
	private int productView;
	private boolean isPromotionMessageSent;
	private String productDescription;
	private int quantity;
	private int discount;
	private String brand;
	
	@Column(name="imageUrl")
	private String imageUrl;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getString() {
		return productCategory;
	}
	public void setString(String productCategory) {
		this.productCategory = productCategory;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Promos getPromo() {
		return promo;
	}
	public void setPromo(Promos promo) {
		this.promo = promo;
	}
	public int getProductsSold() {
		return productsSold;
	}
	public void setProductsSold(int productsSold) {
		this.productsSold = productsSold;
	}
	public int getProductView() {
		return productView;
	}
	public void setProductView(int productView) {
		this.productView = productView;
	}
	public boolean isPromotionMessageSent() {
		return isPromotionMessageSent;
	}
	public void setPromotionMessageSent(boolean isPromotionMessageSent) {
		this.isPromotionMessageSent = isPromotionMessageSent;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productCategory="
				+ productCategory + ", inventory=" + inventory + ", productPrice=" + productPrice + ", promo=" + promo
				+ ", productsSold=" + productsSold + ", productView=" + productView + ", isPromotionMessageSent="
				+ isPromotionMessageSent + ", productDescription=" + productDescription + ", quantity=" + quantity
				+ ", discount=" + discount + ", brand=" + brand + ", imageUrl=" + imageUrl + "]";
	}
	
	public Product(int productId, String productName, String productCategory, Inventory inventory, double productPrice,
			Promos promo, int productsSold, int productView, boolean isPromotionMessageSent, String productDescription,
			int quantity, int discount, String brand, String imageUrl) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.inventory = inventory;
		this.productPrice = productPrice;
		this.promo = promo;
		this.productsSold = productsSold;
		this.productView = productView;
		this.isPromotionMessageSent = isPromotionMessageSent;
		this.productDescription = productDescription;
		this.quantity = quantity;
		this.discount = discount;
		this.brand = brand;
		this.imageUrl = imageUrl;
	}
	public Product() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + discount;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result + (isPromotionMessageSent ? 1231 : 1237);
		result = prime * result + ((productCategory == null) ? 0 : productCategory.hashCode());
		result = prime * result + ((productDescription == null) ? 0 : productDescription.hashCode());
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(productPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + productView;
		result = prime * result + productsSold;
		result = prime * result + ((promo == null) ? 0 : promo.hashCode());
		result = prime * result + quantity;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (discount != other.discount)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		if (isPromotionMessageSent != other.isPromotionMessageSent)
			return false;
		if (productCategory == null) {
			if (other.productCategory != null)
				return false;
		} else if (!productCategory.equals(other.productCategory))
			return false;
		if (productDescription == null) {
			if (other.productDescription != null)
				return false;
		} else if (!productDescription.equals(other.productDescription))
			return false;
		if (productId != other.productId)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (Double.doubleToLongBits(productPrice) != Double.doubleToLongBits(other.productPrice))
			return false;
		if (productView != other.productView)
			return false;
		if (productsSold != other.productsSold)
			return false;
		if (promo == null) {
			if (other.promo != null)
				return false;
		} else if (!promo.equals(other.promo))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
}