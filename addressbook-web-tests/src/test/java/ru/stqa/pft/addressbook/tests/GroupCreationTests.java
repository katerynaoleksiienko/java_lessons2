package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.List;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation()  {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupDate> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupDate("Test1", "Test2", "Test3"));
    List<GroupDate> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() +1);
  }

}
