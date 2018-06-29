package com.example.demo.webs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;

@Controller
public class UserController {
	
	private List<User> users = new ArrayList<User>();
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/SignIn")
	public String SignIn() {
		return "SignIn/index";
	}
	
	@GetMapping("/SignUp")
	public String SignUp() {
		return "SignUp/index";
	}
	
	
	@PostMapping("/create")
	public String create(User user) {
		//users.add(user);
		userRepository.save(user);
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "UserList/index";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Long id,Model model) {
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "Update/index";
	}
}
