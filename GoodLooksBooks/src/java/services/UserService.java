package services;

import objects.User;

public interface UserService {
    public int save(User user);
    public User getUser(String username, String password);
}
