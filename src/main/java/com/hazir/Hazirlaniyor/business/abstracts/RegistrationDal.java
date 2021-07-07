package com.hazir.Hazirlaniyor.business.abstracts;

import com.hazir.Hazirlaniyor.business.concretes.EmailValidatorManager;
import com.hazir.Hazirlaniyor.core.utillities.results.ErrorResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessResult;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.AppUserDao;
import com.hazir.Hazirlaniyor.entity.concretes.AppUser;
import com.hazir.Hazirlaniyor.entity.concretes.RegistrationRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationDal implements RegistrationService{


    @Override
    public Result takenEmail(AppUserDao appUserDao, RegistrationRequest request) {
	    Optional<AppUser> userByEmail = appUserDao.findUserByEmail (request.getEmail ());
	    if(userByEmail.isPresent ()){
	    	showError("There is a user with this account");
	    }
	    return new SuccessResult ("You can continue");
    }

    @Override
    public Result isValid(EmailValidatorManager emailValidatorManager, RegistrationRequest request) {
	     boolean checkIfValidUser =  emailValidatorManager.test(request.getEmail());
	     if(!checkIfValidUser){
	     	return showError ("Please Enter Valid Email");
	     }
	     return new SuccessResult ("Confirmed");

    }

	@Override
	public ErrorResult showError(String msg) {
		return new ErrorResult (msg);
	}


}
