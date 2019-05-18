package com.capstore.service;

import java.util.Date;
import java.util.List;

import com.capstore.model.Shipment;

public interface IShipmentService {

	public Shipment insertshipment(Shipment shipment);
	
	public Shipment getShipment(int shipmentId);
	public List<Shipment> getAllShipments();
	public List<Shipment> getAllUndeliveredShipments();
		
	public String getShipmentDeliveryStatus(int shipmentId);
	public boolean updateShipmentDeliveryStatus(int shipmentId, String status);

	List<Shipment> getShipmentsOfOrder(int orderId);
	List<Shipment> getShipmentsOfCustomer(int customerId);
	
}
