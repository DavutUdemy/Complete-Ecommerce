package com.hazir.Hazirlaniyor.api.controllers;

import com.hazir.Hazirlaniyor.business.concretes.ContactManager;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.entity.concretes.Contact;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/contact")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})

public class ContactController {
	private final ContactManager contactManager;

	@GetMapping
	public DataResult<List<Contact>> getAll() {
		return this.contactManager.getContacts ();
	}

	@GetMapping(path = "{firstName}")
	public DataResult<List<Contact>> findContactAccountByName(@PathVariable("firstName") String firstName) {
		return this.contactManager.findContactByName (firstName);
	}

	@PostMapping
	public Result addContact(@RequestBody Contact contact) {
		return this.contactManager.addNewContact (contact);
	}

	@DeleteMapping(path = "{id}")
	public Result deleteContactById(@PathVariable("Id") Long Id) {
		return this.contactManager.deleteContactById (Id);
	}

}
