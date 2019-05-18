package com.capstore.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IInvoiceDao;
import com.capstore.model.Invoice;
import com.capstore.model.Order;

@Service("invoiceService")
public class InvoiceService implements IInvoiceService{

	@Autowired
	IInvoiceDao invoiceDao;
	
	@Autowired
	IOrderService orderService;
	
	@Override
	public Invoice getInvoiceFromOrderId(int OrderId) {
		
		Order myOrder = orderService.findOrderById(OrderId);
		
		List<Invoice> invoices = invoiceDao.findAll();
		
		for(Invoice myInvoice: invoices) {
			if(myInvoice.getOrder().equals(myOrder)) {
				return myInvoice;
			}
		}
		return null;
	}

	@Override
	public boolean generateInvoice(Invoice invoice) {
		
		invoiceDao.save(invoice);
		
		return true;
	}

	@Override
	public List<Invoice> getInvoiceDetailsBetweenDates(Date fromDate, Date toDate) {
		List<Invoice> invoiceDetails=invoiceDao.getInvoiceDetailsBetweenDates(fromDate, toDate);
		return invoiceDetails;
	}
}