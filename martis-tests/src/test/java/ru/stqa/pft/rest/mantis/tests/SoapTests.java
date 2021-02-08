package ru.stqa.pft.rest.mantis.tests;
import com.google.protobuf.ServiceException;
import ru.stqa.pft.rest.mantis.appmanager.model.Issue;
import ru.stqa.pft.rest.mantis.appmanager.model.Project;
import org.testng.annotations.Test;
//import javax.xml.rpc.ServiceException;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;
public class SoapTests extends TestBase {

    @Test()
    public void testGetProjects() throws RemoteException, ServiceException, MalformedURLException, javax.xml.rpc.ServiceException {
        skipIfNotFixed(2);
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test(alwaysRun = false)
    public void testCreateIssue() throws RemoteException, SecurityException, MalformedURLException, javax.xml.rpc.ServiceException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                .withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}