package ru.stqa.pft.rest.addressbook.model;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.File;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;


@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")

public class ContactDate {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private  int id = Integer.MAX_VALUE;
    @Column(name = "firstname")
    private  String firstname;

    @Column(name = "lastname")
    private  String lastname;

    @Column(name = "nickname")
    private  String nickname;

    @Column(name = "company")
    private  String company;

    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilephone;

    @Column(name = "home")
    @Type(type = "text")
    private String homephone;

    @Column(name = "work")
    @Type(type = "text")
    private String workphone;

    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Transient
    private String allphone;

    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String allAddresses;

    @Transient
    private String allEmailAddresses;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupDate> groups = new HashSet<GroupDate>();
    public ContactDate withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public File getPhoto() {
        return new File (photo);
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
    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactDate inGroup(GroupDate group) {
        groups.add(group);
        return this; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDate that = (ContactDate) o;
        return  Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(company, that.company) &&
                Objects.equals(address, that.address) &&
                Objects.equals(mobilephone, that.mobilephone) &&
                Objects.equals(email, that.email);

    }

    @Override
    public String toString() {
        return "ContactDate{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, nickname, company, address, mobilephone, email);
    }

    }

