package com.capstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IPromoDao;
import com.capstore.model.Promos;

@Service("promoService")
public class PromoService implements IPromoService{

	@Autowired
	IPromoDao promoDao;
	
	@Override
	public int getDiscount(int promoId) {
		Promos p= (Promos) promoDao.findById(promoId).get();

		
		return p.getDiscount();
	}
	 
	
	@Override
	public void addPromo(Promos promo) {
		promoDao.save(promo);	
	}

	@Override
	public List<Promos> getAllPromos() {
		return promoDao.findAll();
	}

	@Override
	public Promos getPromo(String promoCode) {
		return promoDao.getPromoByPromoCode(promoCode);
	}

}
