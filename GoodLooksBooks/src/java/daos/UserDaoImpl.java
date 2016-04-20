package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.User;

import org.apache.commons.dbutils.DbUtils;

public class UserDaoImpl extends JdbcDaoSupportImpl implements UserDao {
    
    private Statement stmt = null;
    private ResultSet res = null;
    
    @Override
    public int save(User user) {
        String sql = "insert into USERS values(?,?,?,?,?)";
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
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }

    // TODO
    @Override
    public User getUser(String username, String password) {
        String sql = "select U.username, U.password, U.firstname, U.lastname, U.street, U.city, U.state, U.zipcode, U.phoneNumber, U.email, U.type, U.accessCode"
                        + "   from USER U "
                        + "   where U.username = " + username
                        + "   and U.password = " + password;
        
        Connection conToUse = null;
        PreparedStatement ps = null;
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
              String accessCode = res.getString("accessCode");
              count++;
            }
            if (count != 1) {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        
        return null;
    }
}