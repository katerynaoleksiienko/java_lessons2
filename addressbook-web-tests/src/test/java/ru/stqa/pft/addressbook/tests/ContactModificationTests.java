package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().selectHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDate("firstname", "lastname", "nickname", "company", "address", "test1"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillTheForm(new ContactDate("firstname",
                "lastname", "nickname", "company", "address", null), false);
        app.getContactHelper().updateForm();
    }
}
