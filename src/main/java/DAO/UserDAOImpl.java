package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static SessionFactory sessionFactory;

        public UserDAOImpl() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }


    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();

        System.out.println("Adding developer's records to the DB");
        /**'
         *  Adding developer's records to the database (DB)
         */
        userDAO.addUser(new User("Proselyte", "Developer", "Java Developer",""));
        userDAO.addUser(new User("Some", "Developer", "C++ Developer",""));
        userDAO.addUser(new User("Peter", "UI", "UI Developer",""));

        System.out.println("List of developers");
        /**
         * List developers
         */
        List<User> developers = userDAO.getAllUsers();
        for (User developer : developers) {
            System.out.println(developer);
        }
        System.out.println("===================================");
        System.out.println("Removing Some Developer and updating Proselyte");
        /**
         * Update and Remove developers
         */
//        userDAO.updateUser(new User(2, 3));
//        userDAO.removeUser(3);
//
//        System.out.println("Final list of developers");
//        /**
//         * List developers
//         */
//        developers = userDAO.listDevelopers();
//        for (Developer developer : developers) {
//            System.out.println(developer);
//        }
//        System.out.println("===================================");

    }


    @Override
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        }
    }

    @Override
    public User getUser(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            return user;
        }
    }


    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User modifiedUser = session.get(User.class, user.getId());
            modifiedUser.setFirstName(user.getFirstName());
            modifiedUser.setLastName(user.getLastName());
            modifiedUser.setSpeciality(user.getSpeciality());
            session.update(modifiedUser);
            transaction.commit();
        }
    }

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
