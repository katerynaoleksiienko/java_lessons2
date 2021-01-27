package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactDate {
    private  int id = Integer.MAX_VALUE;
    private  String firstname;
    private  String lastname;
    private  String nickname;
    private  String company;
    private String address;
    private String group;

    public ContactDate withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactDate withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactDate withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactDate withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactDate withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactDate withGroup(String group) {
        this.group = group;
        return this;
    }

    public int getId() {
        return id;
    }

    public ContactDate withId(int id) {
        this.id = id;
        return this;
    }

    public String withFirstname() {
        return firstname;
    }

    public String withLastname() {
        return lastname;
    }

    public String withNickname() {
        return nickname;
    }

    public String withCompany() {
        return company;
    }

    public String withAddress() {
        return address;
    }

    public String withGroup() {
        return group;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDate that = (ContactDate) o;
        return  Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    @Override
    public String toString() {
        return "ContactDate{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
