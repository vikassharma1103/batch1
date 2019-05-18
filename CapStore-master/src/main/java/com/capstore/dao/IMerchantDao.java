package com.capstore.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.capstore.model.Merchant;

@Repository("merchantDao")
@Transactional
public interface IMerchantDao extends JpaRepository<Merchant, Integer>{
	

	public Merchant getByEmailId(String merchantMail);
	
//	public double getMerchantRating(int merchantId);
	
	//public List<Merchant> getAllMerchants();
	
}
