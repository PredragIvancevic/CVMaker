/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CompanyDao;
import dao.ResourcesManager;
import data.Company;
import exception.MyException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author predr
 */

public class CompanyService {
    
    private static final CompanyService instance=new CompanyService();
    
    private CompanyService(){
        
    }
    
    public static CompanyService getInstance(){
        return instance;
    }
    
    public int addNewCompany(Company company) throws MyException{
     Connection con=null;
     
     try{
         con= ResourcesManager.getConnection();  
      // con.setAutoCommit(false);
      return CompanyDao.getInstance().create(con, company);
       
      // con.commit();
     }catch (SQLException ex) {
            throw new MyException("Failed to add new company " + company, ex);
     
    } finally {
            ResourcesManager.closeConnection(con);
        }
    
}
    
    public Company findCompany(int companyId)throws MyException{
        Connection con=null;
        
        try{
            con=ResourcesManager.getConnection();
         
         return CompanyDao.getInstance().find(con, companyId);
           
        }catch(SQLException ex){
           
            throw new MyException("Failed to find company with id " + companyId, ex);
        }finally{
            ResourcesManager.closeConnection(con);
        }
        
    }
    
    public List<Company> findAllCompanies(String username)throws MyException{
        Connection con=null;
        try{
            con=ResourcesManager.getConnection();
            return CompanyDao.getInstance().findAll(con, username);
        }catch(SQLException ex){
            
           throw new MyException("Failed to find companies for username " + username, ex);
          
        }finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteCompany(int companyId) throws MyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Company company = CompanyDao.getInstance().find(con,companyId);
            if (company != null) {
                CompanyDao.getInstance().delete(con,company);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new MyException("Failed to delete company with id " + companyId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateCompany(Company company) throws MyException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            CompanyDao.getInstance().update(con,company);
        } catch (SQLException ex) {
           
            throw new MyException("Failed to update company" + company, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    
    
}