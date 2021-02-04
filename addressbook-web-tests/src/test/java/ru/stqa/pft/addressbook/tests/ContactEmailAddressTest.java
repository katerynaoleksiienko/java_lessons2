package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailAddressTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
       // app.contact().selectHomePage();
        if (app.db().contact().size() == 0) {
            app.contact().selectHomePage();
            app.contact().createContact(new ContactDate().withFirstname("Test").withGroup("[none]"));
        }
    }

    @Test
    public void testContactEmailAddresses() {
        app.goTo().gotoHomePage();
        ContactDate contact = app.db().contact().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFromEditFrom(contact);
        assertThat(contact.getAllEmailAddresses(), equalTo(mergeEmailAddresses(contactInfoFromEditForm)));
    }

    private String mergeEmailAddresses(ContactDate contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactEmailAddressTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String emailAddress) {
        return emailAddress.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}