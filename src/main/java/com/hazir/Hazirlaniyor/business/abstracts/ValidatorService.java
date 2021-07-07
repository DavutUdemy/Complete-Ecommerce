package com.hazir.Hazirlaniyor.business.abstracts;

import com.hazir.Hazirlaniyor.business.concretes.ConfirmationTokenManager;
import com.hazir.Hazirlaniyor.business.concretes.UpdatePasswordManager;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.ErrorResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.entity.concretes.ConfirmationToken;
import com.hazir.Hazirlaniyor.entity.concretes.UpdatePasswordToken;

import java.util.Optional;

public interface ValidatorService {
	Result validateRepeatPassword(String password, String repeatPassword);
	Result existsEmail(String email);
	public ErrorResult showError(String msg);
	public DataResult<Optional<UpdatePasswordToken>> getToken(String token);
	Result checkIfConfirmationTokenValid(UpdatePasswordManager updatePasswordManager, String token);

}
