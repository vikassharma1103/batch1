package com.capstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IAddressDao;
import com.capstore.dao.ICustomerDao;
import com.capstore.model.Address;
import com.capstore.model.Customer;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service("addressService")
public class AddressService implements IAddressService{

	@Autowired

	IAddressDao addressDao;
	@Autowired
	
	ICustomerDao customerDao;

	//Get all addresses
	
	@Override
	public List<Address> getAllAddresses() {

		List<Address> add=addressDao.findAll();
		System.out.println("Service"+add);
		return addressDao.findAll();

	}

	
	@Override
	public List<Address> createAddress(Address address ,String customerMail) {
		
		List<Customer> customers=customerDao.findAll();
		Customer customer=customerDao.getByEmailId(customerMail);
		
		
		customer=customerDao.getByEmailId(customerMail);
		//addressDao.save(address) ;
		customer.getAddresses().add(address);
		//System.out.println(customer);
		customerDao.save(customer);
		
		
		 return customer.getAddresses();
	}
	
	
	@Override
	public Address createAddress(Address address) {
		
		return addressDao.save(address) ;
	}

		@Override
		public List<Address> updateAddress(Address address) {
		addressDao.save(address);
		
		return addressDao.findAll();
		}

	

}