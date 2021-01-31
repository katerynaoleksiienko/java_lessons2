package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contact;

import java.util.List;


public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitForm() {
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

/*
        if (creation)   {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

 */
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }
   // public void delete() {
     //   click(By.xpath("//input[@value='Delete']"));
     //   wd.switchTo().alert().accept();
      //  wd.findElement(By.cssSelector("div.msgbox"));
   // }
    public void selectHomePage() {
        click(By.linkText("home"));
    }

    public void initContactModification(int id) {
        wd.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();
        // click(By.xpath("(//img[@alt='Edit'])"));

    }
    public void updateForm() {
        click(By.name("update"));
    }

    //public void selectContact(int index) {
        //wd.findElements(By.name("selected[]")).get(index).click();
    //}

   // public void deleteGroup() {
        //click(By.xpath("//input[@name='delete']"));
  // }

    public void createContact(ContactDate contactDate) {
        addNewContact();
        fillTheForm(contactDate);
        contactCache = null;
        submitForm();
    }
    public void modify(ContactDate contact) {
        initContactModification(contact.getId());
        fillTheForm(contact, false);
        updateForm();
        contactCache = null;
        selectHomePage();
    }
    public void delete(ContactDate contact) {
        deleteById(contact.getId());
        contactCache = null;
        submitContactDeletion();
    }
    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    private void deleteById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
       // wd.switchTo().alert().accept();
        //wd.findElement(By.cssSelector("div.msgbox"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
    public void fillTheForm(ContactDate contactDate) {
    }
    public int count2() {
       return  wd.findElements(By.name("selected[]")).size();
    }

    private Contact contactCache = null;
    //public Contact all() {
        //if (contactCache != null) {
         //   return new Contact(contactCache);
       // }
        //contactCache = new Contact();
       // List<WebElement> elements = wd.findElements(By.name("entry"));
    //for (WebElement element : elements) {
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
        initContactModification(contact.getId());
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

}
