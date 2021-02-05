package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.ContactDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
      //  app.contact().selectHomePage();
        if (app.db().contact().size() == 0) {
            app.contact().homeContact();
            app.contact().createContact(new ContactDate().withFirstname("Dima"));
        }
    }
    @Test
    public void testContactDeletion() {
        Contact before = app.db().contact();
        ContactDate deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.contact().homeContact();
        Contact after = app.db().contact();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }
    }

