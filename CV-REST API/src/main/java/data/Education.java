/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author predr
 */
import java.io.Serializable;

public class Education implements Serializable{
    private int educationId=-1;
    private String bachelor;
    private String master;
    private String doctoral;
    private String languages;
    private String highSchool;

    
public Education(){
    
}
    public Education(int educationId, String bachelor, String master, String doctoral,String languages,String highSchool) {
        this.educationId = educationId;
        this.bachelor=bachelor;
        this.master = master;
        this.doctoral = doctoral;
        this.languages=languages;
        this.highSchool=highSchool;
    }

    public Education(String bachelor, String master, String doctoral, String languages, String highSchool) {
        this.bachelor = bachelor;
        this.master = master;
        this.doctoral = doctoral;
        this.languages = languages;
        this.highSchool = highSchool;
    }

    public int getEducationId() {
        return educationId;
    }

    public String getBachelor() {
        return bachelor;
    }

    public String getMaster() {
        return master;
    }

    public String getDoctoral() {
        return doctoral;
    }

    public String getLanguages() {
        return languages;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setBachelor(String bachelor) {
        this.bachelor = bachelor;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public void setDoctoral(String doctoral) {
        this.doctoral = doctoral;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    @Override
    public String toString() {
        return "Education{" + "educationId=" + educationId + ", bachelor=" + bachelor + ", master=" + master + ", doctoral=" + doctoral + ", languages=" + languages + ", highSchool=" + highSchool + '}';
    }

    
}

