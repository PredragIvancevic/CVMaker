/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import data.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author predr
 */
public class ContactDao {
    private static final ContactDao instance=new ContactDao();
    private ContactDao(){
        
    }
    public static ContactDao getInstance(){
        return instance;
    }
    public int create(Connection con,Contact contact)throws SQLException{
       PreparedStatement ps=null;
       ResultSet rs=null;
       int id=-1;
       try{
         ps=con.prepareStatement("INSERT INTO contact(phoneNumber,email,linkedin,skype) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
         ps.setString(1,contact.getPhoneNumber());
         ps.setString(2,contact.getEmail());
         ps.setString(3,contact.getLinkedin());
         ps.setString(4,contact.getSkype());
         ps.execute();
         rs=ps.getGeneratedKeys();
         rs.next();
         id=rs.getInt(1);        
       }finally{
           ResourcesManager.closeResources(rs,ps);
       }
       return id;
    }
    public void update(Connection con,Contact contact)throws SQLException{
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement("UPDATE contact SET phoneNumber=?,email=?,linkedin=?,skype=? WHERE contactId=?");
            ps.setString(1,contact.getPhoneNumber());
            ps.setString(2,contact.getEmail());
            ps.setString(3,contact.getLinkedin());
            ps.setString(4,contact.getSkype());
            ps.setInt(5,contact.getContactId());
            ps.execute();
        }finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    public void delete(Connection con,int contactId)throws SQLException{
        PreparedStatement ps=null;
        try{
         
            UserDao.getInstance().deleteContact(con,contactId);
            ps=con.prepareStatement("DELETE FROM contact WHERE contactId=?");
            ps.setInt(1,contactId);
            ps.execute();
        }finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    public Contact find(Connection con,int contactId)throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        Contact contact=null;
        try{
            ps=con.prepareStatement("SELECT * FROM contact WHERE contactId=?");
            ps.setInt(1,contactId);
            rs=ps.executeQuery();
            if(rs.next()){
                contact=new Contact(contactId,rs.getString("phoneNumber"),rs.getString("email"),rs.getString("linkedin"),rs.getString("skype"));
            }
            return contact;
        }finally{
            ResourcesManager.closeResources(rs, ps);
        }
}
}
