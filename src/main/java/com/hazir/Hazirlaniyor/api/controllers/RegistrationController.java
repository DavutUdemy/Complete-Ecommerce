package com.hazir.Hazirlaniyor.api.controllers;

import com.hazir.Hazirlaniyor.business.concretes.RegistrationManager;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.AppUserDao;
import com.hazir.Hazirlaniyor.entity.concretes.AppUser;
import com.hazir.Hazirlaniyor.entity.concretes.RegistrationRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

	private final RegistrationManager registrationService;
	private final AppUserDao appUserRepository;

	@GetMapping("/admin")
	public List<AppUser> getAllUsers() {
		return appUserRepository.findAll ();
	}
	@GetMapping(path = "{email}")
	public DataResult<Optional<AppUser>> findUserByFirstName(@PathVariable("email")String email){
		return registrationService.findUserByFirstName(email);
	}

	@PostMapping
	public Result register(@RequestBody RegistrationRequest request) {
		return registrationService.register (request);
	}

	@GetMapping(path = "confirm")
	public Result confirm(@RequestParam("token") String token) {
		return registrationService.confirmToken (token);
	}

}
