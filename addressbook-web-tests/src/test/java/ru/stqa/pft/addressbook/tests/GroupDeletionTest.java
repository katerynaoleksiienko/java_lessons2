package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.List;

public class GroupDeletionTest extends TestBase {
    @BeforeMethod
    public void  ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupDate("Test1", "Test2", "Test3"));
        }

    }

    @Test
    public void testGroupDeletion() {
        app.goTo().groupPage();
        if (!app.group().isThereAGroup()) {
            app.group().create(new GroupDate("Test1", "Test2", "Test3"));
        }
        List<GroupDate> before = app.group().list();
        int index = before.size() -1;
        app.group().delete(index);
        List<GroupDate> after = app.group().list();
        Assert.assertEquals(after.size(), index);
        before.remove(index);

        for (int i = 0; i < after.size(); i++) {
            Assert.assertEquals(before.get(i), after.get(i));
        }
        }


}
