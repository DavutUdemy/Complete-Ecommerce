package com.hazir.Hazirlaniyor.api.controllers;

import com.hazir.Hazirlaniyor.business.concretes.PaymentSuccessManager;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.entity.concretes.ChargeParameter;
import com.hazir.Hazirlaniyor.entity.concretes.Contact;
import com.stripe.exception.StripeException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})

public class PaymentSuccessController {
	private final PaymentSuccessManager paymentSuccessManager;

	@PostMapping("charge")
	public Result charge(@RequestBody Contact contact, ChargeParameter chargeParameter) throws StripeException {
		return this.paymentSuccessManager.charge (contact, chargeParameter);
	}

	@ExceptionHandler(StripeException.class)
	public String handleError(Model model, StripeException ex) {
		model.addAttribute ("error", ex.getMessage ());
		return "result";
	}
}
