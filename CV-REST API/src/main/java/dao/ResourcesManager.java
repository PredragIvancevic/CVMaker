/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import exception.MyException;

/**
 *
 * @author predr
 */


public class ResourcesManager {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/mycv1?user=root&password=");//definisem gde mi se nalazi baza, getConnection moze da baci SQLException
        return con;
    }

    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
//ovde smo stavili obe jer mogu da prosledim null, ovde je najopstiji slucaj pokriven, ako npr.meni ne treba rs objekat, tu kao prvi parametar stavim null 
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public static void closeConnection(Connection con) throws MyException {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new MyException("Failed to close database connection.", ex);
            }
        }
    }

    public static void rollbackTransactions(Connection con) throws MyException {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new MyException("Failed to rollback database transactions.", ex);
            }
        }
    }
}





