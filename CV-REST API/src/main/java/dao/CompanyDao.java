/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import data.Company;
import data.User;
import exception.MyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author predr
 */
public class CompanyDao {
    private static final CompanyDao instance=new CompanyDao();
    private CompanyDao(){
        
    }
    public static CompanyDao getInstance(){
        return instance;
    }
    public int create(Connection con,Company company)throws SQLException,MyException{
       PreparedStatement ps=null;
       ResultSet rs=null;
       int id=-1;
       try{
        
         ps=con.prepareStatement("INSERT INTO experience(companyName,position,period,username)VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
         ps.setString(1,company.getName());
         ps.setString(2,company.getPosition());
         ps.setString(3,company.getPeriod());
         User user=UserDao.getInstance().find(con,company.getUser().getUsername());
         if(user==null){
             throw new MyException("User"+company.getUser()+"doesn't exist.");
         }
         ps.setString(4,user.getUsername());
         ps.execute();
            rs=ps.getGeneratedKeys();
            rs.next();
            id=rs.getInt(1);
       }finally{
           ResourcesManager.closeResources(rs,ps);
       }
       return id;
    }
    public void update(Connection con,Company company)throws SQLException{
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement("UPDATE experience SET companyName=?,position=?,period=? WHERE experienceId=?");
            ps.setString(1,company.getName());
            ps.setString(2,company.getPosition());
            ps.setString(3,company.getPeriod());
            ps.setInt(4,company.getCompanyId());
            ps.execute();
        }finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    public void delete(Connection con,Company company)throws SQLException{
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement("DELETE FROM experience WHERE experienceId=?");
            ps.setInt(1,company.getCompanyId());
            ps.execute();
        }finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void delete(User user, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM experience WHERE username=?");
            ps.setString(1, user.getUsername());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    public Company find(Connection con,int companyId)throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        Company company=null;
        try{
            ps=con.prepareStatement("SELECT* FROM experience WHERE experienceId=?");
            ps.setInt(1,companyId);
            rs=ps.executeQuery();
            if(rs.next()){
                User user=UserDao.getInstance().find(con, rs.getString("username"));
                company=new Company(companyId,rs.getString("companyName"),rs.getString("position"),rs.getString("period"),user);
            }
            return company;
        }finally{
            ResourcesManager.closeResources(rs,ps);
        }
    }
    public List<Company> findAll(Connection con, String username)throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Company> companies=new ArrayList<>();
        try{
            ps=con.prepareStatement("SELECT* FROM experience WHERE username=?");
            ps.setString(1, username);
            User user=UserDao.getInstance().find(con,username);
            rs=ps.executeQuery();
            while(rs.next()){
                companies.add(new Company(rs.getInt("experienceId"),rs.getString("companyName"),rs.getString("position"),rs.getString("period"),user));
            }
            return companies;
        }finally{
            ResourcesManager.closeResources(rs, ps);
        }
    }
}
