package com.hazir.Hazirlaniyor.api.controllers;

import com.hazir.Hazirlaniyor.business.concretes.ShipmentManager;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessResult;
import com.hazir.Hazirlaniyor.entity.concretes.Shipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/shipment")
@CrossOrigin(origins = "http://localhost:3000")

public class ShipmentController {
	private final ShipmentManager shipmentManager;

	@Autowired
	public ShipmentController(ShipmentManager shipmentManager) {
		this.shipmentManager = shipmentManager;
	}


	@GetMapping(path = "/canceled")
	public DataResult<List<Shipment>> getCanceledShipment() {
		return this.shipmentManager.getCanceledShipment ();
	}

	@GetMapping
	public DataResult<List<Shipment>> getAll() {
		return this.shipmentManager.getAllShipments ();
	}

	@GetMapping(path = "{firstName}")
	public DataResult<List<Shipment>> findShipmentByFirstName(@PathVariable("firstName") String firstName) {
		return this.shipmentManager.findShipmentsByFirstName (firstName);
	}

	@PostMapping("/addNewShipment")
	public Result addNewShipment(@RequestBody Shipment shipment) {
		this.shipmentManager.addNewShipment (shipment);
		return new SuccessResult ("basarili bir sekilde eklendi");
	}

	@DeleteMapping(path = "{Id}")
	public void deleteShipment(@PathVariable("Id") Long Id) {
		this.shipmentManager.deleteById (Id);
	}

	@PostMapping("/cancel")
	public Result cancelShipment(@RequestBody Shipment shipment, Long Id) {
		this.shipmentManager.cancelShipment (shipment, Id);
		return new SuccessResult ("basarili bir sekilde iptal edildi");
	}
}

