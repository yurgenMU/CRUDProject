package controllers;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static String INSERT = "/jsp/user.jsp";
    private static String LIST_USER = "/jsp/listUser.jsp";
    private static String EDIT = "/jsp/update.jsp";
    private UserDAO userDAO;

    public UserController() {
        super();
        userDAO = new UserDAOImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int userId = Integer.parseInt(req.getParameter("id"));
            userDAO.removeUser(userId);
            forward = LIST_USER;
            req.setAttribute("users", userDAO.getAllUsers());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = EDIT;
            int userId = Integer.parseInt(req.getParameter("id"));
            User user = userDAO.getUser(userId);
            req.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")) {
            forward = LIST_USER;
            req.setAttribute("users", userDAO.getAllUsers());
        } else {
            forward = INSERT;
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setSpeciality(req.getParameter("speciality"));
        user.setEmail(req.getParameter("email"));
        String userid = req.getParameter("id");
        if (userid == null || userid.isEmpty()) {
            userDAO.addUser(user);
        } else {
            user.setId(Integer.parseInt(userid));
            userDAO.updateUser(user);
        }
        RequestDispatcher view = req.getRequestDispatcher(LIST_USER);
        req.setAttribute("users", userDAO.getAllUsers());
        view.forward(req, resp);
    }
}