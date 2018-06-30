package com.example.demo.webs;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Question;
import com.example.demo.domain.QuestionRepository;
import com.example.demo.domain.User;

@Controller
public class QuestionController {
	
	@Autowired
	QuestionRepository questionRepository;

	@GetMapping("/writeForm")
	public String writeForm() {
		return "/Write/index";
	}
	
	@PostMapping("/writeAction")
	public String writeAction(String title, String contents, HttpSession session) {
		
		User writer = (User)session.getAttribute("sessionedUser");
		if(writer == null) {
			return "redirect:/SignIn";
		}
		
		Question newQuestion = new Question();
		
		String questionsWriter = writer.getName();
		newQuestion.setContents(contents);
		newQuestion.setTitle(title);
		newQuestion.setWriter(questionsWriter);
		questionRepository.save(newQuestion);
		
		return "redirect:/";
	}
	
	
	
}
