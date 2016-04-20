package daos;

import objects.User;

public interface UserDao extends JdbcDaoSupport {   
    public int save(User user);
}