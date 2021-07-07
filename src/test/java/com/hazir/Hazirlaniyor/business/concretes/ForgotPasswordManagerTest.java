package com.hazir.Hazirlaniyor.business.concretes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hazir.Hazirlaniyor.business.abstracts.AfterPaymentSuccessDal;
import com.hazir.Hazirlaniyor.business.abstracts.Facade;
import com.hazir.Hazirlaniyor.business.abstracts.ProductCheckerService;
import com.hazir.Hazirlaniyor.business.abstracts.RegistrationDal;
import com.hazir.Hazirlaniyor.business.abstracts.ValidatorDal;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.AppUserDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.CartDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.ConfirmationTokenDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.ForgotPasswordDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.PaymentSuccessDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.ProductDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.ShipmentDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.UpdatePasswordTokenDao;
import com.hazir.Hazirlaniyor.entity.concretes.AppUser;
import com.hazir.Hazirlaniyor.entity.concretes.AppUserRole;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UpdatePasswordManager.class, PasswordEncoder.class, Facade.class,
		ForgotPasswordEmailManager.class, AppUserManager.class, ForgotPasswordManager.class})
@ExtendWith(SpringExtension.class)
public class ForgotPasswordManagerTest {
	@MockBean
	private AppUserDao appUserDao;

	@MockBean
	private AppUserManager appUserManager;

	@MockBean
	private Facade facade;

	@MockBean
	private ForgotPasswordDao forgotPasswordDao;

	@MockBean
	private ForgotPasswordEmailManager forgotPasswordEmailManager;

	@Autowired
	private ForgotPasswordManager forgotPasswordManager;

	@MockBean
	private PasswordEncoder passwordEncoder;

	@MockBean
	private UpdatePasswordManager updatePasswordManager;

	@MockBean
	private UpdatePasswordTokenDao updatePasswordTokenDao;

	@Test
	public void testRequestForResetingPassword() {
		AppUser appUser = new AppUser ();
		appUser.setLastName ("Doe");
		appUser.setEmail ("nailmemmedova12@gmail.com");
		appUser.setPassword ("iloveyou");
		appUser.setAppUserRole (AppUserRole.USER);
		appUser.setId (123L);
		appUser.setEnabled (true);
		appUser.setLocked (true);
		appUser.setFirstName ("Jane");
		AppUserDao appUserDao = mock (AppUserDao.class);
		when (appUserDao.getUserByEmail (anyString ())).thenReturn (Optional.<AppUser>of (appUser));
		ShipmentDao shipmentDao = mock (ShipmentDao.class);
		ProductDao productDao = mock (ProductDao.class);
		CartDao cartDao = mock (CartDao.class);
		StripeManager paymentsService = new StripeManager ();
		PaymentSuccessDao paymentSuccessDao = mock (PaymentSuccessDao.class);
		ShipmentManager shipmentService = new ShipmentManager (shipmentDao, productDao, cartDao, paymentsService,
				paymentSuccessDao, new EmailManager (null));

		PaymentEmailManager paymentEmailManager = new PaymentEmailManager (new JavaMailSenderImpl ());
		ShipmentManager shipmentService1 = new ShipmentManager (mock (ShipmentDao.class), mock (ProductDao.class),
				mock (CartDao.class), null, mock (PaymentSuccessDao.class), null);

		AfterPaymentSuccessDal afterPaymentSuccessService = new AfterPaymentSuccessDal (shipmentService1,
				new PaymentEmailManager (null), mock (CartDao.class), mock (ProductDao.class));

		CartDao cartDao1 = mock (CartDao.class);
		ProductDao productDao1 = mock (ProductDao.class);
		UpdatePasswordTokenDao updatePasswordTokenDao = mock (UpdatePasswordTokenDao.class);
		ValidatorDal validatorService = new ValidatorDal (mock (AppUserDao.class), mock (UpdatePasswordTokenDao.class));

		ProductCheckerService productCheckerDal = mock (ProductCheckerService.class);
		Facade facade = new Facade (shipmentService, paymentEmailManager, afterPaymentSuccessService, cartDao1, productDao1,
				appUserDao, updatePasswordTokenDao, validatorService, productCheckerDal, new RegistrationDal ());

		ForgotPasswordDao forgotPasswordDao = mock (ForgotPasswordDao.class);
		UpdatePasswordManager updatePasswordManager = new UpdatePasswordManager (mock (UpdatePasswordTokenDao.class));
		AppUserDao appUserDao1 = mock (AppUserDao.class);
		ForgotPasswordEmailManager emailManager = new ForgotPasswordEmailManager (new JavaMailSenderImpl ());
		AppUserDao appUserRepository = mock (AppUserDao.class);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder ();
		ConfirmationTokenManager confirmationTokenService = new ConfirmationTokenManager (mock (ConfirmationTokenDao.class));
		ShipmentManager shipmentService2 = new ShipmentManager (mock (ShipmentDao.class), mock (ProductDao.class),
				mock (CartDao.class), null, mock (PaymentSuccessDao.class), null);

		PaymentEmailManager paymentEmailManager1 = new PaymentEmailManager (null);
		AfterPaymentSuccessDal afterPaymentSuccessService1 = new AfterPaymentSuccessDal (null, null, mock (CartDao.class),
				mock (ProductDao.class));

		CartDao cartDao2 = mock (CartDao.class);
		ProductDao productDao2 = mock (ProductDao.class);
		AppUserDao appUserDao2 = mock (AppUserDao.class);
		UpdatePasswordTokenDao updatePasswordTokenDao1 = mock (UpdatePasswordTokenDao.class);
		ValidatorDal validatorService1 = new ValidatorDal (mock (AppUserDao.class), mock (UpdatePasswordTokenDao.class));

		ProductCheckerService productCheckerDal1 = mock (ProductCheckerService.class);
		Facade facade1 = new Facade (shipmentService2, paymentEmailManager1, afterPaymentSuccessService1, cartDao2,
				productDao2, appUserDao2, updatePasswordTokenDao1, validatorService1, productCheckerDal1,
				new RegistrationDal ());

		AppUserManager appUserManager = new AppUserManager (appUserRepository, bCryptPasswordEncoder,
				confirmationTokenService, facade1, new EmailValidatorManager ());

		UpdatePasswordTokenDao updatePasswordTokenDao2 = mock (UpdatePasswordTokenDao.class);
		(new ForgotPasswordManager (forgotPasswordDao, facade, updatePasswordManager, appUserDao1, emailManager,
				appUserManager, updatePasswordTokenDao2, new PasswordEncoder ()))
				.requestForResetingPassword ("nailmemmedova12@gmail.com");
		verify (appUserDao).getUserByEmail (anyString ());
	}

