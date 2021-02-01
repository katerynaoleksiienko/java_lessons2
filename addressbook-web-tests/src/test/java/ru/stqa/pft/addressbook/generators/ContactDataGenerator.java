package ru.stqa.pft.addressbook.generators;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }catch (ParameterException ex) {
            jCommander.usage();
        }
        generator.run();
    }
    private void run() throws IOException {
    List<ContactDate> contact = generateContacts(count);
    saveCsv(contact, new File(file));
}
    private static void saveCsv(List<ContactDate> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactDate contact : contacts) {
            writer.write(String.format("%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress()));
        }
        writer.close();
    }
    private static List<ContactDate> generateContacts(int count) {
        List<ContactDate> contacts = new ArrayList<ContactDate>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactDate().withFirstname(String.format("FirstName %s", i))
                    .withLastname(String.format("LastName %s", i)).withAddress(String.format("Address %s", i)));
        }
        return contacts;
    }
}