package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    private String message;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String index(ModelMap model, @ModelAttribute User user) {
        model.addAttribute(userService.getAll());
        model.addAttribute("message", message);
        return "index";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute User user) {
        try {
            userService.add(user);
            message = "Пользователь успешно добавлен";
        } catch (RuntimeException e) {
            message = e.getMessage();
        }

        return "redirect:/";
    }

    @PostMapping("/update_user")
    public String updateUser(@ModelAttribute User user) {
        try {
            userService.update(user);
            message = "Данные пользователя обновлены";
        } catch (RuntimeException e) {
            message = e.getMessage();
        }

        return "redirect:/";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@ModelAttribute User user) {
        try {
            userService.removeById(user.getId());
            message = "Пользователь успешно удален";
        } catch (RuntimeException e) {
            message = e.getMessage();
        }

        return "redirect:/";
    }

}