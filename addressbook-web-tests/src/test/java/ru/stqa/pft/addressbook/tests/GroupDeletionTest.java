package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

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
        //app.goTo().groupPage();
        //if (!app.group().isThereAGroup()) {
           // app.group().create(new GroupDate("Test1", "Test2", "Test3"));
       // }
        Groups before = app.group().all();
        GroupDate deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        //app.goTo().groupPage();
        Groups after = app.group().all();
        assertEquals(after.size(), before.size() -1);
        assertThat(after, equalTo(before.without(deletedGroup)));
        //for (int i = 0; i < after.size(); i++) {
            //Assert.assertEquals(before.get(i), after.get(i));
        }
        }