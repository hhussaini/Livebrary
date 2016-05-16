package com.glb.daos;

import com.glb.exceptions.ResourceHelperException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;

public class ConnectionUtil {
    
    @Resource(name="jdbc/glbdb")
    private static DataSource ds;
    
    // Get the connection
    public static Connection getConnection() throws ResourceHelperException {
        System.out.println("Inside ConnectionUtil.getConnection");
        Connection conn = null;
        try {
            // If ds is null, there's a db connection problem so we must get the
            // connection the old way
            if (ds == null) {
                 return getConnectionOldWay();
            }
            conn = ds.getConnection();
            
            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
    
    // Close a Connection, avoid closing if null and hide any SQLExceptions that occur.
    public static void closeConnection(Connection connection1) {
        DbUtils.closeQuietly(connection1);
    }
    
    // Close a ResultSet, avoid closing if null and hide any SQLExceptions that occur.
    public static void closeRS(ResultSet rs) {
        DbUtils.closeQuietly(rs);
    }
    
    // Close a Sttement, avoid closing if null and hide any SQLExceptions that occur.
    public static void closeStatement(Statement statement) {
        DbUtils.closeQuietly(statement);
    }
    
    // Close a connection, statement, and resultset, avoid closing if null and hide any SQLExceptions that occur.
    public static void closeAll(Connection conn1, Statement stmt, ResultSet rs) {
        DbUtils.closeQuietly(conn1, stmt, rs);
    }
    
    /**
     * Get connection in the old way. Use this when the code inside getConnection()
     * isn't working.
     */
    private static Connection getConnectionOldWay() throws ResourceHelperException {
        Connection conn = null;
        String dbURL = "jdbc:mysql://mysql2.cs.stonybrook.edu:3306/pmannarino?zeroDateTimeBehavior=convertToNull";
        String usr = "pmannarino";
        String pass = "108060069";
        String driver = "com.mysql.jdbc.Driver";
        try {            
            Class.forName(driver).newInstance();
            conn = (Connection) DriverManager.getConnection(dbURL, usr, pass);
        } catch (SQLException e) {
            System.err.println("%%% SQLException %%%" + e.getMessage());
            throw new ResourceHelperException(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("%%% Driver Class Not found %%%" + e);
            throw new ResourceHelperException(e.getMessage());
        } catch (InstantiationException ex) {       
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
    
    /*
     * //Test the connection
     *
     * public static void main(String[] args) throws Exception {
     * Connection conToUse = getConnection();
     * System.out.println(conToUse);
     *
     *  }
     */
}