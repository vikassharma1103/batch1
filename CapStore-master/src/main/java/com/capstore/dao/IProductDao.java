package com.capstore.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstore.model.Inventory;
import com.capstore.model.Product;
import com.capstore.model.ProductImage;

@Repository("productDao")
@Transactional
public interface IProductDao extends JpaRepository<Product,Integer> {

	//public Product findTop1ByOrderByproductsSoldDesc();
	public List<Product> getProductsByIsPromotionMessageSent(boolean isSent);

	@Query("SELECT productCategory, SUM(quantity*productPrice), SUM(productsSold*productPrice) FROM"
			+ " Product group by productCategory")
	public List<Object[]> getProductSold();

	public Product getProductByInventory(Inventory inventory);
	
	@Query("SELECT productCategory, inventory.merchant.merchantId FROM Product WHERE productsSold in(SELECT MAX(productsSold) from Product GROUP BY productCategory)")
	public List<Object[]> getBestSellerId();
	
	
	public List<Product> findByProductCategoryOrderByProductPrice(String productCategory);

	public List<Product> findByProductCategoryOrderByProductPriceDesc(String productCategory);
    
	//@Query("from Product WHERE productView in(SELECT MAX(productView) from Product WHERE productCategory=:productCategory GROUP BY productCategory)")
	public List<Product> findByproductCategoryOrderByProductViewDesc(String productCategory);

	//@Query("from Product WHERE productsSold in(SELECT MAX(productsSold) from Product WHERE productCategory=:productCategory GROUP BY productCategory)")
	public List<Product> findByproductCategoryOrderByProductsSoldDesc(String productCategory);

	@Query("from Product WHERE  productCategory=:productCategory AND productPrice>=:min AND  productPrice<:max")
	public List<Product> getProductsInRange(String productCategory, double min, double max);
    
	@Query("from ProductImage WHERE product_product_id=:productId AND imageStatus='slider' ")
	public List<ProductImage> getProductImageId(int productId);

	@Query("from ProductImage WHERE product_id=:productId AND imageStatus='main' ")
	public ProductImage getImage(int productId);

	@Query("from Product WHERE product_id=:productId")
	public Product getProductfromProductId(int productId);
    
	@Query("from Product WHERE brand=:brand AND productCategory=:productCategory")
	public List<Product> getSimilarProducts(String brand, String productCategory);
	/*@Query("from Product WHERE image_url is null")
	public List<Product> getProductToUpload();*/

}
