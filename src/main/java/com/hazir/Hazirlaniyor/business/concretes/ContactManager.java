package com.hazir.Hazirlaniyor.business.concretes;

import com.hazir.Hazirlaniyor.business.abstracts.ContactService;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessDataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessResult;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.ContactDao;
import com.hazir.Hazirlaniyor.entity.concretes.Contact;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContactManager implements ContactService {
	private final ContactDao contactDao;

	@Override
	public DataResult<List<Contact>> getContacts() {
		return new SuccessDataResult<List<Contact>>
				(this.contactDao.findAll (), "Data listelendi");

	}

	@Override
	public DataResult<List<Contact>> findContactByName(String firstName) {
		return new SuccessDataResult<List<Contact>>
				(this.contactDao.findContactByFirstName (firstName), "Data listelendi");
	}

	@Override
	public Result addNewContact(Contact contact) {
		this.contactDao.save (contact);
		return new SuccessResult ("Kullanici Bilgileri Eklendi");
	}

	@Override
	public Result deleteContactById(Long Id) {
		this.contactDao.deleteById (Id);
		return new SuccessResult ("Kullanici Bilgileri Silindi");

	}
}
