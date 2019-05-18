package com.capstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.model.Invoice;
import com.capstore.service.IInvoiceService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class InvoiceController {
	
	
	@Autowired
	private IInvoiceService invoiceService;
	
	/*@PostMapping(value = "/invoice")
	public ResponseEntity<Invoice> insertShipment(@RequestBody Invoice invoice) {
		System.out.println("add customer");
		System.out.println(invoice);
		
		Invoice invoice1=invoiceService.insertInvoice(invoice);
		
		if(invoice==null)
			return new ResponseEntity("Insertion Failed",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Invoice>(invoice1,HttpStatus.OK);
	}*/
	
	@GetMapping(value = "/getInvoice/{OrderId}")
	public ResponseEntity<Invoice> getInvoice(@PathVariable("OrderId") Integer orderId){
		
		Invoice requiredInvoice = invoiceService.getInvoiceFromOrderId(orderId);
		
		return new ResponseEntity<Invoice>(requiredInvoice, HttpStatus.OK);
		
	}
}