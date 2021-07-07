package com.hazir.Hazirlaniyor.business.abstracts;

import com.hazir.Hazirlaniyor.business.concretes.EmailValidatorManager;
import com.hazir.Hazirlaniyor.core.utillities.results.ErrorResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.AppUserDao;
import com.hazir.Hazirlaniyor.entity.concretes.RegistrationRequest;

public interface RegistrationService {
   public Result takenEmail(AppUserDao appUserDao, RegistrationRequest request);
   public Result isValid(EmailValidatorManager emailValidatorManager, RegistrationRequest request);
   public ErrorResult showError(String msg);
}
