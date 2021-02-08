package ru.stqa.pft.rest.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.addressbook.model.ContactDate;

import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contact().size() == 0) {
            app.contact().homeContact();
            app.contact().createContact(new ContactDate().withFirstname("Dima"));
        }
    }

    @Test
    public void testContactAddresses() {
        app.goTo().gotoHomePage();
        ContactDate contact = app.db().contact().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFromEditFrom(contact);
        assertThat(contact.getAddress(), equalTo(mergeAddresses(contactInfoFromEditForm)));
    }

    private String mergeAddresses(ContactDate contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

}