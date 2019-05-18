package com.capstore.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstore.model.Feedback;

@Repository("feedbackDao")
@Transactional
public interface IFeedbackDao extends JpaRepository<Feedback,Integer>  {
	
	@Query("from Feedback where product.productId=:productId")
	public List<Feedback> findByProductId(@Param("productId") int productId);
	
	@Query("from Feedback where merchant.merchantId=:merchantId")
	public List<Feedback> findByMerchantId(@Param("merchantId") int merchantId);


	@Query("from Feedback order by merchant.merchantId")
	public List<Feedback> findAllOrderByMerchantId();

	@Query("select count(*) from Feedback group by merchant.merchantId")
	public List<Long> numberOfFeedbacksPerMerchant();
	
	

	@Query("from Feedback WHERE product_product_id=:productId")
	public List<Feedback> getAllFeedbacks(int productId);

	

}
