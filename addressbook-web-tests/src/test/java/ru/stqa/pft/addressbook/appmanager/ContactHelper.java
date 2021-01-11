package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
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
}
