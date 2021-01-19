package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitForm() {
        click(By.name("submit"));
    }

    public void fillTheForm(ContactDate contactDate) {
        type(By.name("firstname"), contactDate.getFirstname());
        type(By.name("lastname"), contactDate.getLastname());
        type(By.name("nickname"), contactDate.getNickname());
        click(By.name("company"));
        type(By.name("company"), contactDate.getCompany());
        type(By.name("address"), contactDate.getAddress());
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }
    public void selectHomePage() {
        click(By.linkText("home"));
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }


    public void updateForm() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        closePopup();
    }


    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteGroup() {
        click(By.xpath("//input[@name='delete']"));
    }
}
