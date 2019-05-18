package com.capstore.service;

import java.util.List;
import com.capstore.model.Customer;
import com.capstore.model.Inventory;
import com.capstore.model.Product;
import com.capstore.model.ProductImage;

public interface IProductService {

	public List<Product> getAllProducts();
	//public List<SalesAnalysis> getSalesAnalysis();
	
	public Product getProduct(int productId);

	public boolean updateProduct(Product product);
	
	public List<Product> getProductsWithoutPromotionalEmailSent();
	
	public List<Product> getSimilarProducts(int productId);
	
	public double getDiscountedPrice(Product product);

	public void addNewProduct(Inventory inventory);

	public void editProduct(Inventory inventory);
	
	public List<Object[]> getBestSellerId();
	
	public List<Product> getProductsAsc(String productCategory);
	public List<Product> getProductsDesc(String productCategory);
	public List<Product> getMostViewed(String productCategory);
	public List<Product> getBestSeller(String productCategory);
	public List<Product> getProductsInRange(String productCategory, double min, double max);
	public List<ProductImage> getProductImageId(int productId);
	public ProductImage getImage(int productId);

	public List<Product> getFilteredProducts(String productCategory);

	public Product getProductfromProductId(int productId);

	public List<Product> getSimilarProducts(String brand, String productCategory);

	public void deleteProduct(Inventory inventory);
	public Product postProductView(Product product);


	public List<Product> getMerchantProducts(String emailId);

//	public List<Product> getProductToUpload();

}
