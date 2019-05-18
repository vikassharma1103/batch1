package com.capstore.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Merchant {
	
	@Id
	@Column(name="merchantId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int merchantId;
	private String merchantName;
	
	@Column(unique=true,name="emailId")
	private String emailId;
	
	private String merchantPassword;
	private String merchantContact;
	
	@OneToOne(targetEntity=Address.class,cascade=CascadeType.ALL)
//	@Column(name="merchantAddress")
	private Address merchantAddress;
	private boolean isVerified;
	
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMerchantContact() {
		return merchantContact;
	}
	public void setMerchantContact(String merchantContact) {
		this.merchantContact = merchantContact;
	}
	public Address getMerchantAddress() {
		return merchantAddress;
	}
	public void setMerchantAddress(Address merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}
	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}
	@Override
	public String toString() {
		return "Merchant [merchantId=" + merchantId + ", merchantName=" + merchantName + ", emailId=" + emailId
				+ ", merchantPassword=" + merchantPassword + ", merchantContact=" + merchantContact
				+ ", merchantAddress=" + merchantAddress + ", isVerified=" + isVerified + "]"
				+ ", merchantContact=" + merchantContact + ", merchantAddress="
				+ merchantAddress + ", isVerified=" + isVerified + "]";
	}
	public Merchant(int merchantId, String merchantName, String emailId, String merchantPassword,
			String merchantContact, Address merchantAddress, boolean isVerified) {
		super();
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.emailId = emailId;
		this.merchantPassword = merchantPassword;
		this.merchantContact = merchantContact;
		this.merchantAddress = merchantAddress;
		this.isVerified = isVerified;
	}
	public Merchant() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + (isVerified ? 1231 : 1237);
		result = prime * result + ((merchantAddress == null) ? 0 : merchantAddress.hashCode());
		result = prime * result + ((merchantContact == null) ? 0 : merchantContact.hashCode());
		result = prime * result + merchantId;
		result = prime * result + ((merchantName == null) ? 0 : merchantName.hashCode());
		result = prime * result + ((merchantPassword == null) ? 0 : merchantPassword.hashCode());
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
		Merchant other = (Merchant) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (isVerified != other.isVerified)
			return false;
		if (merchantAddress == null) {
			if (other.merchantAddress != null)
				return false;
		} else if (!merchantAddress.equals(other.merchantAddress))
			return false;
		if (merchantContact == null) {
			if (other.merchantContact != null)
				return false;
		} else if (!merchantContact.equals(other.merchantContact))
			return false;
		if (merchantId != other.merchantId)
			return false;
		if (merchantName == null) {
			if (other.merchantName != null)
				return false;
		} else if (!merchantName.equals(other.merchantName))
			return false;
		if (merchantPassword == null) {
			if (other.merchantPassword != null)
				return false;
		} else if (!merchantPassword.equals(other.merchantPassword))
			return false;
		return true;
	}
	
	
}