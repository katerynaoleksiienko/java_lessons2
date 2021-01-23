package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.List;

public class ContactDeletionTest extends TestBase {
    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDate("firstname", "lastname", "nickname", "company", "address", "test1"));
        }
        List<ContactDate> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() -1);
        //app.getContactHelper().deleteContact();
        app.getContactHelper().selectHomePage();
        List<ContactDate>after = app.getContactHelper().getContactList();
        Assert.assertEquals( after.size(), before.size() -1);

        before.remove(before.size() -1);
        Assert.assertEquals(before,after);
        }
    }

