/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import data.Contact;
import data.Education;
import data.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author predr
 */
public class UserDao {
    private static final UserDao instance=new UserDao();
    private UserDao(){
        
    }
    public static UserDao getInstance(){
        return instance;
    }
    public void create(Connection con,User user)throws SQLException{
       PreparedStatement ps=null;
       ResultSet rs=null;
       try{
           Integer fkEducation = null;
            if (user.getEducation() != null) {
                fkEducation = EducationDao.getInstance().create(con,user.getEducation());
            }
            Integer fkContact = null;
            if (user.getContact() != null) {
                fkContact = ContactDao.getInstance().create(con,user.getContact());
            }

         ps=con.prepareStatement("INSERT INTO user(username,name,surname,aboutMe,contactId,educationId) VALUES(?,?,?,?,?,?)");
         ps.setString(1,user.getUsername());
         ps.setString(2,user.getName());
         ps.setString(3,user.getSurname());
         ps.setString(4,user.getAboutMe());
         ps.setInt(5,fkContact);
         ps.setInt(6,fkEducation);
         ps.execute();
            //rs=ps.getGeneratedKeys();
            //rs.next();
            
       }finally{
           ResourcesManager.closeResources(rs,ps);
       }
    }
    public void update(Connection con,User user)throws SQLException{
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement("UPDATE user SET name=?,surname=?,aboutMe=? WHERE username=?");
            ps.setString(1,user.getName());
            ps.setString(2,user.getSurname());
            ps.setString(3,user.getAboutMe());
            ps.setString(4,user.getUsername());
            ps.executeUpdate();
            
            if(user.getContact()!=null){
                ContactDao.getInstance().update(con,user.getContact());
            }
            if(user.getEducation()!=null){
                EducationDao.getInstance().update(con, user.getEducation());
            }
        }finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    public void delete(Connection con,User user)throws SQLException{
        PreparedStatement ps=null;
        try{
            CompanyDao.getInstance().delete(user,con);
            ps=con.prepareStatement("DELETE FROM user WHERE username=?");
            ps.setString(1,user.getUsername());
            ps.execute();
            if (user.getEducation() != null) {
                EducationDao.getInstance().delete(con,user.getEducation().getEducationId());
            }

            
            if (user.getContact() != null) {
                ContactDao.getInstance().delete(con,user.getContact().getContactId());
            }
           
        }finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    public void deleteEducation(Connection con, int educationId)throws SQLException{
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement("DELETE FROM user WHERE educationId=?");
            ps.setInt(1,educationId);
            ps.execute();
        }finally{
            ResourcesManager.closeResources(null,ps);
        }
    }
    public void deleteContact(Connection con, int contactId)throws SQLException{
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement("DELETE FROM user WHERE contactId=?");
            ps.setInt(1,contactId);
            ps.execute();
        }finally{
            ResourcesManager.closeResources(null,ps);
        }
    }
    
    public User find(Connection con,String username)throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        User user=null;
        try{
            ps=con.prepareStatement("SELECT * FROM user WHERE username=?");
            ps.setString(1,username);
            rs=ps.executeQuery();
            if(rs.next()){
                Education education=EducationDao.getInstance().find(con, rs.getInt("educationId"));
                Contact contact=ContactDao.getInstance().find(con, rs.getInt("contactId"));
                user=new User(username,rs.getString("name"),rs.getString("surname"),rs.getString("aboutMe"),contact,education);
            }
            return user;
        }finally{
            ResourcesManager.closeResources(rs, ps);
        }
}
    
    
}
