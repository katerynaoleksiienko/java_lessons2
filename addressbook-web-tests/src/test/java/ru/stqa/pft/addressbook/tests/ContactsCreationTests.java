package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.HashSet;
import java.util.List;

public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreation() {
        app.getContactHelper().selectHomePage();
        List<ContactDate>before = app.getContactHelper().getContactList();
        ContactDate contact = new ContactDate("firstname", "lastname", "nickname", "company", "address", "test1");
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillTheForm(new ContactDate("firstname", "lastname", "nickname", "company", "address", "test1"), true);
        app.getContactHelper().submitForm();
        app.getContactHelper().selectHomePage();
        List<ContactDate>after = app.getContactHelper().getContactList();
        Assert.assertEquals( after.size(), before.size() +1);

        before.add(contact);
int max = 0;
for(ContactDate g : after) {
    if (g.getId() > max) {
        max = g.getId();
    }
}
contact.setId(max);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

}

