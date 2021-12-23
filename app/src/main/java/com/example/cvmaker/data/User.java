package com.example.cvmaker.data;

import java.io.Serializable;

public class User implements Serializable{
    private String username;
    private String name;
    private String surname;
    private String aboutMe;
    private Education education;
    private Contact contact;

    public User(){

    }
    public User(String username,String name, String surname,String aboutMe, Education education, Contact contact) {
        this.username=username;
        this.name = name;
        this.surname = surname;
        this.aboutMe = aboutMe;
        this.education = education;
        this.contact = contact;
    }

    public User(String name, String surname,String aboutMe, Education education, Contact contact) {
        this.name = name;
        this.surname = surname;
        this.aboutMe = aboutMe;
        this.education = education;
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public Education getEducation() {
        return education;
    }

    public Contact getContact() {
        return contact;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", name=" + name + ", surname=" + surname + ", aboutMe=" + aboutMe + ", education=" + education + ", contact=" + contact + '}';
    }






}
