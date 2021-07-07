package com.hazir.Hazirlaniyor.api.controllers;

import com.hazir.Hazirlaniyor.business.concretes.ForgotPasswordManager;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.entity.concretes.ForgotPassword;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/forgot")
@CrossOrigin(origins = {"http://localhost:3000"})

public class ForgotPasswordController {
	private final ForgotPasswordManager forgotPasswordManager;

	@PostMapping(path = "confirm")
	public Result updatePassword(@RequestParam("token") String token, @RequestBody ForgotPassword forgotPassword) {
		return forgotPasswordManager.updatePassword(forgotPassword, token);
	}

	@GetMapping(path = "{email}")
	public Result requestForResetingPassword(@PathVariable("email") String email) {
		return this.forgotPasswordManager.requestForResetingPassword (email);

	}

}
