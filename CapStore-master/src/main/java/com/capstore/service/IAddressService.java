package com.capstore.service;

import java.util.List;

import com.capstore.model.Address;

public interface IAddressService {
	
	public List<Address> getAllAddresses();
	
	public List<Address> createAddress(Address address,String customerMail);
	
	public Address createAddress(Address address);

	List<Address> updateAddress(Address address);

}
