package ru.stqa.pft.rest.mantis.tests;
import org.testng.annotations.Test;

import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.rest.mantis.appmanager.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends  TestBase {
   // @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);
        app.james().createUser(user, password);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationlink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationlink, password);
        assertTrue(app.newSession().login(user));    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

   // @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
