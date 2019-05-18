package com.capstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IMerchantDao;
import com.capstore.model.Customer;
import com.capstore.model.Merchant;

@Service("merchantService")
public class MerchantService implements IMerchantService{

	@Autowired
	IMerchantDao merchantDao;
	
	@Autowired
	public FeedbackService feedbackService;

	@Override
	public void addMerchant(Merchant merchant) {
		 merchantDao.save(merchant);
	}

	@Override
	public void deleteMerchant(Integer merchantId) {
		merchantDao.deleteById(merchantId);
		
	}

	@Override
	public Merchant getMerchantByMail(String merchantMail) {
		Merchant merchant= merchantDao.getByEmailId(merchantMail);
		System.out.println(merchant);
		return merchant;
	}

	@Override
	public void updateMerchant(Merchant merchant) {
		merchantDao.save(merchant);
	}

	@Override
	public double getMerchantRating(int merchantId) {
		
		return feedbackService.calculateMerchantRating(merchantId);
	}

	
	@Override
	public String getMerchantName(int merchantId) {
		Merchant merchant=merchantDao.findById(merchantId).get();
		if(merchant==null)	
			return null;
		return merchant.getMerchantName();
	}

	@Override
	public List<Merchant> getAllMerchants() {
		return merchantDao.findAll();
	}

	@Override
	public boolean checkIfExists(String merchantMailId) {
		Merchant merchant=merchantDao.getByEmailId(merchantMailId);
		if(merchant==null)
			return true;
		else return false;
	}

	@Override
	public Merchant getMerchantByMerchantId(int merchantId) {
		Optional<Merchant> optional = merchantDao.findById(merchantId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
}
