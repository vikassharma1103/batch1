package com.capstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.model.Customer;
import com.capstore.model.Product;
import com.capstore.model.Wishlist;
import com.capstore.service.ICustomerService;
import com.capstore.service.IWishlistService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class WishlistController {

	@Autowired
	private IWishlistService wishlistService;

	@Autowired
	private ICustomerService customerService;
	
	@PostMapping("/addingtowishlist/{customerMail}/{productId}")
	public ResponseEntity<Boolean> addToWishlist(@PathVariable("productId") Integer productId,
			@PathVariable("customerMail") String customerEmail ){
		
		Customer customer=customerService.getCustomerByEmail(customerEmail);
		
		Boolean success = wishlistService.addToWishlist(customer.getCustomerId(), productId);
 
		return new ResponseEntity<Boolean>(success,HttpStatus.OK);
	}
	
	@GetMapping("/viewWishlist/{customerMail}")
	public ResponseEntity<List<Product>> wishListForSpecificCustomer(@PathVariable("customerMail") String customerEmail){
	
		Customer customer=customerService.getCustomerByEmail(customerEmail);
		System.out.println(customer);
		List<Product> products = wishlistService.wishListForSpecificCustomer(customer.getCustomerId());
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteFromWishlist/{customerMail}/{productId}")
	public ResponseEntity<List<Product>> deleteFromWishlist(@PathVariable("productId") Integer productId,
			@PathVariable("customerMail") String customerEmail ){
		
		Customer customer=customerService.getCustomerByEmail(customerEmail);
		
		Wishlist wishlist=wishlistService.deleteFromWishlist(customer.getCustomerId(), productId);
		
		List<Product> products=wishlistService.wishListForSpecificCustomer(customer.getCustomerId());
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@DeleteMapping("/moveFromWishlistToCart/{customerMail}/{productId}")
	public ResponseEntity<Boolean> moveFromWishlistToCart(@PathVariable("productId") Integer productId,
			@PathVariable("customerMail") String customerMail ){
		
		Customer customer=customerService.getCustomerByEmail(customerMail);
		Boolean success = wishlistService.moveFromWishlistToCart(customer.getCustomerId(), productId);
		
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}
	
	@GetMapping("/wishlistcount")
	public ResponseEntity<Integer> getCount(){
		int count=wishlistService.getWishlistCount();
				if(count==0)
					return new ResponseEntity("Sorry",HttpStatus.NOT_FOUND);
				return new ResponseEntity<Integer>(count,HttpStatus.OK);
				
		
	}

}