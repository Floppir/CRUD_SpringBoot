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

	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public String index(ModelMap model) {
		model.addAttribute("user", new User());
		model.addAttribute(userService.getAll());

		return "index";
	}

	@PostMapping("/sava_update_user")
	public String saveUpdateUser(@ModelAttribute User user) {
		userService.update(user);

		return "redirect:/";
	}

	@PostMapping("/delete-user")
	public String deleteUser(@ModelAttribute User user) {
		userService.removeById(user.getId());

		return "redirect:/";
	}
	
}