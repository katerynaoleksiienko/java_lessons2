package ru.stqa.pft.rest.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.*;
import ru.stqa.pft.rest.addressbook.model.Contact;
import java.util.stream.Collectors;

import ru.stqa.pft.rest.addressbook.model.ContactDate;
import ru.stqa.pft.rest.addressbook.model.GroupDate;
import ru.stqa.pft.rest.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    public static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    public void verifyGroupListUI() {

        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().group();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupDate().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contact dbContact = app.db().contact();
            Contact uiContact = app.contact().all();
            assertThat(uiContact, equalTo(dbContact.stream()
                    .map((g) -> new ContactDate().withId(g.getId()).withFirstname(g.getFirstname()))
                    .collect(Collectors.toSet())));
        }
    }
}

