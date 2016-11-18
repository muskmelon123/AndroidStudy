package com.example.lvhang.androidstudyproject.DatabaseStudy;

/**
 * Created by lv.hang on 2016/11/18.
 */

public class Contact {

    private String name;
    private long contactId;

    public Contact() {
    }

    public Contact(String name, long contactId) {
        this.name = name;
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }
}
