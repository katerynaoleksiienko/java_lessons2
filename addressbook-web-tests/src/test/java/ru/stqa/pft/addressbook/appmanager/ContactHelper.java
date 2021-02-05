package ru.stqa.pft.addressbook.appmanager;

import com.google.common.collect.Sets;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import static ru.stqa.pft.addressbook.tests.TestBase.app;

import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submit() {
        click(By.name("submit"));
    }

    public void fillTheForm(ContactDate contactDate, boolean creation) {
        type(By.name("firstname"), contactDate.getFirstname());
        type(By.name("lastname"), contactDate.getLastname());
        attach(By.name("photo"), contactDate.getPhoto());
        type(By.name("nickname"), contactDate.getNickname());
        type(By.name("company"), contactDate.getCompany());
        type(By.name("mobile"), contactDate.getMobilePhone());
        type(By.name("address"), contactDate.getAddress());
        type(By.name("email"), contactDate.getEmail());


        if (creation)   {
            if (contactDate.getGroups().size() > 0) {
                Assert.assertTrue(contactDate.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.getGroups().iterator().next().getName());
            }        
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }
   // public void delete() {
     //   click(By.xpath("//input[@value='Delete']"));
     //   wd.switchTo().alert().accept();
      //  wd.findElement(By.cssSelector("div.msgbox"));
   // }
   private void checkContactById(int id) {
       wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
   }
    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }
    public void clickGroup() {
        wd.findElement(By.name("to_group")).click();
    }
    public void selectGroupFilterByGroupId(int groupId) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(groupId));
    }
      //public void selectContact(int index) {
        //wd.findElements(By.name("selected[]")).get(index).click();
    //}

   // public void deleteGroup() {
        //click(By.xpath("//input[@name='delete']"));
  // }


    public void clickDeleteContactFromGroup() {
        wd.findElement(By.name("remove")).click();
    }

    public void delete(ContactDate contact) {
        checkContactById(contact.getId());
        submitContactDeletion();
    }
    public void fillTheForm(ContactDate contactDate) {
    }
    public void selectGroup(ContactDate contactDate, boolean selection) {

        int contactGroupSize = contactDate.getGroups().size();
        int totalDBGroupSize = app.db().group().size();
        int counter = 0;
        if (selection) {
            if (contactGroupSize == 0 || contactGroupSize == totalDBGroupSize) {

                List<ContactDate> contactList = app.contact().getContactList();
                for (ContactDate contact: contactList) {
                    if (contact.getGroups().size() < totalDBGroupSize) {
                        homeContact();
                        editContactById(contact.getId());
                        homeContact();
                        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(getGroupListContact(contact.getGroups()).iterator().next().getId()));
                        counter++;
                        break;
                    }
                }
                if (counter == 0) {
                    app.goTo().homeContact();
                    app.contact().createContact(new ContactDate().withFirstname("Test").withLastname("Test2"));
                    addContactToGroup(contactDate);
                }
            } else {
                Groups totalContactGroups =  contactDate.getGroups();
                new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(getGroupListContact(totalContactGroups).iterator().next().getId()));
            }
        }
    }
    public  Set<GroupDate> getGroupListContact(Groups totalContactGroups) {
        Groups totalGroups = app.db().group();
        return Sets.difference(totalGroups, totalContactGroups);
    }
    public void addGroup() {
        wd.findElement(By.name("add")).click();
    }
    public void homeContact() {
        click(By.linkText("home"));
    }
    public void editContactById(int id) {
        wd.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();
        // click(By.xpath("(//img[@alt='Edit'])"));
    }
    public void updateContact() {
        click(By.name("update"));
    }
    public void createContact(ContactDate contactDate) {
        addNewContact();
        fillTheForm(contactDate);
        submit();
    }
    public void modify(ContactDate contact) {
        editContactById(contact.getId());
        fillTheForm(contact, false);
        updateContact();
        homeContact();
    }
    public void removeGroup(ContactDate contact) {
        editContactById(contact.getId());
        clickDeleteContactFromGroup();
        homeContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    /*public int count2() {
       return  wd.findElements(By.name("selected[]")).size();
    }
     */
    //public Contact all() {
        //if (contactCache != null) {
         //   return new Contact(contactCache);
       // }
        //contactCache = new Contact();
       // List<WebElement> elements = wd.findElements(By.name("entry"));
    //for (WebElement element : elements) {
    public void addContactToGroup(ContactDate contact) {
        checkContactById(contact.getId());
        clickGroup();
        selectGroup(contact, true);
        addGroup();
        homeContact();
    }
    public Contact all() {
        Contact contacts = new Contact();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhone = cells.get(5).getText();
            String allAddresses = cells.get(3).getText();
            String allEmailAddresses = cells.get(4).getText();

            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
           // int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactDate().withId(id).withFirstname(firstname).withLastname(lastname).withAllPhone(allPhone).withAddress(allAddresses).withAllEmailAddresses(allEmailAddresses));
        }
        return  contacts;
    }
    public ContactDate infoFromEditFrom(ContactDate contact) {
        editContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.cssSelector("textarea[name=\"address\"]")).getText();
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactDate().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
    public List<ContactDate> getContactList() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<ContactDate> result = session.createQuery( "from ContactDate where deprecated = '0000-00-00'" ).list();
        session.close();
        return result;
    }
    public List<ContactDate> getGroupList() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<ContactDate> result = session.createQuery("from GroupDate").list();
        session.close();
        return result;
    }

}
