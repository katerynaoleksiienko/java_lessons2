package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactDeletionTest extends TestBase {
    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDate("firstname", "lastname", "nickname", "company", "address", "test1"));
        }
        app.getContactHelper().selectHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
    }
}

