package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.ArrayList;
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
        type(By.name("nickname"), contactDate.getNickname());
        click(By.name("company"));
        type(By.name("company"), contactDate.getCompany());
        type(By.name("address"), contactDate.getAddress());
        if (creation)   {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.getGroup());

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }
    public void selectHomePage() {
        click(By.linkText("home"));
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click(); //get(index).
    }

    public void updateForm() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        closePopup();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteGroup() {
        click(By.xpath("//input[@name='delete']"));
    }

    public void createContact(ContactDate contactDate) {
        addNewContact();
        fillTheForm(contactDate);
        submitForm();
    }
    public void fillTheForm(ContactDate contactDate) {
    }

        public boolean isThereAContact() {
        return  isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
       return  wd.findElements(By.name("selected[]")).size();

    }

    public List<ContactDate> getContactList() {
        List<ContactDate>contacts = new ArrayList<ContactDate>();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactDate contact = new ContactDate(id, firstname,
                    lastname, null, null, null, null);
            contacts.add(contact);
        }
        return  contacts;
    }

}
