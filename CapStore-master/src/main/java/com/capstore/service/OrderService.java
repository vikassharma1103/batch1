package com.capstore.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.ICartDao;
import com.capstore.dao.ICartProductDao;
import com.capstore.dao.IOrderDao;
import com.capstore.model.Cart;
import com.capstore.model.CartProduct;
import com.capstore.model.Order;
import com.capstore.model.Product;
import com.capstore.model.Shipment;

@Service("orderService")
public class OrderService implements IOrderService {

	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IProductService productService;
	@Autowired
	private IShipmentService shipmentService;
	@Autowired
	private ICartDao cartDao;
	@Autowired
	private ICartProductDao cartProductDao;
	

	public OrderService(IOrderDao orderDao, IProductService productService) {
		super();
		this.orderDao = orderDao;
		this.productService = productService;
	}

	@Override
	public List<Product> displayCartProducts(int orderId) {		//display the cart items
		Order order = findOrderById(orderId);
		List<Product> products = new ArrayList<Product>();
		if(order!=null) {
			Cart cart = order.getCart();
			if(cart!=null) {
				List<CartProduct> cartProducts = cart.getCartProducts();
				for(CartProduct cartProduct:cartProducts) {
					products.add(cartProduct.getProduct());
				}
			}
		}
		return products;
	}

	@Override
	public Order findOrderById(int orderId) {		//finding the order
		Optional<Order> optional = orderDao.findById(orderId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public boolean checkAvailabilityInInventory(Order order) {//checking availability of ordered products

		List<CartProduct> products = order.getCart().getCartProducts();

		// check if product is in sufficient quantity
		for (CartProduct orderProduct : products) {
			// fetch product from inventory
			Product inventoryProduct = productService.getProduct(orderProduct.getProduct().getProductId());

			// save orderProduct and inventoryProduct in map
			// inventoryProductsMap.put(orderProduct, inventoryProduct);

			// check quantity
			int orderedQuantity = orderProduct.getQuantity();
			int availableQuantity = inventoryProduct.getQuantity();
			if (orderedQuantity > availableQuantity) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Order placeOrder(Order order) {
		/*System.out.println(order);
		List<Shipment> shipments = order.getShipments();
		for(Shipment shipment:shipments) {
			shipmentService.insertshipment(shipment);
		}
		//System.out.println(order.getCart());
		Cart cart = order.getCart();
		List<CartProduct> cartProducts = cart.getCartProducts();
		for(CartProduct cartProduct:cartProducts) {
			cartProductDao.save(cartProduct);
		}
		cartDao.save(cart);
		//cartService.
		//order.setCart(cart);
		order.setOrderDate(new Date());*/
		Order newOrder = orderDao.save(order);
		return newOrder;
	}

	@Override
	public boolean deliverOrderAndUpdateInventory(Order order) {
		List<CartProduct> products = order.getCart().getCartProducts();
		Map<CartProduct, Product> inventoryProductsMap = new HashMap<>();

		for (CartProduct orderProduct : products) {
			// fetch product from inventory
			Product inventoryProduct = productService.getProduct(orderProduct.getProduct().getProductId());

			// save orderProduct and inventoryProduct in map
			inventoryProductsMap.put(orderProduct, inventoryProduct);
		}

		// update quantity in inventory
		for (Map.Entry<CartProduct, Product> productMap : inventoryProductsMap.entrySet()) {
			// fetch orderProduct
			CartProduct orderProduct = productMap.getKey();
			// fetch inventoryProduct
			Product inventoryProduct = productMap.getValue();

			// quantity
			int orderedQuantity = orderProduct.getQuantity();
			int availableQuantity = inventoryProduct.getQuantity();

			// update quantity
			inventoryProduct.setQuantity(availableQuantity - orderedQuantity);
			productService.updateProduct(inventoryProduct);
		}

		return true;
	}

	@Override
	public List<Order> getOrdersForCustomer(int custId) { // to get orders for a customer
		//System.out.println("Service"+orderDao.getOrdersForCustomer(custId) );
		return orderDao.getOrdersForCustomer(custId) ;
	}
	
	@Override
	public boolean deleteOrder(int orderId) {
		orderDao.deleteById(orderId);
		return true;
	}

	@Override
	public List<Order> getOrdersBetween(Date fromDate, Date toDate) {
		return orderDao.getOrdersBetween(fromDate, toDate);
	}

	


	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderDao.findAll();
	}
}




