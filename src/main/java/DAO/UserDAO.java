package DAO;

import model.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);
    User getUser(int userId);
//    void updateUser(User user);
    void removeUser(int userId);
    List<User> getAllUsers();


}
