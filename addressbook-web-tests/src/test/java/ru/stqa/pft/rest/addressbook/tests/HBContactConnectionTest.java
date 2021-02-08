package ru.stqa.pft.rest.addressbook.tests;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.addressbook.model.ContactDate;
import java.util.List;

public class HBContactConnectionTest {
    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test
    public void testHbContactConnection(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactDate> result = session.createQuery("from ContactDate where deprecated 0000-00-00").list();
        for (ContactDate contact : result) {
            System.out.println(contact);
            System.out.println(contact.getGroups());

        }
        session.getTransaction().commit();
        session.close();
    }
}
