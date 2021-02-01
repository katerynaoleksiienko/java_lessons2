package ru.stqa.pft.addressbook.tests;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validGroups(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new GroupDate().withName("Test1").withHeader("header 1").withFooter("footer1")});
        list.add(new Object[]{new GroupDate().withName("Test2").withHeader("header 2").withFooter("footer2")});
        list.add(new Object[]{new GroupDate().withName("Test3").withHeader("header 3").withFooter("footer3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreate(GroupDate group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
   app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g)  -> g.getId()).max().getAsInt()))));
  }
    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupDate group = new GroupDate().withName("test2'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }
}