package com.hazir.Hazirlaniyor.business.abstracts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hazir.Hazirlaniyor.business.concretes.PaymentEmailManager;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.CartDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.ProductDao;
import com.hazir.Hazirlaniyor.entity.concretes.Cart;
import com.hazir.Hazirlaniyor.entity.concretes.ProductCategory;
import com.hazir.Hazirlaniyor.entity.concretes.Shipment;
import com.hazir.Hazirlaniyor.entity.concretes.SuccessEmail;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AfterPaymentSuccessDal.class})
@ExtendWith(SpringExtension.class)
public class AfterPaymentSuccessDalTest {
	@Autowired
	private AfterPaymentSuccessDal afterPaymentSuccessDal;

	@MockBean
	private CartDao cartDao;

	@MockBean
	private PaymentEmailManager paymentEmailManager;

	@MockBean
	private ProductDao productDao;

	@MockBean
	private ShipmentService shipmentService;

	@Test
	public void testUpdateStock() {
		when (this.cartDao.findCartByEmail (anyString ())).thenReturn (new ArrayList<Cart> ());
		this.afterPaymentSuccessDal.updateStock ("jane.doe@example.org");
		verify (this.cartDao).findCartByEmail (anyString ());
	}

	@Test
	public void testUpdateStock2() {
		when (this.productDao.unitsInStock ((Integer) any (), anyString ())).thenReturn (1);

		Cart cart = new Cart ();
		cart.setProductImage1 ("Product Image1");
		cart.setEmail ("jane.doe@example.org");
		cart.setProductImage2 ("Product Image2");
		cart.setProductPrice (0);
		cart.setQuantity (0);
		cart.setProductImage3 ("Product Image3");
		cart.setId (123L);
		cart.setProductName ("Product Name");
		cart.setProductCategory (ProductCategory.Electronic);
		cart.setProductDescription ("Product Description");
		cart.setProductImage ("Product Image");
		cart.setTotalPrice (0);

		ArrayList<Cart> cartList = new ArrayList<Cart> ();
		cartList.add (cart);
		when (this.cartDao.findCartByEmail (anyString ())).thenReturn (cartList);
		this.afterPaymentSuccessDal.updateStock ("jane.doe@example.org");
		verify (this.productDao).unitsInStock ((Integer) any (), anyString ());
		verify (this.cartDao).findCartByEmail (anyString ());
	}

	@Test
	public void testSendSuccessEmail() {
		doNothing ().when (this.paymentEmailManager).send (anyString (), anyString ());
		this.afterPaymentSuccessDal
				.sendSuccessEmail (new SuccessEmail ("jane.doe@example.org", "jane.doe@example.org", "Jane", "42"));
		verify (this.paymentEmailManager).send (anyString (), anyString ());
	}

	@Test
	public void testSendSuccessEmail2() {
		doNothing ().when (this.paymentEmailManager).send (anyString (), anyString ());
		SuccessEmail successEmail = mock (SuccessEmail.class);
		when (successEmail.getChargeId ()).thenReturn ("foo");
		when (successEmail.getFirstName ()).thenReturn ("foo");
		when (successEmail.getUserEmail ()).thenReturn ("foo");
		this.afterPaymentSuccessDal.sendSuccessEmail (successEmail);
		verify (this.paymentEmailManager).send (anyString (), anyString ());
		verify (successEmail).getChargeId ();
		verify (successEmail).getFirstName ();
		verify (successEmail).getUserEmail ();
	}



	@Test
	public void testPostNewShipment() {
		when (this.shipmentService.addNewShipment ((Shipment) any ())).thenReturn (new Result (true));
		this.afterPaymentSuccessDal.postNewShipment (new Shipment ());
		verify (this.shipmentService).addNewShipment ((Shipment) any ());
	}

	@Test
	public void testDeleteCartByEmail() {
		when (this.cartDao.deleteByemail (anyString ())).thenReturn (1);
		this.afterPaymentSuccessDal.deleteCartByEmail ("jane.doe@example.org");
		verify (this.cartDao).deleteByemail (anyString ());
	}
}

