package ru.stqa.pft.mantis.appmanager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import static jdk.nashorn.internal.objects.NativeJava.type;
import static ru.stqa.pft.mantis.tests.TestBase.app;

public class RegistrationHelper extends HelperBase {
    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup'"));

       // click(By.xpath("//form[@id='signup-form']/fieldset/input[2]"));
    }

    public void finish(String confirmationlink, String password) {
        wd.get(confirmationlink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("input[value='Update User']"));
    }
}
