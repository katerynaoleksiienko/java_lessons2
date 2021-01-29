package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contact;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().selectHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactDate().withFirstname("Test").withGroup("[none]"));
        }
    }

    @Test
    public void testContactModification() {
        Contact before = app.contact().all();
        ContactDate modifiedContact = before.iterator().next();
       ContactDate contact = new ContactDate().withId(modifiedContact.getId()).withFirstname("test2").withLastname("test1").withNickname("test4").withCompany("company").withAddress("address").withMobilePhone("222222").withEmail("ghgh").withGroup("test1");
        app.contact().modify(contact);
        assertThat(app.contact().count2(), equalTo(before.size()));
        Contact after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }
}
