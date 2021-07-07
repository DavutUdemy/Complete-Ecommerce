package com.hazir.Hazirlaniyor.business.concretes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.ContactDao;
import com.hazir.Hazirlaniyor.entity.concretes.Contact;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ContactManager.class})
@ExtendWith(SpringExtension.class)
public class ContactManagerTest {
	@MockBean
	private ContactDao contactDao;

	@Autowired
	private ContactManager contactManager;

	@Test
	public void testGetContacts() {
		when (this.contactDao.findAll ()).thenReturn (new ArrayList<Contact> ());
		DataResult<List<Contact>> actualContacts = this.contactManager.getContacts ();
		assertTrue (actualContacts.getData ().isEmpty ());
		assertTrue (actualContacts.isSuccess ());
		assertEquals ("Data listelendi", actualContacts.getMessage ());
		verify (this.contactDao).findAll ();
	}

	@Test
	public void testFindContactByName() {
		when (this.contactDao.findContactByFirstName (anyString ())).thenReturn (new ArrayList<Contact> ());
		DataResult<List<Contact>> actualFindContactByNameResult = this.contactManager.findContactByName ("Jane");
		assertTrue (actualFindContactByNameResult.getData ().isEmpty ());
		assertTrue (actualFindContactByNameResult.isSuccess ());
		assertEquals ("Data listelendi", actualFindContactByNameResult.getMessage ());
		verify (this.contactDao).findContactByFirstName (anyString ());
		assertTrue (
				this.contactManager.getContacts () instanceof com.hazir.Hazirlaniyor.core.utillities.results.SuccessDataResult);
	}

	@Test
	public void testAddNewContact() {
		Contact contact = new Contact ();
		contact.setLastName ("Doe");
		contact.setPostalCode ("Postal Code");
		contact.setUserEmail ("jane.doe@example.org");
		contact.setFullAdress ("Full Adress");
		contact.setId (123L);
		contact.setShipmentStatus ("Shipment Status");
		contact.setPhoneNumber ("4105551212");
		contact.setFirstName ("Jane");
		when (this.contactDao.save ((Contact) any ())).thenReturn (contact);
		Result actualAddNewContactResult = this.contactManager.addNewContact (new Contact ());
		assertEquals ("Kullanici Bilgileri Eklendi", actualAddNewContactResult.getMessage ());
		assertTrue (actualAddNewContactResult.isSuccess ());
		verify (this.contactDao).save ((Contact) any ());
		assertTrue (
				this.contactManager.getContacts () instanceof com.hazir.Hazirlaniyor.core.utillities.results.SuccessDataResult);
	}

	@Test
	public void testDeleteContactById() {
		doNothing ().when (this.contactDao).deleteById ((Long) any ());
		Result actualDeleteContactByIdResult = this.contactManager.deleteContactById (123L);
		assertEquals ("Kullanici Bilgileri Silindi", actualDeleteContactByIdResult.getMessage ());
		assertTrue (actualDeleteContactByIdResult.isSuccess ());
		verify (this.contactDao).deleteById ((Long) any ());
		assertTrue (
				this.contactManager.getContacts () instanceof com.hazir.Hazirlaniyor.core.utillities.results.SuccessDataResult);
	}
}

