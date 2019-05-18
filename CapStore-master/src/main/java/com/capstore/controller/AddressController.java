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

import com.capstore.model.Address;
import com.capstore.service.IAddressService;
import com.capstore.service.ICustomerService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class AddressController {
	// To get All addresses from DB
	
	@Autowired
	IAddressService addressService;
	
	@Autowired
	ICustomerService customerService;
	
	@GetMapping(value = "/address/all")
	public ResponseEntity<List<Address>> getAllAddress() {
		System.out.println("");
		List<Address> list = addressService.getAllAddresses();
		System.out.println("Controller"+list);
		if (list==null) {
			new ResponseEntity("No address found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Address>>(list, HttpStatus.OK);
	}

	 // Get all address of a customer 
	@GetMapping(value = "/address/{customerMail}")
	public ResponseEntity<List<Address>> getAllAddressOfCustomer(@PathVariable("customerMail") String customerMail) {
		System.out.println("");
		
		List<Address> list = customerService.getAddressesOfCustomer(customerMail);
		System.out.println("Controller"+list);
		if (list==null) {
			new ResponseEntity("No address found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Address>>(list, HttpStatus.OK);
	}


	

	@PostMapping(value = "/address/{customerMail}")
	public ResponseEntity<List<Address>> createAddress(@RequestBody Address address ,@PathVariable("customerMail") String customerMail) {
		System.out.println(address+"i am here");
		
		List<Address> address1 = addressService.createAddress(address,customerMail);
		
		System.out.println("Controller"+address);
		if (address1.isEmpty()) {
			new ResponseEntity("No address found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Address>>(address1, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/updateAddresses")
	public ResponseEntity<List<Address>> updateAddress(@RequestBody Address address){
	System.out.println("in sts");
	
	List<Address> addresses= addressService.updateAddress(address);
	if(addresses.isEmpty())
	return new ResponseEntity
	("invalid insertion", HttpStatus.NOT_FOUND);
	return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
	} 
}

//	@PostMapping(value = "/address")
//	public ResponseEntity<Address> createAddress(@RequestBody Address address) {
//		//System.out.println(address+"i am here");
//		Address address1 = addressService.createAddress(address);
//		//System.out.println("Controller"+address);
//		if (address==null) {
//			new ResponseEntity("No account found", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<List<Address>>(address1, HttpStatus.OK);
//	}
//		
//
//	}	

		

		

