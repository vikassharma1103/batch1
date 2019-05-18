package com.capstore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Promos {
	
	@Id
	@Column(name="promoId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int promoId;
	/*private String promoImageUrl;*/
	private String promoCode;
	private int discount;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	public int getPromoId() {
		return promoId;
	}
	public void setPromoId(int promoId) {
		this.promoId = promoId;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Promos [promoId=" + promoId + ", promoCode=" + promoCode + ", discount=" + discount + ", endDate="
				+ endDate + "]";
	}
	public Promos(int promoId, String promoCode, int discount, Date endDate) {
		super();
		this.promoId = promoId;
		this.promoCode = promoCode;
		this.discount = discount;
		this.endDate = endDate;
	}
	public Promos() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + discount;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((promoCode == null) ? 0 : promoCode.hashCode());
		result = prime * result + promoId;
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
		Promos other = (Promos) obj;
		if (discount != other.discount)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (promoCode == null) {
			if (other.promoCode != null)
				return false;
		} else if (!promoCode.equals(other.promoCode))
			return false;
		if (promoId != other.promoId)
			return false;
		return true;
	}
	
	
}