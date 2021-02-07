package ru.stqa.pft.mantis.tests;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class SkipTests extends TestBase{

    @Test
    public void testSkip() throws RemoteException, ServiceException, MalformedURLException, com.google.protobuf.ServiceException {
        int issueId = 2;
        skipIfNotFixed(issueId);
        System.out.println("NOT Ignored because of issue " + issueId);
    }
}
