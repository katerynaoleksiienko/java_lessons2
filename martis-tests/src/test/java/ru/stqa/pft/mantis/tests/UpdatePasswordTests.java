package ru.stqa.pft.mantis.tests;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.appmanager.model.MailMessage;
import ru.stqa.pft.mantis.appmanager.model.UserData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class UpdatePasswordTests extends TestBase {

        @BeforeMethod
        public void startMailServer() {
            app.mail().start();
        }

        @Test
        public void testUpdatePassword() throws IOException, MessagingException {
            app.loginAndVerification().start("administrator", "root");
            List<UserData> usersList = app.loginAndVerification().getUserWithoutAdmin();
            String username = usersList.get(0).getUsername();
            String email = usersList.get(0).getEmail();
            List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            app.loginAndVerification().finish(confirmationLink, "test");
            HttpSession session = app.newSession();
            assertTrue(session.login(username));
            assertTrue(session.isLoggedInAs(username));
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
