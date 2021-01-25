package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupDate {

    private int id = Integer.MAX_VALUE;
    private  String name;
    private  String header;
    private  String footer;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDate groupDate = (GroupDate) o;
        return Objects.equals(name, groupDate.name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "GroupDate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }
    public GroupDate withName(String name) {
        this.name = name;
        return this;
    }

    public GroupDate withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupDate withFooter(String footer) {
        this.footer = footer;
        return this;
    }
    public GroupDate withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
}
}