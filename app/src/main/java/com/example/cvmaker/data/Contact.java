package com.example.cvmaker.data;

import java.io.Serializable;
public class Contact implements Serializable {
    private int contactId=-1;
    private String phoneNumber;
    private String email;
    private String linkedin;
    private String skype;

    public Contact(){

    }

    public Contact(String phoneNumber, String email, String linkedin, String skype) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.linkedin = linkedin;
        this.skype = skype;
    }
    public Contact(int contactId,String phoneNumber, String email, String linkedin, String skype) {
        this.contactId=contactId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.linkedin = linkedin;
        this.skype = skype;
    }

    public int getContactId() {
        return contactId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getSkype() {
        return skype;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Override
    public String toString() {
        return "Contact{" + "contactId=" + contactId + ", phoneNumber=" + phoneNumber + ", email=" + email + ", linkedin=" + linkedin + ", skype=" + skype + '}';
    }

}
