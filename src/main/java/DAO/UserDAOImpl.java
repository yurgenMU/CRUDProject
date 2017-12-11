package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    public UserDAOImpl() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User modifiedUser = session.get(User.class, user.getId());
            if (modifiedUser != null) {
                modifiedUser.setFirstName(user.getFirstName());
                modifiedUser.setLastName(user.getLastName());
                modifiedUser.setSpeciality(user.getSpeciality());
                modifiedUser.setEmail(user.getEmail());
                session.update(modifiedUser);
            } else
                session.save(user);
            transaction.commit();
        }
    }

    @Override
    public User getUser(int userId) {
        Session session = sessionFactory.openSession();
        return session.get(User.class, userId);
    }


    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

//    @Override
//    public void updateUser(User user) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            User modifiedUser = session.get(User.class, user.getId());
//            modifiedUser.setFirstName(user.getFirstName());
//            modifiedUser.setLastName(user.getLastName());
//            modifiedUser.setSpeciality(user.getSpeciality());
//            modifiedUser.setEmail(user.getEmail());
//            session.update(modifiedUser);
//            transaction.commit();
//        }
//    }

    @Override
    public void removeUser(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User removedUser = session.get(User.class, userId);
            session.delete(removedUser);
            transaction.commit();
        }
    }
}
