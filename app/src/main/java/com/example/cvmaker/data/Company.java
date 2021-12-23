package com.example.cvmaker.data;

import java.io.Serializable;


public class Company implements Serializable {
    private int companyId=-1;
    private String name;
    private String position;
    private String period;
    private User user;

    public Company(){

    }

    public Company(int companyId,String name, String position, String period, User user) {
        this.companyId=companyId;
        this.name = name;
        this.position = position;
        this.period = period;
        this.user=user;
    }

    public Company(String name, String position, String period,User user) {
        this.name = name;
        this.position = position;
        this.period = period;
        this.user=user;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
    public User getUser(){
        return user;
    }

    public String getPeriod() {
        return period;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setUser(User user){
        this.user=user;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


    @Override
    public String toString() {
        return  "Company name:"+" " + name + ", position:"+" " + position + ", period:"+" " + period;
    }

}
