package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().selectHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillTheForm(new ContactDate("firstname",
                "lastname", "nickname", "company", "address"));
        app.getContactHelper().updateForm();
    }
    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
    }
}
