package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String listUsers(ModelMap model) {
        List<User> userList = userService.listUsers();
        for (User usr : userList){
            System.out.println(usr.getName());
        }
        model.addAttribute("usersList", userList);
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping(value = "/user"/*, produces = "text/plain;charset=UTF-8"*/)
    public String addUser(@ModelAttribute ("user") User user){
        System.out.println("добавлен: " + user.getName());
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String showUser(@PathVariable ("id") long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute ("user") User user){
        userService.updateUser(user);
        return "user";
    }

    @RequestMapping("/users/remove/{id}")
    public String removeUser(@PathVariable("id") long id){
        userService.removeUser(id);
        return "redirect:/users";
    }
}
