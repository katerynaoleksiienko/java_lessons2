package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import com.google.common.collect.Sets;
import ru.stqa.pft.addressbook.model.GroupDate;
import java.util.Random;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Iterator;
import java.util.List;

public class ContactAddToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contact().size() == 0) {
            app.contact().homeContact();
            app.contact().createContact(new ContactDate().withFirstname("Dima").withLastname("testg"));
        }
        if (app.db().group().size() == 0) {
            Random random = new Random();
            app.goTo().groupPage();
            app.group().create(new GroupDate().withName("test" + random.nextInt(10)));
        }
    }
        @Test
        public void addContactToGroup() {
            app.goTo().homeContact();
            List<ContactDate> beforeContactList = app.contact().getContactList();
            ContactDate contactDate = null;

            int counter = 0;

            int totalDBGroupSize = app.db().group().size();
            for (ContactDate contact : beforeContactList) {
                if (contact.getGroups().size() != totalDBGroupSize) {
                    contactDate = contact;
                    counter++;
                    break;
                }
            }

            if (counter == 0) {
                app.goTo().homeContact();
                app.contact().createContact(new ContactDate().withFirstname("Dima2").withLastname("test2"));
                beforeContactList = app.contact().getContactList();
                for (ContactDate contact : beforeContactList) {
                    if (contact.getGroups().size() != totalDBGroupSize) {
                        contactDate = contact;
                        break;
                    }
                }
            }
            app.contact().addContactToGroup(contactDate);
            List<ContactDate> afterContact = app.contact().getContactList();
            ContactDate after = null;
            Iterator<ContactDate> allContacts = app.db().contact().iterator();
            while (allContacts.hasNext()) {
                after = allContacts.next();
                if (after.getId() == contactDate.getId()) {
                    break;
                }
            }
            ContactDate bContact = null;
            ContactDate aContact = null;

            if (afterContact.size() > beforeContactList.size()) {
                afterContact.removeAll(beforeContactList);
                contactDate = afterContact.get(0);
            }
            for (ContactDate  contactbefore : beforeContactList) {
                if (contactbefore.getId() == contactDate.getId()) {
                    bContact = contactbefore;
                }
            }
            for (ContactDate contactafter : afterContact) {
                if (contactafter.getId() == contactDate.getId()) {
                    aContact = contactafter;
                }
            }
            Set<GroupDate> beforeContactGroups = Sets.difference(aContact.getGroups(), bContact.getGroups());

            Assert.assertEquals(contactDate.getGroups().size(), after.getGroups().size());
            assertThat(aContact.getGroups(),
                    equalTo(bContact.getGroups().withAdded(beforeContactGroups.iterator().next())));
        }
}


