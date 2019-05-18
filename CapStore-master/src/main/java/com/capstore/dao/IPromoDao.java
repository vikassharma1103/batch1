package com.capstore.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstore.model.Promos;


@Repository("promoDao")
@Transactional
public interface IPromoDao extends JpaRepository<Promos, Integer>{
	
	
	Promos getPromoByPromoCode(String promoCode);

}
