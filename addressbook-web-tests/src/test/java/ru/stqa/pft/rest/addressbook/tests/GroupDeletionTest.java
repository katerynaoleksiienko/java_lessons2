package ru.stqa.pft.rest.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.addressbook.model.GroupDate;
import ru.stqa.pft.rest.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupDeletionTest extends TestBase {
    @BeforeMethod
    public void  ensurePreconditions() {
       // app.goTo().groupPage();
        if (app.db().group().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupDate().withName("test1"));
        }

    }

    @Test
    public void testGroupDeletion() throws Exception {
        Groups before = app.db().group();
        GroupDate deletedGroup = before.iterator().next();
        app.goTo().groupPage();
        app.group().delete(deletedGroup);
        app.goTo().groupPage();
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.db().group();
        assertThat(after, equalTo(before.without(deletedGroup)));
        }
        }