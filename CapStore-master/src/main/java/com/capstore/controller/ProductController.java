package com.capstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.model.Product;
import com.capstore.model.ProductImage;
import com.capstore.service.IProductService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products=productService.getAllProducts();
		if(products.isEmpty())
			return new ResponseEntity("Sorry! No Products Available!", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	/*@GetMapping("/products/salesAnalysis")
	public ResponseEntity<List<SalesAnalysis>> getSalesAnalysis(){
		//List<SalesAnalysis> salesAnalysis=productService.getSalesAnalysis();
		if(salesAnalysis.isEmpty())
			return new ResponseEntity("Sorry! No Sales is done!", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<SalesAnalysis>>(salesAnalysis, HttpStatus.OK);
	}*/

	@GetMapping("/similarProducts/{productId}")
	public ResponseEntity<List<Product>> getSimilarProducts(@PathVariable("productId") Integer productId){
		
		List<Product> products = productService.getSimilarProducts(productId);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/verifyCoupon/{productId}")
	public ResponseEntity<Double> applyingDiscount( @PathVariable("productId") Integer productId){
		
		Product product = productService.getProduct(productId);
		double discountedPrice = productService.getDiscountedPrice(product);
		
		return new ResponseEntity<Double>(discountedPrice,HttpStatus.OK);
	}
	
	@GetMapping("/{productCategory}/lowtohigh")
	public ResponseEntity<List<Product>> getProductsAsc(@PathVariable String productCategory){
		List<Product> productAsc=productService.getProductsAsc(productCategory);
		if(productAsc.isEmpty())
			return new ResponseEntity("Sorry! Products can't Retrieved!",HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<List<Product>>(productAsc,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/{productCategory}/hightolow")
	public ResponseEntity<List<Product>> getProductsDesc(@PathVariable String productCategory){
		List<Product> productDesc=productService.getProductsDesc(productCategory);
		if(productDesc.isEmpty())
			return new ResponseEntity("Sorry! Products can't Retrieved!",HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<List<Product>>(productDesc,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/{productCategory}/mostViewed")
	public ResponseEntity<List<Product>> getMostViewed(@PathVariable String productCategory){
		List<Product> mostViewed=productService.getMostViewed(productCategory);
		if(mostViewed.isEmpty())
			return new ResponseEntity("Sorry! Products can't Retrieved!",HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<List<Product>>(mostViewed,HttpStatus.OK);
		
		}
	
	
	@GetMapping("/{productCategory}/BestSeller")
	public ResponseEntity<List<Product>> getBestSeller(@PathVariable String productCategory){
		List<Product> bestSeller=productService.getBestSeller(productCategory);
		if(bestSeller.isEmpty())
			return new ResponseEntity("Sorry! Product can't fetch!",HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<List<Product>>(bestSeller,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/{productCategory}")
	public ResponseEntity<List<Product>> getFilteredProducts(@PathVariable String productCategory){
		List<Product> filteredProducts=productService.getFilteredProducts(productCategory);
		if(filteredProducts.isEmpty())
			return new ResponseEntity("Sorry! Product can't fetch!",HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<List<Product>>(filteredProducts,HttpStatus.OK);
		
		
	}
	@GetMapping("/{productCategory}/{min}/{max}")
	public ResponseEntity<List<Product>> getProductsInRange(@PathVariable String productCategory,@PathVariable double min,
			@PathVariable double max){
		List<Product> inRangeProducts=productService.getProductsInRange(productCategory,min,max);
		if(inRangeProducts.isEmpty())
			return new ResponseEntity("Sorry! Product can't fetch!",HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<List<Product>>(inRangeProducts,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/images/{productId}")
	public ResponseEntity<List<ProductImage>> getProductImageId(@PathVariable int productId){
		List<ProductImage> imageId=productService.getProductImageId(productId);
		if(imageId==null)
			return new ResponseEntity("Sorry! ImageId can't fetch!",HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<List<ProductImage>>(imageId,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/mainImage/{productId}")
	public ResponseEntity<ProductImage> getImage(@PathVariable int productId){
	  
	  ProductImage image=productService.getImage(productId);
	  if(image==null)
			return new ResponseEntity("Sorry! ImageId can't fetch!",HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<ProductImage>(image,HttpStatus.OK);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable int productId){
		Product product=productService.getProductfromProductId(productId);
		if(product==null)
			return new ResponseEntity("Sorry! Product can't fetch!",HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	
	@GetMapping("/similarProduct/{brand}/{productCategory}")
	public ResponseEntity<List<Product>> getListofproducts(@PathVariable String brand,@PathVariable String productCategory){
		List<Product> product1=productService.getSimilarProducts(brand,productCategory);
		if(product1.isEmpty()) 
			return new ResponseEntity("Sorry! Product can't fetch!",HttpStatus.NOT_FOUND);
			else
		return new ResponseEntity<List<Product>>(product1,HttpStatus.OK);
	
	}
	
	@PostMapping("/productViewCount/{productId}")
	public ResponseEntity<Product> postProductView(@PathVariable int productId){
		Product product=productService.getProductfromProductId(productId);
		product.setProductView(product.getProductView()+1);
		Product product5= productService.postProductView(product);
		if(product5==null) 
			return new ResponseEntity("Sorry! Product can't fetch!",HttpStatus.NOT_FOUND);
			else
		return new ResponseEntity<Product>(product5,HttpStatus.OK);
		
			
		
	}
	
	
}