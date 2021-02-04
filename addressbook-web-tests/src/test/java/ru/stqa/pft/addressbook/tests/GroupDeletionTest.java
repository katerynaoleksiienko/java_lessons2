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
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.db().group();
        assertThat(after, equalTo(before.without(deletedGroup)));
        }
        }