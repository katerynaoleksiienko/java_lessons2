package ru.stqa.pft.rest.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.addressbook.model.ContactDate;
import ru.stqa.pft.rest.addressbook.model.Contact;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import java.io.File;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
       // app.contact().selectHomePage();
        if (app.db().contact().size() == 0) {
            app.contact().homeContact();
            app.contact().createContact(new ContactDate().withFirstname("Dima"));
        }
    }

    @Test
    public void testContactModification() {
        Contact before = app.db().contact();
        ContactDate modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/download.png");
       ContactDate contact = new ContactDate()
               .withId(modifiedContact.getId()).withFirstname("test").withLastname("test55").withPhoto(photo).withNickname("tes54")
               .withCompany("company2").withAddress("address2").withMobilePhone("2222222").withEmail("ghgh2");
        app.contact().modify(contact);
        Contact after = app.db().contact();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }
}
