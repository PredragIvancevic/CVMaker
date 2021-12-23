/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import data.Education;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author predr
 */
public class EducationDao {
     private static final EducationDao instance=new EducationDao();
    private EducationDao(){
        
    }
    public static EducationDao getInstance(){
        return instance;
    }
    public int create(Connection con,Education education)throws SQLException{
       PreparedStatement ps=null;
       ResultSet rs=null;
       int id=-1;
       try{
         ps=con.prepareStatement("INSERT INTO education(bachelor,master,doctoral,languages,highSchool) VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
         ps.setString(1,education.getBachelor());
         ps.setString(2,education.getMaster());
         ps.setString(3,education.getDoctoral());
         ps.setString(4,education.getLanguages());
         ps.setString(5,education.getHighSchool());
         ps.execute();
            rs=ps.getGeneratedKeys();
            rs.next();
            id=rs.getInt(1);
       }finally{
           ResourcesManager.closeResources(rs,ps);
       }
       return id;
    }
    public void update(Connection con,Education education)throws SQLException{
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement("UPDATE education SET bachelor=?,master=?,doctoral=?,languages=?,highSchool=? WHERE educationId=?");
            ps.setString(1,education.getBachelor());
            ps.setString(2,education.getMaster());
            ps.setString(3,education.getDoctoral());
            ps.setString(4,education.getLanguages());
            ps.setString(5,education.getHighSchool());
            ps.setInt(6,education.getEducationId());
            ps.execute();
        }finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    public void delete(Connection con,int educationId)throws SQLException{
        PreparedStatement ps=null;
        try{
             UserDao.getInstance().deleteEducation(con,educationId);
            ps=con.prepareStatement("DELETE FROM education WHERE educationId=?");
            ps.setInt(1,educationId);
            ps.execute();
        }finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    public Education find(Connection con,int educationId)throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        Education education=null;
        try{
            ps=con.prepareStatement("SELECT* FROM education WHERE educationId=?");
            ps.setInt(1,educationId);
            rs=ps.executeQuery();
            if(rs.next()){
                education=new Education(educationId,rs.getString("bachelor"),rs.getString("master"),rs.getString("doctoral"),rs.getString("languages"),rs.getString("highSchool"));
            }
            return education;
        }finally{
            ResourcesManager.closeResources(rs, ps);
        }
}
}
