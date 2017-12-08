package DAO;

import model.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);
    List<User> getAllUsers();
    void updateUser(User user);
    void removeUser(User user);


}
