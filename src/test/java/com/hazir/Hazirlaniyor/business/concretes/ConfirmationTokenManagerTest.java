package com.hazir.Hazirlaniyor.business.concretes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hazir.Hazirlaniyor.dataAccess.abstracts.ConfirmationTokenDao;
import com.hazir.Hazirlaniyor.entity.concretes.AppUser;
import com.hazir.Hazirlaniyor.entity.concretes.AppUserRole;
import com.hazir.Hazirlaniyor.entity.concretes.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ConfirmationTokenManager.class})
@ExtendWith(SpringExtension.class)
public class ConfirmationTokenManagerTest {
	@MockBean
	private ConfirmationTokenDao confirmationTokenDao;

	@Autowired
	private ConfirmationTokenManager confirmationTokenManager;

	@Test
	public void testSaveConfirmationToken() {
		AppUser appUser = new AppUser ();
		appUser.setLastName ("Doe");
		appUser.setEmail ("jane.doe@example.org");
		appUser.setPassword ("iloveyou");
		appUser.setAppUserRole (AppUserRole.USER);
		appUser.setId (123L);
		appUser.setEnabled (true);
		appUser.setLocked (true);
		appUser.setFirstName ("Jane");

		ConfirmationToken confirmationToken = new ConfirmationToken ();
		confirmationToken.setConfirmedAt (LocalDateTime.of (1, 1, 1, 1, 1));
		confirmationToken.setCreatedAt (LocalDateTime.of (1, 1, 1, 1, 1));
		confirmationToken.setId (123L);
		confirmationToken.setExpiresAt (LocalDateTime.of (1, 1, 1, 1, 1));
		confirmationToken.setToken ("ABC123");
		confirmationToken.setAppUser (appUser);
		when (this.confirmationTokenDao.save ((ConfirmationToken) any ())).thenReturn (confirmationToken);
		this.confirmationTokenManager.saveConfirmationToken (new ConfirmationToken ());
		verify (this.confirmationTokenDao).save ((ConfirmationToken) any ());
	}

	@Test
	public void testGetToken() {
		AppUser appUser = new AppUser ();
		appUser.setLastName ("Doe");
		appUser.setEmail ("jane.doe@example.org");
		appUser.setPassword ("iloveyou");
		appUser.setAppUserRole (AppUserRole.USER);
		appUser.setId (123L);
		appUser.setEnabled (true);
		appUser.setLocked (true);
		appUser.setFirstName ("Jane");

		ConfirmationToken confirmationToken = new ConfirmationToken ();
		confirmationToken.setConfirmedAt (LocalDateTime.of (1, 1, 1, 1, 1));
		confirmationToken.setCreatedAt (LocalDateTime.of (1, 1, 1, 1, 1));
		confirmationToken.setId (123L);
		confirmationToken.setExpiresAt (LocalDateTime.of (1, 1, 1, 1, 1));
		confirmationToken.setToken ("ABC123");
		confirmationToken.setAppUser (appUser);
		Optional<ConfirmationToken> ofResult = Optional.<ConfirmationToken>of (confirmationToken);
		when (this.confirmationTokenDao.findByToken (anyString ())).thenReturn (ofResult);
		Optional<ConfirmationToken> actualToken = this.confirmationTokenManager.getToken ("ABC123");
		assertSame (ofResult, actualToken);
		assertTrue (actualToken.isPresent ());
		verify (this.confirmationTokenDao).findByToken (anyString ());
	}

	@Test
	public void testSetConfirmedAt() {
		when (this.confirmationTokenDao.updateConfirmedAt (anyString (), (java.time.LocalDateTime) any ())).thenReturn (1);
		assertEquals (1, this.confirmationTokenManager.setConfirmedAt ("ABC123"));
		verify (this.confirmationTokenDao).updateConfirmedAt (anyString (), (java.time.LocalDateTime) any ());
	}
}

