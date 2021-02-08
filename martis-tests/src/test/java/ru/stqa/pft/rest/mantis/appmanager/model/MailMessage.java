package ru.stqa.pft.rest.mantis.appmanager.model;

public class MailMessage {
    public String to;
    public String text;

    public MailMessage (String to, String text) {
        this.to = to;
        this.text = text;
    }
}
