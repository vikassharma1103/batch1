package com.capstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IShipmentDao;
import com.capstore.model.Order;
import com.capstore.model.Shipment;

@Service("shipmentService")
public class ShipmentService implements IShipmentService {

	@Autowired
	IShipmentDao shipmentdao;
	
	@Autowired
	IOrderService orderService;

	@Override
	public Shipment insertshipment(Shipment shipment) {
		return shipmentdao.save(shipment);
	}

	@Override
	public Shipment getShipment(int shipmentId) {// Team-6
		Optional<Shipment> optional = shipmentdao.findById(shipmentId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	public List<Shipment> getAllShipments() {
		return shipmentdao.findAll();
	}

	@Override
	public List<Shipment> getAllUndeliveredShipments() {
		return shipmentdao.getShipmentsByDeliveryStatus("delivered");
	}
	
	@Override
	public List<Shipment> getShipmentsOfOrder(int orderId) {
		Order order = orderService.findOrderById(orderId);
		return order.getShipments();
	}

	@Override
	public List<Shipment> getShipmentsOfCustomer(int customerId) {
		List<Order> orders = orderService.getOrdersForCustomer(customerId);
		List<Shipment> shipments = new ArrayList<Shipment>();
		for(Order order:orders) {
			shipments.addAll(order.getShipments());
		}
		return shipments;
	}

	@Override
	public String getShipmentDeliveryStatus(int shipmentId) {// Team-6
		Shipment shipment = getShipment(shipmentId);
		if (shipment != null) {
			return shipment.getDeliveryStatus();
		}
		return null;
	}

	@Override
	public boolean updateShipmentDeliveryStatus(int shipmentId, String status) {// Team-6
		Shipment shipment = getShipment(shipmentId);
		if (shipment != null) {
			shipment.setDeliveryStatus(status);
			shipmentdao.save(shipment);
			return true;
		}
		return false;
	}

}
