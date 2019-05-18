package com.capstore.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.ICustomerDao;
import com.capstore.model.Address;
import com.capstore.model.Customer;

@Service("customerService")
public class CustomerService implements ICustomerService{
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	public boolean createCustomer(Customer customer) {
		customerDao.save(customer);
		return true;
	}

	@Override
	public Customer getCustomerByEmail(String customerEmail) {
		return customerDao.getByEmailId(customerEmail);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.findAll();
	}

	@Override
	public Customer getCustomerByCustomerId(int customerId) {
		Optional<Customer> optional = customerDao.findById(customerId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Customer> deleteCustomer(int customerId) {
		customerDao.deleteById(customerId);
		return customerDao.findAll();
	}

	@Override
	public List<Address> getAddressesOfCustomer(String customerMail) {
		Customer customer=customerDao.getByEmailId(customerMail);
		List<Address> addresses=customer.getAddresses();
		return addresses;
	}
	
	
	@Override
	public Boolean updateMobile(Customer customer) {
	
	customerDao.save(customer);

	return true;
	}

	
}
