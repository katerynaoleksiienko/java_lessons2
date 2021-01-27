package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.ContactDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().selectHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactDate().withFirstname("Test1").withGroup("[none]"));
        }
    }
    @Test
    public void testContactDeletion() {
        Contact before = app.contact().all();
        ContactDate deletedContact = before.iterator().next();
        //app.getContactHelper().initContactModification();
        app.contact().delete(deletedContact);
        app.contact().selectHomePage();
        Contact after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() -1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
    }

