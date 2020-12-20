package ru.stqa.pft.addressbook;


import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactsCreationTests {
    private WebDriver wd;

    @BeforeMethod (alwaysRun = true)
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php");
        login("admin", "secret");
    }
    private void login(String userName, String password) {

        toFillLastName("user", userName);
        wd.findElement(By.id("LoginForm")).click();
        toFillNickName("pass", password);
        submitForm("//input[@value='Login']");
    }
    @Test
    public void testContactsCreation() throws Exception {
        goToAddPage("add new");
        toFillFirstName("firstname", "Katya");
        toFillLastName("lastname", "Oleksienko");
        toFillNickName("nickname", "Kate");
        toFollAdressName("address", "Vinnitsya");
        submitForm("(//input[@name='submit'])[2]");
    }

    private void submitForm(String s) {
        wd.findElement(By.xpath(s)).click();
    }

    private void toFollAdressName(String address, String vinnitsya) {
        wd.findElement(By.name(address)).click();
        wd.findElement(By.name(address)).clear();
        wd.findElement(By.name(address)).sendKeys(vinnitsya);
    }

    private void toFillNickName(String nickname, String kate) {
        toFollAdressName(nickname, kate);
    }

    private void toFillLastName(String lastname, String oleksienko) {
        toFollAdressName(lastname, oleksienko);
    }

    private void toFillFirstName(String firstname, String katya) {
        toFollAdressName(firstname, katya);
    }

    private void goToAddPage(String s) {
        wd.findElement(By.linkText(s)).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();
        }


    private boolean isElementPresent(By by) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}

