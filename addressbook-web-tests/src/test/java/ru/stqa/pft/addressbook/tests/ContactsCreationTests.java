package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.ContactDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreation() {
      app.contact().selectHomePage();
       Contact before = app.contact().all();
        app.contact().addNewContact();
        ContactDate contact = new ContactDate().withFirstname("Test1").withLastname("Test2").withNickname("Test3")
                .withCompany("Test4").withAddress("Test5").withMobilePhone("222222").withEmail("ghgh").withGroup("Test6");
        app.contact().fillTheForm(contact, true);
        app.contact().submitForm();
        app.contact().selectHomePage();
        assertThat(app.contact().count2(), equalTo(before.size() + 1));
        Contact after = app.contact().all();
        assertThat(after, equalTo(
               before.withAdded(contact.withId(after.stream().mapToInt((c)  -> c.getId()).max().getAsInt()))));
    }
    @Test (enabled = false)
    public void testBadContactsCreation() {
        app.contact().selectHomePage();
        Contact before = app.contact().all();
        app.contact().addNewContact();
        ContactDate contact = new ContactDate().withFirstname("Test1'").withLastname("Test2").withNickname("Test3").withCompany("Test4").withAddress("Test5").withGroup("Test6");
        app.contact().fillTheForm(contact, true);
        app.contact().submitForm();
        app.contact().selectHomePage();
        assertThat(app.contact().count2(), equalTo(before.size()));
        Contact after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}

