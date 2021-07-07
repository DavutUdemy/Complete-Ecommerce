package com.hazir.Hazirlaniyor.business.abstracts;

import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.entity.concretes.ForgotPassword;
import com.hazir.Hazirlaniyor.entity.concretes.SuccessEmail;

public interface ForgotPasswordService {
	public Result requestForResetingPassword(String email);
	public void sendSuccessEmail(String email);
	public Result updatePassword(ForgotPassword forgotPassword,String token);
	String buildEmail(String Link);

}
