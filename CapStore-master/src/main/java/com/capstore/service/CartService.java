package com.capstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.ICartDao;
import com.capstore.dao.ICartProductDao;
import com.capstore.dao.ICustomerDao;
import com.capstore.model.Cart;
import com.capstore.model.CartProduct;
import com.capstore.model.Customer;

@Service("cartService")
public class CartService implements ICartService {

	@Autowired
	ICartDao cartDao;
	
	@Autowired
	ICustomerDao customerDao;
	
	@Autowired
	ICartProductDao cartProductDao;
	
	@Autowired
	IProductService productService;
	
	

	@Override
	public Cart addProductToCart(CartProduct cartProduct, String customerEmailId) {
		
		Customer customer=customerDao.getByEmailId(customerEmailId);

		
		System.out.println("1"+cartProduct);

		System.out.println(customer);
		

		Cart cart=cartDao.findByCustomer(customer.getCustomerId());
		
		if(cart==null)
		{
			
			cart=new Cart();
			
			cart.setCustomer(customer);
			cartProductDao.save(cartProduct);
			
			cart.getCartProducts().add(cartProduct);
			cart.setMinimumAmount(100);
			System.out.println("cart"+cart);
			cartDao.save(cart);
		}
		else
		{
			boolean flag=false;
			List<CartProduct> cartProducts=cart.getCartProducts();
			System.out.println("2"+cartProducts);
			for(CartProduct cartProduct1:cartProducts)
			{
				System.out.println("3"+cartProduct1);
				//if the product already exists
				
				if(cartProduct1.getProduct().getProductId()==(cartProduct.getProduct().getProductId()))
				{
					flag=true;
					return cart;
				}
			}
			if(!flag) {
			//save the product in table if it new product is added
			//cartProductDao.save(cartProduct);
			
			//add the product to cart table
			cart.getCartProducts().add(cartProduct);
			
			cartDao.save(cart);}
			
		}
		return cart;
	}
	
	@Override
	public Cart deleteProductFromCart(String customerEmailId, Integer productId) {
		
		Customer customer=customerDao.getByEmailId(customerEmailId);
		Cart cart=cartDao.findByCustomer(customer.getCustomerId());
		
		if(cart==null)
			return null;
		else
		{
			
			CartProduct cartProduct=cartProductDao.findByProduct(productId,customer.getCustomerId());
			
			//cart.getCartProducts().remove(cartProduct);
			cartProductDao.delete(cartProduct);
			cart=cartDao.findByCustomer(customer.getCustomerId());
			
			return cart;
		}
		
	}
	
	@Override
	public Cart getCartProducts(String customerEmailId) {
		Customer customer=customerDao.getByEmailId(customerEmailId);
		
		Cart cart=cartDao.findByCustomer(customer.getCustomerId());
		return cart;
	}

	@Override
	public Cart updateCartProductQuantity(CartProduct cartProduct, String customerEmailId) {
		
		//Cart cart=cartDao.findByCustomer(customerId);
		Customer customer=customerDao.getByEmailId(customerEmailId);
		
		int quantity=cartProduct.getQuantity();
		
		cartProductDao.updateQuantity(quantity,cartProduct.getProduct().getProductId(),customer.getCustomerId());
		
		 Cart cart=cartDao.findByCustomer(customer.getCustomerId());
		
		return cart;
	}

	@Override
	public double calculateTotalCartAmount(Cart cart) {
		
		if(cart == null) {
			return 0;
		}
		
		double totalAmount=0;
		
		List<CartProduct> cartProducts = cart.getCartProducts();
		
		for(CartProduct cartProduct:cartProducts) {
			
			double price = productService.getDiscountedPrice(cartProduct.getProduct());
			double quantity = (double)cartProduct.getQuantity();
			
			totalAmount += price*quantity;
		}
		
		return totalAmount;
	}
	
	@Override
	public int getCount() {
		return cartProductDao.getCount();
	}



}