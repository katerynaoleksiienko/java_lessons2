package ru.stqa.pft.mantis.appmanager;
import ru.stqa.pft.mantis.appmanager.model.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;

import java.util.List;
public class PasswordHelper extends HelperBase{

    public PasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти']"));
        click(By.linkText("управление"));
        click(By.linkText("Управление пользователями"));
        click(By.linkText("user1"));
        List<UserData> usersList = app.loginAndVerification().getUserWithoutAdmin();
        click(By.linkText((usersList.iterator().next().getUsername())));
        click(By.xpath("//input[@value='Сбросить пароль']"));
        click(By.linkText("Продолжить"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("пароль"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("span.bigger-110"));
    }

    public List<UserData> getAllUsersList() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<UserData> result = session.createQuery("from UserData").list();
        session.close();
        return result;
    }

    public List<UserData> getUserWithoutAdmin() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<UserData> result = session.createQuery("from UserData where username != 'administrator'").list();
        session.close();
        return result;
    }
}