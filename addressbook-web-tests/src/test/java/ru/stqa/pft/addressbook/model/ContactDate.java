package ru.stqa.pft.addressbook.model;

public class ContactDate {
    private final String firstname;
    private final String lastname;
    private final String nickname;
    private final String company;
    private final String address;
    private String group;

    public ContactDate(String firstname, String lastname, String nickname, String company, String address, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
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

    public String getGroup() {
        return group;
    }
}
