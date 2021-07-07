package com.hazir.Hazirlaniyor.business.concretes;

import com.hazir.Hazirlaniyor.dataAccess.abstracts.UpdatePasswordTokenDao;
import com.hazir.Hazirlaniyor.entity.concretes.UpdatePasswordToken;

import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdatePasswordManager {

	private final UpdatePasswordTokenDao updatePasswordTokenDao;

	public void saveConfirmationToken(UpdatePasswordToken token) {
		updatePasswordTokenDao.save (token);
	}

	public Optional<UpdatePasswordToken> getToken(String token) {
		return updatePasswordTokenDao.findByToken(token);
	}


}
