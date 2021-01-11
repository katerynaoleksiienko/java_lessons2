package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreation() {
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillTheForm(new ContactDate("firstname",
                "lastname", "nickname", "company", "address"));
        app.getContactHelper().submitForm();
        app.getSessionHelper().logout("Logout");
    }

}

