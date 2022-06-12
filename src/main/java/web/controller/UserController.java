package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImp;

import java.util.List;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String listUsers(ModelMap model) {
        List<User> userList = userService.listUsers();
        model.addAttribute("usersList", userList);
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping(value = "/user")
    public String addUser(@ModelAttribute ("user") User user){
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String showUser(@PathVariable ("id") int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
        userService.removeUser(id);
        return "redirect:/users";
    }

//    @GetMapping(value = "/users")
//    public String printWelcome(@RequestParam(value = "count", required = false, defaultValue = "5") int count, ModelMap model) {
//        List<User> userList = userService.listUsers();
//        model.addAttribute("messages", userList);
//        return "index";
//    }
}
