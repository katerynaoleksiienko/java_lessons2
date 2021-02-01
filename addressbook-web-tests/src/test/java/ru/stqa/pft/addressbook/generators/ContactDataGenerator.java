package ru.stqa.pft.addressbook.generators;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;

import ru.stqa.pft.addressbook.model.ContactDate;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactDate> contact = generateContacts(count);
        if (format.equals("csv")) {
            saveCsv(contact, new File(file));
        } else if (format.equals("xml")) {
            saveCsv(contact, new File(file));
        } else {
            System.out.println("Неизвесный формат" + format);
        }
    }

    private static void saveCsv(List<ContactDate> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactDate contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress(), contact.getMobilePhone(), contact.getEmail(), contact.getGroup(), contact.getPhoto()));
        }
        writer.close();
    }

    private void saveXml(List<ContactDate> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactDate.class);
        String xml = xStream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private static List<ContactDate> generateContacts(int count) {
        List<ContactDate> contacts = new ArrayList<ContactDate>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactDate().withFirstname(String.format("FirstName %s", i))
                    .withLastname(String.format("LastName %s", i)).withAddress(String.format("Address %s", i)).withMobilePhone(String.format("mobilephone %s", i)).withEmail(String.format("emailaddress %s", i))
                    .withGroup("[none]").withPhoto(new File("src/test/resources/123.png")));
        }
            return contacts;
        }
    }