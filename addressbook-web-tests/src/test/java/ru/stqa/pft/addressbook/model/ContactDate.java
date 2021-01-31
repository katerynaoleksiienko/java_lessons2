package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactDate {
    private  int id = Integer.MAX_VALUE;
    private  String firstname;
    private  String lastname;
    private  String nickname;
    private  String company;
    private String address;
    private String group;
    private String mobilephone;
    private String homephone;
    private String workphone;
    private String email;
    private String allphone;
    private String email2;
    private String email3;
    private String allAddresses;
    private String allEmailAddresses;
    private File photo;

    public File getPhoto() {
        return photo;
    }

    public ContactDate withPhoto(File photo) {
        this.photo = photo;
        return this;

    }

    public ContactDate withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactDate withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public ContactDate withHomePhone(String homePhone) {
        this.homephone = homePhone;
        return this;
    }

    public ContactDate withWorkPhone(String workPhone) {
        this.workphone = workPhone;
        return this;
    }
    public ContactDate withEmail(String email) {
        this.email = email;
        return this;
    }


    public ContactDate withAllAddresses(String allAddresses) {
        this.allAddresses = allAddresses;
        return this;
    }

    public ContactDate withAllEmailAddresses(String allEmailAddresses) {
        this.allEmailAddresses = allEmailAddresses;
        return this;
    }
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

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getHomePhone() {
        return homephone;
    }

    public String getGroup() {
        return group;
    }
    public ContactDate withMobilePhone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }


    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }


    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilephone;
    }
    public String getEmail() {
        return email;
    }

    public String getWorkPhone() {
        return workphone;
    }
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }

    public String getAllPhone() {
        return allphone;
    }

public ContactDate withAllPhone(String allPhone) {
        this.allphone = allPhone;
        return this;
    }

    public String getAllAddresses() {
        return allAddresses;
    }


    public String getAllEmailAddresses() {
        return allEmailAddresses;
    }


    @Override
    public String toString() {
        return "ContactDate{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
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

    }

