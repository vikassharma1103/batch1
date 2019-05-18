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

import com.capstore.model.Shipment;
import com.capstore.service.IShipmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ShipmentController {

	@Autowired
	private IShipmentService shipmentService;

	@PostMapping(value = "/shipment")
	public ResponseEntity<Shipment> insertshipment(@RequestBody Shipment shipment) {
		System.out.println("add customer");
		System.out.println(shipment);

		Shipment shipment1 = shipmentService.insertshipment(shipment);

		if (shipment == null)
			return new ResponseEntity("Insertion Failed", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Shipment>(shipment1, HttpStatus.OK);
	}

	@GetMapping(value = "/shipment/{shipmentId}")
	public ResponseEntity<Shipment> getShipment(@PathVariable("shipmentId") Integer shipmentId) {
		Shipment shipment = shipmentService.getShipment(shipmentId);
		if (shipment == null)
			return new ResponseEntity(shipment, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Shipment>(shipment, HttpStatus.OK);
	}

	@GetMapping(value = "/shipment/all")
	public ResponseEntity<List<Shipment>> getAllShipments() {
		List<Shipment> shipments = shipmentService.getAllShipments();
		if (shipments.isEmpty())
			return new ResponseEntity(shipments, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Shipment>>(shipments, HttpStatus.OK);
	}

	@GetMapping(value = "/shipment/undelivered")
	public ResponseEntity<List<Shipment>> getAllUndeliveredShipments() {
		List<Shipment> shipments = shipmentService.getAllUndeliveredShipments();
		if (shipments.isEmpty())
			return new ResponseEntity(shipments, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Shipment>>(shipments, HttpStatus.OK);
	}

	@GetMapping(value = "/shipment/order/{orderId}")
	public ResponseEntity<List<Shipment>> getShipmentsOfOrder(@PathVariable("orderId") Integer orderId) {
		List<Shipment> shipments = shipmentService.getShipmentsOfOrder(orderId);
		if (shipments.isEmpty())
			return new ResponseEntity(shipments, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Shipment>>(shipments, HttpStatus.OK);
	}
	
	@GetMapping(value = "/shipment/customer/{customerId}")
	public ResponseEntity<List<Shipment>> getShipmentsOfCustomer(@PathVariable("customerId") Integer customerId) {
		List<Shipment> shipments = shipmentService.getShipmentsOfCustomer(customerId);
		if (shipments.isEmpty())
			return new ResponseEntity(shipments, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Shipment>>(shipments, HttpStatus.OK);
	}
	
	@PostMapping("/getShipmentDeliveryStatus/{shipmentId}")
	public ResponseEntity<String> getShipmentDeliveryStatus(@PathVariable("shipmentId") Integer shipmentId) throws JsonProcessingException {
		String shipmentDeliveryStatus = shipmentService.getShipmentDeliveryStatus(shipmentId);
		ObjectMapper objectMapper = new ObjectMapper();
		if (shipmentDeliveryStatus != null) {
			return new ResponseEntity<String>(objectMapper.writeValueAsString(shipmentDeliveryStatus), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(objectMapper.writeValueAsString("error"), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/updateShipmentDeliveryStatus/{shipmentId}/{status}")
	public ResponseEntity<Boolean> deliverOrderAndUpdateInventory(@PathVariable("shipmentId") Integer shipmentId,
			@PathVariable("status") String status) {
		if (shipmentService.updateShipmentDeliveryStatus(shipmentId, status)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
	}
}
