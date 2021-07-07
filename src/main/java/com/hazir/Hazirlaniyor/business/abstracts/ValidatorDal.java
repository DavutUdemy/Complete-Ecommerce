package com.hazir.Hazirlaniyor.business.abstracts;

import com.hazir.Hazirlaniyor.business.concretes.UpdatePasswordManager;
import com.hazir.Hazirlaniyor.core.utillities.BadRequest.BadRequestException;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.ErrorResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessDataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessResult;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.AppUserDao;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.UpdatePasswordTokenDao;
import com.hazir.Hazirlaniyor.entity.concretes.AppUser;
import com.hazir.Hazirlaniyor.entity.concretes.Product;
import com.hazir.Hazirlaniyor.entity.concretes.UpdatePasswordToken;

import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class ValidatorDal implements ValidatorService {
	private final AppUserDao appUserDao;
	private final UpdatePasswordTokenDao updatePasswordTokenDao;

	@Override
	public Result validateRepeatPassword(String password, String repeatPassword) {
		if(password.equals (repeatPassword)){
			System.out.println ("Gecerli Parola");
		}
		else{
			return new ErrorResult ("There is not a user with this email please try again");
		}
		return new SuccessResult ("Succesfully,Validated");


	}

	@Override
	public Result existsEmail(String email) {
		Optional<AppUser> findUserByEmail = this.appUserDao.getUserByEmail(email);
		if(!findUserByEmail.isPresent ()){
			return new ErrorResult ("There is not a user with this email please try again");
		}
		return new SuccessResult ("Succesfully,Validated");

	}

	@Override
	public ErrorResult showError(String msg) {
		return new ErrorResult (msg);
	}



@Override
	public DataResult<Optional<UpdatePasswordToken>> getToken(String token) {
	if(!updatePasswordTokenDao.findByToken(token).isPresent()){
		this.showError("There is not a user with this token please try again");
	}
	return new SuccessDataResult<Optional<UpdatePasswordToken>>
			(this.updatePasswordTokenDao.findByToken (token),"Data listelendi");

	}

	@Override
	public Result checkIfConfirmationTokenValid(UpdatePasswordManager updatePasswordManager, String token) {
		UpdatePasswordToken updatePasswordToken =
				updatePasswordManager.getToken(token).orElseThrow(() -> new IllegalStateException(
						"token not found"));
		LocalDateTime expiredAt = updatePasswordToken.getExpiresAt();
		if (expiredAt.isBefore(LocalDateTime.now())) {
			this.showError("token expired");
 		}
		return new SuccessResult("Succesfully,Confirmed");
	}

}
