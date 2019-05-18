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
public class Inventory {

	@Id
	@Column(name="inventoryId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int inventoryId;
	
//	@Column(name="merchantId")
	@OneToOne(targetEntity=Merchant.class)
	private Merchant merchant;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="productCategory")
	private String productCategory;
	@Column(name="productPrice")
	private double productPrice;
	@Column(name="productDescription")
	private String productDescription;
	private String productBrand;
	
//	@Column(name="promoId")
	@OneToOne(targetEntity=Promos.class)
	private Promos promo;
	@Column(name="status")
	private String status;
	@Column(name="inventoryType")
	private String inventoryType;
	@Column(name="inventoryQuantity")
	private int inventoryQuantity;
	
	@Column(name="imageUrl")
	private String imageUrl;
	
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public Promos getPromo() {
		return promo;
	}
	public void setPromo(Promos promo) {
		this.promo = promo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInventoryType() {
		return inventoryType;
	}
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public int getInventoryQuantity() {
		return inventoryQuantity;
	}
	public void setInventoryQuantity(int inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", merchant=" + merchant + ", productName=" + productName
				+ ", productCategory=" + productCategory + ", productPrice=" + productPrice + ", productDescription="
				+ productDescription + ", productBrand=" + productBrand + ", promo=" + promo + ", status=" + status
				+ ", inventoryType=" + inventoryType + ", inventoryQuantity=" + inventoryQuantity + ", imageUrl="
				+ imageUrl + "]";
	}
	
	public Inventory(int inventoryId, Merchant merchant, String productName, String productCategory,
			double productPrice, String productDescription, String productBrand, Promos promo, String status,
			String inventoryType, int inventoryQuantity, String imageUrl) {
		super();
		this.inventoryId = inventoryId;
		this.merchant = merchant;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productBrand = productBrand;
		this.promo = promo;
		this.status = status;
		this.inventoryType = inventoryType;
		this.inventoryQuantity = inventoryQuantity;
		this.imageUrl = imageUrl;
	}
	public Inventory() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + inventoryId;
		result = prime * result + inventoryQuantity;
		result = prime * result + ((inventoryType == null) ? 0 : inventoryType.hashCode());
		result = prime * result + ((merchant == null) ? 0 : merchant.hashCode());
		result = prime * result + ((productBrand == null) ? 0 : productBrand.hashCode());
		result = prime * result + ((productCategory == null) ? 0 : productCategory.hashCode());
		result = prime * result + ((productDescription == null) ? 0 : productDescription.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(productPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((promo == null) ? 0 : promo.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Inventory other = (Inventory) obj;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (inventoryId != other.inventoryId)
			return false;
		if (inventoryQuantity != other.inventoryQuantity)
			return false;
		if (inventoryType == null) {
			if (other.inventoryType != null)
				return false;
		} else if (!inventoryType.equals(other.inventoryType))
			return false;
		if (merchant == null) {
			if (other.merchant != null)
				return false;
		} else if (!merchant.equals(other.merchant))
			return false;
		if (productBrand == null) {
			if (other.productBrand != null)
				return false;
		} else if (!productBrand.equals(other.productBrand))
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
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (Double.doubleToLongBits(productPrice) != Double.doubleToLongBits(other.productPrice))
			return false;
		if (promo == null) {
			if (other.promo != null)
				return false;
		} else if (!promo.equals(other.promo))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
}