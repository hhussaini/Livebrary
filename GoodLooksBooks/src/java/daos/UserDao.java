package daos;

import objects.User;

public interface UserDao extends JdbcDaoSupport {   
    public int save(User user);
    public User getUser(String username, String password);
}