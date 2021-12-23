/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContactDao;
import dao.EducationDao;
import dao.ResourcesManager;
import dao.UserDao;
import data.Education;
import data.User;
import exception.MyException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author predr
 */
public class UserService {
    private static final UserService instance = new UserService();

    private UserService() {
    }

    public static UserService getInstance() {
        return instance;
    }
    public void addNewUser(User user)throws MyException{
      Connection con=null; 
      try{
       con= ResourcesManager.getConnection();  
       con.setAutoCommit(false);
       UserDao.getInstance().create(con,user);
       con.commit();
      }catch(SQLException ex){
          ResourcesManager.rollbackTransactions(con);
      throw new MyException("Failed to add new user " + user, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    public void addNewEducation(Education education)throws MyException{
      Connection con=null; 
      try{
       con= ResourcesManager.getConnection();  
       EducationDao.getInstance().create(con,education);
      }catch(SQLException ex){
      throw new MyException("Failed to add new education " + education, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteUser(String username)throws MyException{
        Connection con=null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            User user = UserDao.getInstance().find(con,username);
            if (user != null) {
                UserDao.getInstance().delete(con,user);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new MyException("Failed to delete user with username " + username, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
   /* public void deleteContact(int contactId)throws MyException{
        Connection con=null;
        try{
            con=ResourcesManager.getConnection();
            con.setAutoCommit(false);
            
        }catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new MyException("Failed to delete contact with id " + contactId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
*/
    public void updateUser(User user) throws MyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            UserDao.getInstance().update(con,user);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new MyException("Failed to update user " + user, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateEducation(User user)throws MyException{
        Connection con=null;
        try{
            con=ResourcesManager.getConnection();
            EducationDao.getInstance().update(con, user.getEducation());
        }catch (SQLException ex) {
            
            throw new MyException("Failed to update education for user: " + user, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateContact(User user)throws MyException{
        Connection con=null;
        try{
            con=ResourcesManager.getConnection();
            ContactDao.getInstance().update(con,user.getContact());
        }catch (SQLException ex) {
            
            throw new MyException("Failed to update contact for user: " + user, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
     public User findUser(String username) throws MyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return UserDao.getInstance().find(con,username);

        } catch (SQLException ex) {
            throw new MyException("Failed to find user with id " + username, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
