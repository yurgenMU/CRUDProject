package controllers;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;


//    @Autowired(required = true)
//    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
        System.out.println(userService.getAllUsers());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        System.out.println(users.size() + " ХУЙ");
        model.addAttribute("users", users);
        System.out.println("++++++++++++++++++++++++++++++" +
                "+++++++++++++++++++++++++++++++++++" +
                "хуйпизда");
        return "listUser";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNew(@ModelAttribute("user") User user, Model model, @RequestParam("id") String userid) {
//        User user = new User();
//        user.setFirstName(model.getParameter("firstName"));
//        user.setLastName(req.getParameter("lastName"));
//        user.setSpeciality(req.getParameter("speciality"));
//        user.setEmail(req.getParameter("email"));
//        String userid = req.getParameter("id");
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("speciality", user.getSpeciality());
        model.addAttribute("email",user.getEmail());

        if (userid == null || userid.isEmpty()) {
            userService.addUser(user);
        } else {
            user.setId(Integer.parseInt(userid));
            userService.addUser(user);
        }
        return "user";
    }
}

