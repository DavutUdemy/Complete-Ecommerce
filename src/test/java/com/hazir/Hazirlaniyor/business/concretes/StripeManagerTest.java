package com.hazir.Hazirlaniyor.business.concretes;

import com.hazir.Hazirlaniyor.entity.concretes.ChargeRequest;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StripeManager.class})
@ExtendWith(SpringExtension.class)
public class StripeManagerTest {
	@Autowired
	private StripeManager stripeManager;

	@Test
	public void testCharge()
			throws APIConnectionException, APIException, AuthenticationException, CardException, InvalidRequestException {
		// TODO: This test is incomplete.
		//   Reason: Attempting to run the method under test violated the sandboxing policy.
		//
		//   See https://diff.blue/R011

		this.stripeManager
				.charge (new ChargeRequest ("The characteristics of someone or something", ChargeRequest.Currency.GEL));
	}

	@Test
	public void testCharge2()
			throws APIConnectionException, APIException, AuthenticationException, CardException, InvalidRequestException {
		// TODO: This test is incomplete.
		//   Reason: Attempting to run the method under test violated the sandboxing policy.
		//
		//   See https://diff.blue/R011

		this.stripeManager.charge (new ChargeRequest ("amount", ChargeRequest.Currency.GEL));
	}

	@Test
	public void testConstructor() {
		// TODO: This test is incomplete.
		//   Reason: Nothing to assert: the constructed class does not have observers (e.g. getters or public fields).
		//   Add observers (e.g. getters or public fields) to the class.
		//   See https://diff.blue/R002

		new StripeManager ();
	}
}

