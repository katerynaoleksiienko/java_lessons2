package ru.stqa.pft.addressbook;

public class ContactDate {
    private final String firstname;
    private final String lastname;
    private final String nickname;
    private final String company;
    private final String address;

    public ContactDate(String firstname, String lastname, String nickname, String company, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
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
}
