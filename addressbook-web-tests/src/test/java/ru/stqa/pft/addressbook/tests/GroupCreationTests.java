package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillTheForm(new GroupDate("Test1", "Test2", "Test3"));
    app.getGroupHelper().submitForm();
    app.getGroupHelper().returnToGroupPage();
  }

}
