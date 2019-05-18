package com.capstore.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstore.model.Product;
import com.capstore.model.Shipment;

@Repository("shipmentdao")
@Transactional
public interface IShipmentDao extends JpaRepository<Shipment,Integer> {
	
	//public Shipment insertshipment(Shipment shipment)

	public List<Shipment> getShipmentsByDeliveryStatus(String deliveryStatus);

}
