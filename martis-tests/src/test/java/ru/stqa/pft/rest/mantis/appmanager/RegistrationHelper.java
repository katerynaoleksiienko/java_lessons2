package ru.stqa.pft.rest.mantis.appmanager;
import jdk.nashorn.internal.objects.NativeJava;
import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {
    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        NativeJava.type(By.name("username"), username);
        NativeJava.type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup'"));

       // click(By.xpath("//form[@id='signup-form']/fieldset/input[2]"));
    }

    public void finish(String confirmationlink, String password) {
        wd.get(confirmationlink);
        NativeJava.type(By.name("password"), password);
        NativeJava.type(By.name("password_confirm"), password);
        click(By.xpath("input[value='Update User']"));
    }
}
