package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import java.util.stream.Collectors;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhonesTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
       // app.contact().selectHomePage();
        if (app.db().contact().size() == 0) {
            app.contact().selectHomePage();
            app.contact().createContact(new ContactDate().withFirstname("Test").withGroup("[none]"));
        }
    }


    @Test
    public void testContactPhones() {
        app.goTo().gotoHomePage();
        ContactDate contact = app.db().contact().iterator().next();
        ContactDate contactInfoFromEditFrom = app.contact().infoFromEditFrom(contact);
//assertThat(contact.getHomePhone(), equals(cleaned(contactInfoFromEditFrom.getHomePhone())));

        assertThat(contact.getAllPhone(), equalTo(mergePhones(contactInfoFromEditFrom)));
    }
   private String mergePhones(ContactDate contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesTest::cleaned)
                .collect(Collectors.joining("\n" ));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}
