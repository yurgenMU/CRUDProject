package service;

import DAO.UserDAO;
import model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void addUser(User user){
        userDAO.addUser(user);
    }

    @Transactional
    public User getUser(int userID){
        return userDAO.getUser(userID);
    }

    @Transactional
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    @Transactional
    public void removeUser(int userID){
        userDAO.removeUser(userID);
    }
}
