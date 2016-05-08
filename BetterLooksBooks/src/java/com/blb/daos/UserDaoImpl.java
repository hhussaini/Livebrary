package com.blb.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.blb.objects.User;

public class UserDaoImpl extends JdbcDaoSupportImpl implements UserDao {
    
    @Override
    public int save(User user) {
        String sql = "insert into USERS values(?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conToUse = null;
        PreparedStatement ps = null;
        int status = 0;
        try {
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getStreet());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getZipcode());
            ps.setString(10, user.getPhoneNumber());
            ps.setString(11, user.getType());
            ps.setString(12, user.getFirstServerUsername());
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            ConnectionUtil.closeStatement(ps);
        }
        return status;
    }

    @Override
    public User getUser(String username, String password) {
        String sql = "select U.username, U.password, U.firstname, U.lastname, U.street, "
                + "U.city, U.state, U.zipcode, U.phoneNumber, U.email, U.type, U.firstServerUsername"
                        + "   from USERS U "
                        + "   where U.username = '" + username + "'"
                        + "   and U.password = '" + password + "'";        
        Connection conToUse = null;
        ResultSet res = null;
        Statement stmt = null;
        try {
            conToUse = getConnection();
            stmt = (Statement) conToUse.createStatement();
            res = stmt.executeQuery(sql);
            User user = new User();
            int count = 0;
            while (res.next()) {
              String user_name = res.getString("username");
              String pass_word = res.getString("password");  
              String firstname = res.getString("firstname");
              String lastname = res.getString("lastname");
              String street = res.getString("street"); 
              String city = res.getString("city"); 
              String state = res.getString("state"); 
              String zipcode = res.getString("zipcode"); 
              String phoneNumber = res.getString("phoneNumber"); 
              String email = res.getString("email");
              String type = res.getString("type");
              String firstServerUsername = res.getString("firstServerUsername");
              user.setUsername(user_name);
              user.setPassword(pass_word);
              user.setFirstName(firstname);
              user.setLastName(lastname);
              user.setStreet(street);
              user.setCity(city);
              user.setState(state);
              user.setZipcode(zipcode);
              user.setPhoneNumber(phoneNumber);
              user.setEmail(email);
              user.setType(type);
              user.setFirstServerUsername(firstServerUsername);
              count++;
            }
            if (count != 1) {
                return null;
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionUtil.closeStatement(stmt);
        }
        
        return null;
    }
}