	@Test
	public void testSendSuccessEmail() {
		doNothing ().when (this.forgotPasswordEmailManager).send (anyString (), anyString ());
		this.forgotPasswordManager.sendSuccessEmail ("jane.doe@example.org");
		verify (this.forgotPasswordEmailManager).send (anyString (), anyString ());
	}

	@Test
	public void testBuildEmail() {
		assertEquals ("<!DOCTYPE html>\n"
				+ "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">"
				+ "\n" + "<head>\n" + "\t<meta charset=\"UTF-8\">\n"
				+ "\t<meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n"
				+ "\t<meta name=\"x-apple-disable-message-reformatting\">\n" + "\t<title></title>\n" + "\t<!--[if mso]>\n"
				+ "\t<noscript>\n" + "\t\t<xml>\n" + "\t\t\t<o:OfficeDocumentSettings>\n"
				+ "\t\t\t\t<o:PixelsPerInch>96</o:PixelsPerInch>\n" + "\t\t\t</o:OfficeDocumentSettings>\n" + "\t\t</xml>\n"
				+ "\t</noscript>\n" + "\t<![endif]-->\n" + "\t<style>\n"
				+ "\t\ttable, td, div, h1, p {font-family: Arial, sans-serif;}\n" + "\t</style>\n" + "</head>\n"
				+ "<body style=\"margin:0;padding:0;\">\n"
				+ "\t<table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0"
				+ ";background:#ffffff;\">\n" + "\t\t<tr>\n" + "\t\t\t<td align=\"center\" style=\"padding:0;\">\n"
				+ "\t\t\t\t<table role=\"presentation\" style=\"width:602px;border-collapse:collapse;border:1px solid"
				+ " #cccccc;border-spacing:0;text-align:left;\">\n" + "\t\t\t\t\t<tr>\n"
				+ "\t\t\t\t\t\t<td align=\"center\" style=\"padding:40px 0 30px 0;background:#70bbd9;\">\n"
				+ "\t\t\t\t\t\t\t<img src=\"data:image/jpeg;base64,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\""
				+ " alt=\"\" width=\"300\" style=\"height:auto;display:block;\" />\n" + "\t\t\t\t\t\t</td>\n"
				+ "\t\t\t\t\t</tr>\n" + "\t\t\t\t\t<tr>\n" + "\t\t\t\t\t\t<td style=\"padding:36px 30px 42px 30px;\">\n"
				+ "\t\t\t\t\t\t\t<table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">"
				+ "\n" + "\t\t\t\t\t\t\t\t<tr>\n" + "\t\t\t\t\t\t\t\t\t<td style=\"padding:0 0 36px 0;color:#153643;\">\n"
				+ "\t\t\t\t\t\t\t\t\t\t<h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">Forgot"
				+ " Password?</h1>\n"
				+ "\t\t\t\t\t\t\t\t\t\t<p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">İf"
				+ " You Forgot Password Click To Link</p>\n"
				+ "\t\t\t\t\t\t\t\t\t\t<p style=\"margin:0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\"><a href=\"Link\""
				+ " style=\"color:#ee4c50;text-decoration:underline;\">Forgot Password</a></p>\n" + "\t\t\t\t\t\t\t\t\t\n"
				+ "</body>\n" + "</html>", this.forgotPasswordManager.buildEmail ("Link"));
	}
}

