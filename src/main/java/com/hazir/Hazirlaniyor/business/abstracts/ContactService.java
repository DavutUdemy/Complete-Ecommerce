package com.hazir.Hazirlaniyor.business.abstracts;

import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.entity.concretes.Contact;

import java.util.List;

public interface ContactService {
    DataResult<List<Contact>> getContacts(); //ADMIN PAGE
    DataResult<List<Contact>> findContactByName(String firstName);
    Result addNewContact(Contact contact);
    Result deleteContactById(Long Id);



}
