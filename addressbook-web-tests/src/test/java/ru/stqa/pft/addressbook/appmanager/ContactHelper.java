package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitForm() {
        click(By.name("submit"));
    }

    public void fillTheForm(ContactDate contactDate, boolean creation) {
        type(By.name("firstname"), contactDate.withFirstname());
        type(By.name("lastname"), contactDate.withLastname());
        type(By.name("nickname"), contactDate.withNickname());
        type(By.name("company"), contactDate.withCompany());
        type(By.name("address"), contactDate.withAddress());
        //if (creation)   {
       // new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.withGroup());

       // } else {
        //    Assert.assertFalse(isElementPresent(By.name("new_group")));
        //}
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
    public Contact all() {
        if (contactCache != null) {
            return new Contact(contactCache);
        }
        contactCache = new Contact();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactDate().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return  contactCache;
    }

}
