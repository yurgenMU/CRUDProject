package DAO;

import model.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);
    User getUser(int userId);
    void removeUser(int userId);
    List<User> getAllUsers();


}
