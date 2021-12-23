/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;

/**
 *
 * @author predr
 */
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

    public Company(String name, String position, String period, User user) {
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

    public String getPeriod() {
        return period;
    }
    public User getUser(){
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
    public void setUser(User user){
        this.user=user;
    }

    @Override
    public String toString() {
        return "Company{" + "companyId=" + companyId + ", name=" + name + ", position=" + position + ", period=" + period + '}';
    }
   
}
    
    
    

