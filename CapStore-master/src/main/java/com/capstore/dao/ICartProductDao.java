package com.capstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstore.model.CartProduct;


@Repository("cartProductDao")
@Transactional
public interface ICartProductDao extends JpaRepository<CartProduct, Integer> {

	
	@Query("from CartProduct where product_product_id=:productId and customer_customer_id=:customerId")
	public CartProduct findByProduct(@Param("productId") Integer productId,@Param("customerId") Integer customerId);

	@Modifying(clearAutomatically = true)
	@Query("update CartProduct set quantity=:quantity where customer_customer_id=:customerId and product_product_id=:productId")
	public void updateQuantity(@Param("quantity")Integer quantity, @Param("productId") Integer productId,
																				@Param("customerId") Integer customerId);

	@Query("SELECT COUNT(serialNo) FROM CartProduct")
	public int getCount();
}
	
