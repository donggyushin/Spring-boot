package com.example.demo.webs;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.QuestionRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;

@Controller
public class UserController {
	
	private List<User> users = new ArrayList<User>();
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@GetMapping("")
	public String Home(Model model) {
		model.addAttribute("questions", questionRepository.findAll());
		return "index";
	}
	
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
	public String update(@PathVariable("id") Long id,Model model,HttpSession session) {
		User user = userRepository.findById(id).get();
		User sessionedUser = (User)session.getAttribute("sessionedUser");
		if(sessionedUser == null) {
			return "redirect:/SignIn";
		}
		if(sessionedUser.getKey()!= id) {
			return "redirect:/";
		}
		
		model.addAttribute("user", user);
		return "Update/index";
	}
	
	@PostMapping("/updateAction/{id}")
	public String updateAction(@PathVariable Long id, User updatedUser, HttpSession session) {
		User user = userRepository.findById(id).get();
		User sessionedUser = (User)session.getAttribute("sessionedUser");
		if(sessionedUser == null) {
			return "redirect:/SignIn";
		}
		if(sessionedUser.getKey()!= id) {
			return "redirect:/";
		}
		user.update(updatedUser);
		userRepository.save(user);
		return "redirect:/list";
	}
	
	@PostMapping("/login")
	public String login(String id, String password, HttpSession session) {
		User user = userRepository.findByid(id);
		if(user == null) {
			return "redirect:/SignIn";
		}
		if(!password.equals(user.getPassword())) {
			return "redirect:/SignIn";
		}
		
		session.setAttribute("sessionedUser", user);
		
		return "redirect:/";
	}
	
	@GetMapping("/logoutAction")
	public String logoutAction(HttpSession session) {
		session.removeAttribute("sessionedUser");
		return "redirect:/";
	}
}
