package ru.stqa.pft.rest.mantis.tests;
import ru.stqa.pft.rest.mantis.appmanager.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class RegistrationSmtpTests extends TestBase {

        @BeforeMethod
        public void startMailServer() {
            app.mail().start();
        }

        @Test
        public void testRegistrationSmtp() throws IOException, MessagingException {
            long now = System.currentTimeMillis();
            String username = "user" + now;
            String email = "user" + now + "@localhost.localdomain";
            app.registration().start(username, email);
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            app.registration().finish(confirmationLink, "test");
            assertTrue(app.newSession().login(username));
        }

        private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
            MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
            VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
            return regex.getText(mailMessage.text);
        }


        @AfterMethod(alwaysRun = true)
        public void stopMailServer() {
            app.mail().stop();
        }
    }
