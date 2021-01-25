package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupDate;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends  HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitForm() { click(By.name("submit")); }

    public void fillTheForm(GroupDate groupDate) {
        type(By.name("group_name"), groupDate.getName());
        type(By.name("group_header"), groupDate.getHeader());
        type(By.name("group_footer"), groupDate.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectGroups() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }


    public void create(GroupDate group) {
        initGroupCreation();
        fillTheForm(group);
        submitForm();
        returnToGroupPage();
    }
    public void modify(GroupDate group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillTheForm(group);
        submitGroupModification();
        returnToGroupPage();
    }
        public void delete(GroupDate group) {
        selectGroupById(group.getId());
        deleteSelectGroups();
        returnToGroupPage();

    }

   // public int getGroupCount() {
    //    return wd.findElements(By.name("selected[]")).size();
   // }

    public List<GroupDate> list() {
        List<GroupDate> groups = new ArrayList<GroupDate>();
        List<WebElement>  elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupDate().withId(id).withName(name));
        }

        return groups;
    }
    public Set<GroupDate> all() {
        Set<GroupDate> groups = new HashSet<GroupDate>();
        List<WebElement>  elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupDate().withId(id).withName(name));
        }

        return groups;
    }
}
