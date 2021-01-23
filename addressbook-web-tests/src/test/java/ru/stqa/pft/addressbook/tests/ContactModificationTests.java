package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().selectHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDate("firstname", "lastname", "nickname", "company", "address", "test1"));
        }
        List<ContactDate>before = app.getContactHelper().getContactList();
       app.getContactHelper().selectContact(before.size() -1);
       ContactDate contact = new ContactDate(before.get(before.size() -1).getId(), "firstname", "lastname", "nickname", "company", "address", "test1");
       app.getContactHelper().fillTheForm(contact);
        app.getContactHelper().updateForm();
        app.getContactHelper().selectHomePage();
        List<ContactDate> after = app.getContactHelper().getContactList();
        Assert.assertEquals( after.size(), before.size());
        before.remove(before.size() -1);
        before.add(contact);
                Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
