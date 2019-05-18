package com.capstore.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstore.model.Address;
import com.capstore.model.Customer;
import com.capstore.model.Shipment;

@Repository("addressDao")
@Transactional
public interface IAddressDao extends JpaRepository<Address,Integer> {

	
}
