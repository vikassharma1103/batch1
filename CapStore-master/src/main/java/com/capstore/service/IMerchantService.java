package com.capstore.service;

import java.util.List;

import com.capstore.model.Merchant;

public interface IMerchantService {

	public void addMerchant(Merchant merchant);

	public void deleteMerchant(Integer merchantId);

	public Merchant getMerchantByMail(String merchantMail);
	
	public Merchant getMerchantByMerchantId(int merchantId);

	public void updateMerchant(Merchant merchant);
	
	public double getMerchantRating(int merchantId);
	
	public String getMerchantName(int merchantId);
	
	public List<Merchant> getAllMerchants();

	public boolean checkIfExists(String merchantMailId);
}
