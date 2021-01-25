package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.List;
import java.util.Set;

public class GroupDeletionTest extends TestBase {
    @BeforeMethod
    public void  ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupDate().withName("test1"));
        }

    }

    @Test
    public void testGroupDeletion() throws Exception {
        app.goTo().groupPage();
        //if (!app.group().isThereAGroup()) {
           // app.group().create(new GroupDate("Test1", "Test2", "Test3"));
       // }
        Set<GroupDate> before = app.group().all();
        GroupDate deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        app.goTo().groupPage();
        Set<GroupDate> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() -1);
        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
        //for (int i = 0; i < after.size(); i++) {
            //Assert.assertEquals(before.get(i), after.get(i));
        }
        }