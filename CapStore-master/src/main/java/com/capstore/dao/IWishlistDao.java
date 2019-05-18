package com.capstore.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstore.model.Wishlist;

@Repository("wishlistDao")
@Transactional
public interface IWishlistDao  extends JpaRepository<Wishlist,Integer> {

	@Query("from Wishlist WHERE customer.customerId=:customerId")
	public Wishlist getWishlistByCustomerId(@Param("customerId") int customerId);


	@Query("SELECT COUNT(wishlist_id) FROM Wishlist")
	public int getCount();

}