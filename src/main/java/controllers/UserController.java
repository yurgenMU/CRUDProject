package controllers;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;


    @Autowired(required = true)
    @Qualifier(value = "userService")
    private void setUserService(UserService userService) {
        this.userService = userService;
        System.out.println(userService.getAllUsers());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "listUser";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private String addNew(@ModelAttribute("user") User user, Model model) {
        userService.addUser(user);
        return "redirect:/";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    private String updateExisting(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    private String getAddPage() {
        return "user";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    private String getEditPage(Model model, @PathVariable("id") int userId) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "update";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    private String delete(@PathVariable("id") int userId){
        userService.removeUser(userId);
        return "redirect:/";
    }
}

