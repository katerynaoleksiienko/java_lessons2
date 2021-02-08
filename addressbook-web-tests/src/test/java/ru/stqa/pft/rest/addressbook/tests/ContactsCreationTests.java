package ru.stqa.pft.rest.addressbook.tests;
import ru.stqa.pft.rest.addressbook.model.GroupDate;
import ru.stqa.pft.rest.addressbook.model.Groups;

import org.testng.annotations.*;
import ru.stqa.pft.rest.addressbook.model.ContactDate;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import java.io.File;
import java.io.BufferedReader;

import static org.hamcrest.MatcherAssert.assertThat;
import com.thoughtworks.xstream.XStream;

import java.util.stream.Collectors;


public class ContactsCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContact() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactDate.class);
        List<ContactDate> contacts = (List<ContactDate>) xStream.fromXML(xml);
        return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().group().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupDate().withName("test1"));
        }
    }

    @Test
    public void testContactCreation() {
        Groups group = app.db().group();
        File photo = new File("src/test/resources/download.png");
        ContactDate newContact = new ContactDate().withFirstname("Test").withLastname("Dom").withPhoto(photo)
                .inGroup(group.iterator().next());
        app.contact().addNewContact();
        app.contact().fillTheForm(newContact, true);
        app.contact().submit();
        app.contact().homeContact();

    }
}