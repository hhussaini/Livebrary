package services;

import factories.DaoFactory;
import daos.UserDao;
import exceptions.ResourceHelperException;
import java.sql.Connection;
import java.sql.SQLException;
import objects.User;
import daos.ConnectionUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;
import services.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public int save(User user) {
        System.out.println("Inside UserServiceImpl.save");
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            UserDao userDao = DaoFactory.getUserDao();
            userDao.setConnection(conToUse);
            status = userDao.save(user);
            conToUse.commit();
        } catch (ResourceHelperException e) {
            // TODO Auto-generated catch block
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            System.out.println("SQLException");
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        System.out.println("Status from UserServiceImppl.save() = " + status);
        return status;
    }
}