package com.capstore.service;

import java.util.Date;
import java.util.List;

import com.capstore.model.CartProduct;
import com.capstore.model.Order;
import com.capstore.model.Product;

public interface IOrderService {

	public List<Product> displayCartProducts(int orderId);

	public boolean checkAvailabilityInInventory(Order order);
	public Order placeOrder(Order order);
	public boolean deliverOrderAndUpdateInventory(Order order);
	
	public Order findOrderById(int orderId);
	public boolean deleteOrder(int orderId);
	
	public List<Order> getOrdersForCustomer( int custId);
	
	public List<Order> getOrdersBetween(Date fromDate, Date toDate);
	
	public List<Order> getAllOrders();

	
	
	
}