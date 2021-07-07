package com.hazir.Hazirlaniyor.business.concretes;

import com.hazir.Hazirlaniyor.business.abstracts.Facade;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessDataResult;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.AppUserDao;
import com.hazir.Hazirlaniyor.entity.concretes.AppUser;
import com.hazir.Hazirlaniyor.entity.concretes.ConfirmationToken;
import com.hazir.Hazirlaniyor.entity.concretes.Product;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserManager implements UserDetailsService {

	private final static String USER_NOT_FOUND_MSG =
			"user with email %s not found";

	private final AppUserDao appUserRepository;
	private final BCryptPasswordEncoder    bCryptPasswordEncoder;
	private final ConfirmationTokenManager confirmationTokenService;
	private final Facade facade;
	private final EmailValidatorManager emailValidatorManager;



	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		return appUserRepository.findByEmail (email)
				.orElseThrow (() ->
						new UsernameNotFoundException (
								String.format (USER_NOT_FOUND_MSG, email)));
	}

	public String signUpUser(AppUser appUser) {
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		boolean checkIfEmailValid =  this.emailValidatorManager.test (appUser.getEmail());
 		if(userExists) {
     throw new IllegalStateException ("There is a user with this email please try again");
		}
 		if(!checkIfEmailValid){
    throw new IllegalStateException ("Email is not valid");
	  }

		String encodedPassword = bCryptPasswordEncoder
				.encode (appUser.getPassword ());

		appUser.setPassword (encodedPassword);

		appUserRepository.save (appUser);

		String token = UUID.randomUUID ().toString ();

		ConfirmationToken confirmationToken = new ConfirmationToken (
				token,
				LocalDateTime.now (),
				LocalDateTime.now ().plusMinutes (15),
				appUser
		);

		confirmationTokenService.saveConfirmationToken (
				confirmationToken);

//        TODO: SEND EMAIL

		return token;
	}

	public int updatePassword(String password, String email) {

		String encodedPassword = bCryptPasswordEncoder
				.encode (password);

		return appUserRepository.updatePassword (encodedPassword,email);
	}

	public int enableAppUser(String email) {
		return appUserRepository.enableAppUser(email);
	}

	public boolean checkIfEmailExists(String email) {
		boolean checkIfEmailExists = this.appUserRepository.existsEmail(email);

		return checkIfEmailExists;
	}

	public DataResult<List<AppUser>> getAllUsers() {
		return new SuccessDataResult<List<AppUser>>
				(this.appUserRepository.findAll (), "Data listelendi");
	}
}
