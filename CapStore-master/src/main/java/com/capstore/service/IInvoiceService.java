package com.capstore.service;

import java.util.Date;
import java.util.List;

import com.capstore.model.Invoice;

public interface IInvoiceService {

	public boolean generateInvoice(Invoice invoice);
	public Invoice getInvoiceFromOrderId(int OrderId);
	public List<Invoice> getInvoiceDetailsBetweenDates(Date fromDate, Date toDate);

}