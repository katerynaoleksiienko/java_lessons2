package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contact;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Comparator;
import java.util.List;

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
        ContactDate modifiedContact = before.iterator().next();      // app.getContactHelper().selectContact(before.size() -1);
      // app.contact().initContactModification(before.size() -1);
       ContactDate contact = new ContactDate().withId(modifiedContact.getId()).withFirstname("test2").withLastname("test1").withNickname("test4").withCompany("company").withAddress("address").withGroup("test1");
        app.contact().modify(contact);
        Contact after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }
}
