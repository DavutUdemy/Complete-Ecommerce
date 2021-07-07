package com.hazir.Hazirlaniyor.business.abstracts;

import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.entity.concretes.Shipment;

import java.util.List;

public interface ShipmentService {
	Result addNewShipment(Shipment shipment);
	Result deleteById(Long Id);
	DataResult<List<Shipment>> getAllShipments();
	DataResult<List<Shipment>> findShipmentsByFirstName(String firstName);
	DataResult<List<Shipment>> getCanceledShipment ();
	Result cancelShipment(Shipment shipment,Long  Id);


}
