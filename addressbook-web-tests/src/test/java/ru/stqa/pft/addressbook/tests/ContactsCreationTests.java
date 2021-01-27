package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreation() throws Exception {
      app.contact().selectHomePage();
       Contact before = app.contact().all();
        app.contact().addNewContact();
        ContactDate contact = new ContactDate().withFirstname("Test1").withLastname("Test2").withNickname("Test3").withCompany("Test4").withAddress("Test5").withGroup("Test6");

        app.contact().fillTheForm(contact, true);
        app.contact().submitForm();
        app.contact().selectHomePage();
        Contact after = app.contact().all();
        //assertThat(after.size(), equalTo(before.size() + 1));
        Assert.assertEquals(after.size(), before.size() + 1);

        assertThat(after, equalTo(
               before.withAdded(contact.withId(after.stream().mapToInt((c)  -> c.getId()).max().getAsInt()))));
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}

