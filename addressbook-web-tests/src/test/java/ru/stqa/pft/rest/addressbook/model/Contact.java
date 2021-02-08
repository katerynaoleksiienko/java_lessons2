package ru.stqa.pft.rest.addressbook.model;
import com.google.common.collect.ForwardingSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;


public class Contact extends ForwardingSet<ContactDate> {

    private Set<ContactDate> delegate;

    public Contact(Contact contacts) {
        this.delegate = new HashSet<ContactDate>(contacts.delegate);
    }

    public Contact() {
        this.delegate = new HashSet<ContactDate>();
    }
    public Contact(Collection<ContactDate> contact) {
        this.delegate = new HashSet<ContactDate>(contact);
    }

    @Override
    protected Set<ContactDate> delegate() {
        return delegate;
    }

    public Contact withAdded(ContactDate contact){
        Contact contacts = new Contact(this);
        contacts.add(contact);
        return contacts;
    }
    public Contact without(ContactDate contact){
        Contact contacts = new Contact(this);
        contacts.remove(contact);
        return contacts;
    }
}
