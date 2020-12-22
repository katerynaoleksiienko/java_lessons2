package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    initGroupCreation();
    fillTheForm(new GroupDate("Test1", "Test2", "Test3"));
    submitForm();
    returnToGroupPage();
  }

}
