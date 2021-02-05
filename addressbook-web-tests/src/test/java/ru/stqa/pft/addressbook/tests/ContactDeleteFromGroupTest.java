package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import com.google.common.collect.Sets;
import ru.stqa.pft.addressbook.model.GroupDate;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Iterator;

public class ContactDeleteFromGroupTest extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().group().size() == 0) {
            Random random = new Random();
            app.goTo().groupPage();
            app.group().create(new GroupDate().withName("test" + random.nextInt(100)));
        }
        if (app.db().contact().size() == 0) {
            app.goTo().homeContact();
            app.contact().createContact(new ContactDate().withFirstname("Dima").withLastname("Test"));
            ContactDate contactsData = app.db().contact().iterator().next();
            app.contact().addContactToGroup(contactsData);
        }
        List<ContactDate> contactList = app.contact().getContactList();
        int counter = 0;
        for (ContactDate contact : contactList) {
            if (contact.getGroups().size() > 0) {
                counter++;
                break;
            }
        }
        if (counter == 0) {
        }
        ContactDate contactData = app.db().contact().iterator().next();
            app.contact().addContactToGroup(contactData);
    }

    @Test
    public void removeContactFromGroup() {
        app.goTo().homeContact();
        List<ContactDate> beforeContactList = app.contact().getContactList();
        ContactDate contactDate = null;
        Iterator<ContactDate> allContacts = app.db().contact().iterator();

        while (allContacts.hasNext()) {
            contactDate = allContacts.next();
            if (contactDate.getGroups().size() > 0) {
                break;
            }
        }
        if (contactDate != null && contactDate.getGroups().size() > 0) {
            app.contact().selectGroupFilterByGroupId(contactDate.getGroups().iterator().next().getId());
            app.contact().removeGroup(contactDate);

            ContactDate after = null;

            Iterator<ContactDate> updatedContacts = app.db().contact().iterator();

            while (updatedContacts.hasNext()) {
                after = updatedContacts.next();
                if (after.getId() == contactDate.getId()) {
                    break;
                }
            }
            List<ContactDate> afterContactList = app.contact().getContactList();
            Set<GroupDate> beforeContactGroups = Sets.difference(contactDate.getGroups(), after.getGroups());
            Assert.assertEquals(contactDate.getGroups().size() - 1, after.getGroups().size());
            assertThat(after.getGroups(),
                    equalTo(contactDate.getGroups().without(beforeContactGroups.iterator().next())));

        }

    }
}