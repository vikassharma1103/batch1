package com.capstore.controller;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.dao.IProductImage;
import com.capstore.model.Address;
import com.capstore.model.Order;
import com.capstore.model.ProductImage;
import com.capstore.model.Return;
import com.capstore.service.IOrderService;
import com.capstore.service.IProductImageService;
import com.capstore.service.IProductService;
import com.capstore.service.IReturnService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ReturnGoodsController {

	
	@Autowired
	private IOrderService orderService;

	@Autowired
	private IReturnService returnService;
	
	@Autowired
	private IProductImageService productImageService;
	 
	@GetMapping("/myorders")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders= orderService.getAllOrders();
		
		
		
		if (orders==null) {
			new ResponseEntity("No account found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
	
	@GetMapping("/myorders/{orderId}")
	public ResponseEntity<Integer> addrecordtoreturn(@PathVariable("orderId") int orderid ) {
		System.out.println(orderid+"printing temp");
		 int myret=returnService.addrecordtoreturn(orderid);
		
		 return new ResponseEntity<Integer>(myret, HttpStatus.OK);
		 
	}
	
	
	@GetMapping("/myorders/verify/{orderId}")
	public ResponseEntity<Return> checkstatus(@PathVariable("orderId") int orderid ) {
		System.out.println(orderid+"printing temp");
		 Return myret=returnService.checkstatus(orderid);
		
		 return new ResponseEntity<Return>(myret, HttpStatus.OK);
		 
	}
	
	@GetMapping("/myOrder/verify/{orderId}/refundMoney")
	public ResponseEntity<Boolean> refundMoney(@PathVariable("orderId") int orderId){
		
		boolean refundStatus = returnService.refundMoney(orderId);
		
		return new ResponseEntity<Boolean>(refundStatus, HttpStatus.OK);
	}
	
	
	 
		@GetMapping("/getreturngoods")
		public ResponseEntity<List<Return>> getreturngoods() {
			List<Return> orders= returnService.getreturngoods();
			
	
			return new ResponseEntity<List<Return>>(orders, HttpStatus.OK);
		}
		
	
		@GetMapping("/getimages")
		public ResponseEntity<List<ProductImage>> getimages() {
			List<ProductImage> orders=  productImageService.getimages();
			
	
			return new ResponseEntity<List<ProductImage>>(orders, HttpStatus.OK);
		}
		
		
} 
 
