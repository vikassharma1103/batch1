package com.capstore.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstore.model.Invoice;
import com.capstore.model.Shipment;

@Repository("invoiceDao")
@Transactional
public interface IInvoiceDao extends JpaRepository<Invoice,Integer> {
	
	@Query("from Invoice where InvoiceDate between :fromDate and :toDate")
	public List<Invoice> getInvoiceDetailsBetweenDates(Date fromDate, Date toDate);

}
