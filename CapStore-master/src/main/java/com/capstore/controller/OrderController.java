package com.capstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.capstore.model.Order;
import com.capstore.model.Product;
import com.capstore.service.IOrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@GetMapping("/displayCartProducts/{orderId}")//error
	public ResponseEntity<List<Product>> displayCartProducts(HttpSession session,
			@PathVariable("orderId") Integer orderId) {
		List<Product> mycart = orderService.displayCartProducts(orderId);
		return new ResponseEntity<List<Product>>(mycart, HttpStatus.OK);
	}

	@GetMapping("/findOrderById/{orderId}")
	public ResponseEntity<Order> findOrderById(@PathVariable("orderId") Integer orderId) {
		Order order = orderService.findOrderById(orderId);
		if (order == null)
			return new ResponseEntity("Sorry! Product is not available!", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@PostMapping("/checkAvailabilityInInventory")//
	public ResponseEntity<Boolean> checkAvailabilityInInventory(@RequestBody Order order) {
		if (orderService.checkAvailabilityInInventory(order)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("/placeOrder")//
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
		Order nullOrder = null;
		if (!orderService.checkAvailabilityInInventory(order)) {
			return new ResponseEntity<Order>(nullOrder, HttpStatus.OK);
		}
		Order newOrder = orderService.placeOrder(order);
		if (newOrder!=null) {
			return new ResponseEntity<Order>(newOrder, HttpStatus.OK);
		} else {
			return new ResponseEntity<Order>(nullOrder, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/deliverOrderAndUpdateInventory")//
	public ResponseEntity<Boolean> deliverOrderAndUpdateInventory(@RequestBody Order order) {
		if (orderService.deliverOrderAndUpdateInventory(order)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getorders/{custId}")//
	public ResponseEntity<List<Order>> displayAllOrder(HttpSession session, @PathVariable("custId") Integer custId) {
		List<Order> myorder = new ArrayList<Order>();
	
			myorder = orderService.getOrdersForCustomer(custId);

		return new ResponseEntity<List<Order>>(myorder, HttpStatus.OK);

	}
}