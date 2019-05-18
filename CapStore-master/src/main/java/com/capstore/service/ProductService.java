package com.capstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IInventoryMerchantDao;
import com.capstore.dao.IProductDao;
import com.capstore.model.Inventory;
import com.capstore.model.Merchant;
import com.capstore.model.Product;
import com.capstore.model.ProductImage;

@Service("productService")
public class ProductService implements IProductService{

	@Autowired
	private IProductDao productDao;
	@Autowired
	private IMerchantService merchantService;
	@Autowired
	private IInventoryMerchantDao inventoryDao;
	@Override
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}
	
	/*@Override
	public List<SalesAnalysis> getSalesAnalysis() {
		double salesPercentage=0.00;
		List<Object[]> bestSellerDetails=productDao.getBestSellerId();
		List<Object[]> productSales=productDao.getProductSold();
		List<SalesAnalysis> salesAnalysis=new ArrayList<>();
		for(Object[] object:productSales)	{
			SalesAnalysis sales=new SalesAnalysis();
			sales.setProductCategory(((String)object[0]).toUpperCase());
			sales.setProductQuantity((Double)object[1]);
			sales.setProductSales((Double)object[2]);
			for(Object[] object1:bestSellerDetails)	{
				if(((String)object[0]).equals((String)object1[0]))
					sales.setMerchant(merchantService.getMerchantName((Integer)object1[1]).toUpperCase());
			}
			salesPercentage=(sales.getProductSales()*100)/sales.getProductQuantity();
			sales.setSalesPercent(salesPercentage);
			
			salesAnalysis.add(sales);
		}
		return salesAnalysis;
	}
*/
	@Override
	public Product getProduct(int productId) {
		Optional<Product> optional = productDao.findById(productId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public boolean updateProduct(Product product) {
		productDao.save(product);
		return true;
	}

	@Override
	public List<Product> getProductsWithoutPromotionalEmailSent() {
		return productDao.getProductsByIsPromotionMessageSent(false);
	}

	@Override
	public List<Product> getSimilarProducts(int productId) {
		
		Product product = getProduct(productId);
		
		List<Product> similarProducts = new ArrayList<>();
		List<Product> allProducts = getAllProducts();
		int numberOfProducts = 0;
		
		for(Product myProduct:allProducts) {
			if(!myProduct.equals(product)) {
				if(myProduct.getProductCategory().equals(product.getProductCategory())) {
					
					numberOfProducts ++;
					if(numberOfProducts > 3) {
						return similarProducts;
					}else {
						similarProducts.add(myProduct);
					}
				}
			}
		}
		
		return similarProducts;
	}

	@Override
	public double getDiscountedPrice(Product product) {
		
		if(product == null) {
			return 0;
		}
		
		double discountedPrice = 0;
		int discount = product.getDiscount();
		int promo = product.getPromo().getDiscount();
		
		discountedPrice = (double)product.getProductPrice();
		
		if(promo != 0) {
			
			discountedPrice = discountedPrice - (discountedPrice*promo)/100;
		}
		
		if(discount != 0) {
			
			discountedPrice = discountedPrice - (discountedPrice*discount)/100;
		}
		
		return discountedPrice;
	}

	@Override
	public void addNewProduct(Inventory inventory) {
		Product product=new Product();//getProductByInventory(inventory);
		
		product.setInventory(inventory);
		product.setProductName(inventory.getProductName());
		product.setProductPrice(inventory.getProductPrice());
		product.setProductCategory(inventory.getProductCategory());
		product.setPromo(inventory.getPromo());
		product.setProductDescription(inventory.getProductDescription());
		product.setBrand(inventory.getProductBrand());
		product.setImageUrl(inventory.getImageUrl());
		
		productDao.save(product);
		
		
	}

	private Product getProductByInventory(Inventory inventory) {
		
		return productDao.getProductByInventory(inventory);
		
	}

	@Override
	public void editProduct(Inventory inventory) {
		Product product=getProductByInventory(inventory);
		if(product==null) {
			product=new Product();
		}
		product.setInventory(inventory);
		product.setProductName(inventory.getProductName());
		product.setProductPrice(inventory.getProductPrice());
		product.setProductCategory(inventory.getProductCategory());
		product.setPromo(inventory.getPromo());
		product.setProductDescription(inventory.getProductDescription());
		product.setBrand(inventory.getProductBrand());
		product.setImageUrl(inventory.getImageUrl());
		
		productDao.save(product);
		
	}
	
	@Override
	public List<Object[]> getBestSellerId() {
		return productDao.getBestSellerId();
	}
	
	@Override
	public List<Product> getProductsAsc(String productCategory) {
		List<Product> products=productDao.findByProductCategoryOrderByProductPrice(productCategory);
		
		return products;
	}

	@Override
	public List<Product> getProductsDesc(String productCategory) {
		List<Product> products1=productDao.findByProductCategoryOrderByProductPriceDesc(productCategory);
		return products1;
	}

	@Override
	public List<Product> getMostViewed(String productCategory) {
	
		 List<Product> product2=productDao.findByproductCategoryOrderByProductViewDesc(productCategory);
		return product2;
	
	}

	@Override
	public List<Product> getBestSeller(String productCategory) {
		 List<Product> product3=productDao.findByproductCategoryOrderByProductsSoldDesc(productCategory);
		return product3;
	}

	@Override
	public List<Product> getProductsInRange(String productCategory, double min, double max) {
		List<Product> product4 = productDao.getProductsInRange(productCategory,min,max);
		return product4;
	}

	@Override
	public List<ProductImage> getProductImageId(int productId) {
		
		 List<ProductImage> imageId =productDao.getProductImageId(productId);
	  return imageId;
		
	}

	@Override
	public ProductImage getImage(int productId) {
		ProductImage image=productDao.getImage(productId);
		return image;
	}

	@Override
	public List<Product> getFilteredProducts(String productCategory) {
		return productDao.findByproductCategoryOrderByProductViewDesc(productCategory);
	}

	@Override
	public Product getProductfromProductId(int productId) {
	
		return productDao.getProductfromProductId(productId);
	}

	@Override
	public List<Product> getSimilarProducts(String brand, String productCategory) {
		return productDao.getSimilarProducts(brand,productCategory);
	}

	@Override

	public void deleteProduct(Inventory inventory) {
		Product product=getProductByInventory(inventory);
		
		product.setInventory(inventory);
		product.setProductName(inventory.getProductName());
		product.setProductPrice(inventory.getProductPrice());
		product.setProductCategory(inventory.getProductCategory());
		product.setPromo(inventory.getPromo());
		product.setProductDescription(inventory.getProductDescription());
		product.setBrand(inventory.getProductBrand());
		product.setImageUrl(inventory.getImageUrl());
		
		productDao.delete(product);
	}
	public Product postProductView(Product product) {
		     Product product5=productDao.save(product);
			return product5;

	}

	@Override
	public List<Product> getMerchantProducts(String emailId) {
		Merchant merchant=merchantService.getMerchantByMail(emailId);
		//List<Inventory> inventories=inventoryDao.getAllInventoryByMerchantId(merchant.getMerchantId());
		//productDao
		List<Product> products=getAllProducts();
		List<Product> products1=new ArrayList<Product>();
	
			for(Product product:products)
			{
				if(product.getInventory().getMerchant().getEmailId().equals(emailId))
				{
					products1.add(product);
				}
			}
			System.out.println(products1);
		return products1;
	}
	
	/*@Override
	public List<Product> getProductToUpload() {
		
		return productDao.getProductToUpload();
	}*/
}