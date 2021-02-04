package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void  ensurePreconditions() {
       // app.goTo().groupPage();
        if (app.db().group().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupDate().withName("test1"));
        }

    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().group();
        GroupDate modifiedGroup = before.iterator().next();
        GroupDate group = new GroupDate()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().group();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
