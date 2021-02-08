package ru.stqa.pft.rest.mantis.tests;
import java.io.File;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.protobuf.ServiceException;
import ru.stqa.pft.rest.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.SkipException;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.io.IOException;


public class TestBase {

    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));



    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException, javax.xml.rpc.ServiceException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL("http://localhost/mantisbt-2.24.4/api/soap/mantisconnect.php"));
    }

    boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issueIdTest = mc.mc_issue_get("administrator", "test", BigInteger.valueOf(issueId));
        if (issueIdTest.getResolution().getName().equals("fixed")) {
            return false;
        } else {
            return true;
        }
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

}