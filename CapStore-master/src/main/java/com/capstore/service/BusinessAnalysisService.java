package com.capstore.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.model.CartProduct;
import com.capstore.model.DispatchAnalysis;
import com.capstore.model.Invoice;
import com.capstore.model.Order;
import com.capstore.model.Return;
import com.capstore.model.SalesAnalysis;
import com.capstore.model.Shipment;


@Service("businessAnalysisService")
public class BusinessAnalysisService implements IBusinessAnalysisService {

	@Autowired
	private IInvoiceService invoiceService;
	
	@Autowired
	private IReturnService returnService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IMerchantService merchantService;

	
	//Method to retrieve total revenue for the given time period
	@Override
	public double getTotalRevenueBetween(Date fromDate, Date toDate) {
		
		//get invoice details for sales in given period
		List<Invoice> invoiceDetails=invoiceService.getInvoiceDetailsBetweenDates(fromDate, toDate);
		//get product return details, if present, for given period
		List<Return> returnDetails=returnService.getAllReturnDetails();
		double totalRevenue=0;
		boolean flag=false;
		
		//for every sale, see if the order was returned
		for(Invoice invoice:invoiceDetails) {
			for(Return return1:returnDetails) {
				
				//if order returned, avoid the revenue for this order
				if(invoice.getOrder().equals(return1.getOrder()))
				{	flag=true;
					break;
				}
			}
			//else, add to revenue
			if(flag==false)
				totalRevenue+=invoice.getDiscountedPrice();
		}
		
		return totalRevenue;
	}

	
	//method to display product category-wise sales for the given period
	@Override
	public List<SalesAnalysis> getSalesAnalysis(Date fromDate, Date toDate) {
		
		List<SalesAnalysis> salesAnalysisDetails=new ArrayList<>();
		SalesAnalysis salesAnalysis=new SalesAnalysis();
		
		//get orders placed in the given period
		List<Order> orderDetails=orderService.getOrdersBetween(fromDate, toDate);
		
		HashMap<String, Double> purchasePriceDetails=new HashMap<>();
		HashMap<String, Double> salesPriceDetails=new HashMap<>();
		int qty=0;
		double salesPrice=0;
		double purchasePrice=0;
		
		//get total revenue for period
		double totalRevenue=getTotalRevenueBetween(fromDate, toDate);
		
		//get best seller details for the products, category-wise(from PRODUCT table)
		List<Object[]> bestSellerDetails=productService.getBestSellerId();
		System.out.println(bestSellerDetails);
		
		
		//for each product category, check sales details
		for(Object[] object:bestSellerDetails)	{
			String productCategory=(String)object[0];
			salesPrice=0;
			purchasePrice=0;
			purchasePriceDetails.put(productCategory, purchasePrice);
			salesPriceDetails.put(productCategory, salesPrice);
			
			//for each order, check product category and add details to maps
			for(Order order:orderDetails)	{
				
				//get products in this order
				List<CartProduct> products=order.getCart().getCartProducts();
				
				//getting purchase price details for each product ordered
				for(CartProduct product:products)	{
					if(product.getProduct().getProductCategory().equals(productCategory))	{
						purchasePrice=purchasePriceDetails.get(productCategory)+
								(product.getProduct().getQuantity()*product.getProduct().getProductPrice());
						purchasePriceDetails.put(productCategory, purchasePrice);
					}
				}
				
				//getting sales price details for each product ordered
				for(CartProduct product:products)	{
					if(product.getProduct().getProductCategory().equals(productCategory))	{
						qty=product.getQuantity();
						
						salesPrice=salesPriceDetails.get(productCategory)+
								(qty*(100-product.getProduct().getDiscount())*product.getProduct().getProductPrice())/100;
						salesPriceDetails.put(productCategory, salesPrice);
					}
				}
			}
			
			
			//make SalesAnalysis object using obtained data
			salesAnalysis.setProductCategory(productCategory);
			salesAnalysis.setMerchant(merchantService.getMerchantName((Integer)object[1]));
			salesAnalysis.setProductQuantity(purchasePriceDetails.get(productCategory));
			salesAnalysis.setProductSales(salesPriceDetails.get(productCategory));
			salesAnalysis.setSalesPercent((salesAnalysis.getProductSales()*100)/salesAnalysis.getProductQuantity());
			salesAnalysis.setTotalRevenue(totalRevenue);
			
			//make a list of sales analysis performed
			salesAnalysisDetails.add(salesAnalysis);
		}
		
		return salesAnalysisDetails;
	}
	
	//this method retrieves dispatch details for business analysis by admin
	@Override
	public List<DispatchAnalysis> getDispatchDetailsBetween(Date fromDate, Date toDate)	{
		
		List<DispatchAnalysis> dispatchAnalysisDetails=new ArrayList<>();
		DispatchAnalysis dispatchAnalysis=new DispatchAnalysis();
		Calendar c = Calendar.getInstance();
	
		List<Order> orderDetails=orderService.getOrdersBetween(fromDate, toDate);
		System.out.println(orderDetails);
		//for each order placed, get shipment details
		for(Order order:orderDetails)	{
			List<Shipment> shipmentDetails=order.getShipments();
			System.out.println(shipmentDetails);
			
			for(Shipment shipment:shipmentDetails)	{
				
				c.setTime(order.getOrderDate());
				c.add(c.DAY_OF_MONTH, 1);
				
				dispatchAnalysis.setProductName(shipment.getProduct().getProductName());
				dispatchAnalysis.setMerchantName(shipment.getProduct().getInventory().getMerchant().getMerchantName());
				//expected dispatch date of the product is one day after order placed
				dispatchAnalysis.setExpectedDispatchDate(c.getTime());
				dispatchAnalysis.setActualDispatchDate(shipment.getDispatchDate());
				dispatchAnalysis.setDeliveryDate(shipment.getDeliveryDate());
				
				dispatchAnalysisDetails.add(dispatchAnalysis);
			}
		}
		return dispatchAnalysisDetails;
	}
	

}
