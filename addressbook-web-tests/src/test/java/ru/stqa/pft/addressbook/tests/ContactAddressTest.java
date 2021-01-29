package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().selectHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactDate().withFirstname("Test").withGroup("[none]"));
        }
    }

    @Test
    public void testContactAddresses() {
        app.goTo().gotoHomePage();
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFromEditFrom(contact);
        assertThat(contact.getAddress(), equalTo(mergeAddresses(contactInfoFromEditForm)));
    }

    private String mergeAddresses(ContactDate contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

}