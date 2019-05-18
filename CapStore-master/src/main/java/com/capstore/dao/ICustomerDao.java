package com.capstore.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstore.model.Customer;

@Repository("customerDao")
@Transactional
public interface ICustomerDao extends JpaRepository<Customer,Integer> {


	Customer getByEmailId(String customerEmail);

	@Query("from Customer where customer_id=:customerId")
	Customer getByCustomer(@Param("customerId") Integer custId);
	
	
//	public List<Customer> getAllCustomers();



}
