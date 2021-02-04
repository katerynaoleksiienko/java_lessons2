package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.ContactDate;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.io.File;
import java.io.BufferedReader;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import com.thoughtworks.xstream.XStream;
import java.util.stream.Collectors;


public class ContactsCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContact() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"));
        String xml = "";        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactDate.class);
        List<ContactDate> contacts = (List<ContactDate>) xStream.fromXML(xml);
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContact")
    public void testAddressBook(ContactDate contact) throws Exception {
        Contact before = app.db().contact();
        app.contact().addNewContact();
        app.contact().fillTheForm(contact, true);
        app.contact().submitForm();
       app.contact().selectHomePage();
        Contact after = app.db().contact();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
               before.withAdded(contact.withId(after.stream().mapToInt((c)  -> c.getId()).max().getAsInt()))));

    }
    @Test (enabled = false)
    public void testBadContactsCreation() {
        app.contact().selectHomePage();
        Contact before = app.contact().all();
        app.contact().addNewContact();
        ContactDate contact = new ContactDate().withFirstname("Test1'").withLastname("Test2").withNickname("Test3").withCompany("Test4").withAddress("Test5").withGroup("Test6");
        app.contact().fillTheForm(contact, true);
        app.contact().submitForm();
        app.contact().selectHomePage();
        assertThat(app.contact().count2(), equalTo(before.size()));
        Contact after = app.contact().all();
        assertThat(after, equalTo(before));
    }
    @Test (enabled = false)
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/download.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
        verifyContactListUI();
  
    }
}
