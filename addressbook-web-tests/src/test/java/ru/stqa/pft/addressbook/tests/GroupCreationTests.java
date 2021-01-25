package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Set<GroupDate> before = app.group().all();
    GroupDate group = new GroupDate().withName("test2");
   app.group().create(group);
   // app.group().fillTheForm(group);
   // app.group().submitForm();
    app.goTo().groupPage();
    Set<GroupDate> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);


    group.withId(after.stream().mapToInt((g)  -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }
}