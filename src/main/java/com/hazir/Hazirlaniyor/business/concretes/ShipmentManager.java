package com.hazir.Hazirlaniyor.business.concretes;

import com.hazir.Hazirlaniyor.business.abstracts.ShipmentService;
import com.hazir.Hazirlaniyor.core.utillities.BadRequest.BadRequestException;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.ErrorResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessDataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessResult;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.CartDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.PaymentSuccessDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.ProductDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.ShipmentDao;
import com.hazir.Hazirlaniyor.entity.concretes.Shipment;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ShipmentManager implements ShipmentService {
	private final ShipmentDao       shipmentDao;
	private final ProductDao        productDao;
	private final CartDao           cartDao;
	private final StripeManager     paymentsService;
	private final PaymentSuccessDao paymentSuccessDao;
	private final EmailManager      emailSender;


	@Override
	public Result addNewShipment(Shipment shipment) {
		this.shipmentDao.save (shipment);
		return new SuccessResult ("Successfully, Added item to Shipment Section");
	}

	@Override
	public Result deleteById(Long Id) {
		this.shipmentDao.deleteById (Id);
		return new SuccessResult ("Successfully, Deleted item from Shipment Section");
	}

	@Override
	public DataResult<List<Shipment>> getAllShipments() {
		return new SuccessDataResult<List<Shipment>>
				(this.shipmentDao.findAll (), "Data listelendi");

	}

	@Override
	public DataResult<List<Shipment>> findShipmentsByFirstName(String firstName) {
		return new SuccessDataResult<List<Shipment>>
				(this.shipmentDao.findShipmentByName (firstName), "Data listelendi");
	}

	@Override
	public DataResult<List<Shipment>> getCanceledShipment() {
		return new SuccessDataResult<List<Shipment>>
				(this.shipmentDao.findShipmentByCanceledOrder (false), "Data listelendi");
	}

	public ErrorResult showError(String msg){
		return new ErrorResult(msg);
	}
	@Override
	public Result cancelShipment(Shipment shipment, Long Id) {
		LocalDateTime expiresDate = shipment.getPaymentDate ().plusDays (4);

		if (expiresDate.isBefore (LocalDateTime.now ())) {
			this.showError ("You can not cancel your order");
		}
		this.shipmentDao.cancelOrder (Id);
		return new SuccessResult ("Successfully,Your Order Canceled");
	}


}
