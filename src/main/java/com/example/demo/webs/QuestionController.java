package com.example.demo.webs;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Question;
import com.example.demo.domain.QuestionRepository;
import com.example.demo.domain.Reply;
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
		
		String questionsWriter = writer.getId();
		newQuestion.setContents(contents);
		newQuestion.setTitle(title);
		newQuestion.setWriter(questionsWriter);
		questionRepository.save(newQuestion);
		
		return "redirect:/";
	}
	
	@GetMapping("/question/{id}")
	public String Question(@PathVariable long id, Model model) {
		
		Question question = questionRepository.findById(id).get();
		model.addAttribute("question", question);
		return "/Question/index";
	}
	
	@GetMapping("/questions/delete/{id}")
	public String Delete(@PathVariable long id, HttpSession session) {
		
		Question question = questionRepository.findById(id).get();
		User user = (User)session.getAttribute("sessionedUser");
		
		if(!user.getId().equals(question.getWriter())) {
			return "redirect:/";
		}
		
		questionRepository.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/questions/edit/{id}")
	public String Edit(@PathVariable long id, HttpSession session, Model model) {
		User user = (User)session.getAttribute("sessionedUser");
		Question question = questionRepository.findById(id).get();
		
		if(!user.getId().equals(question.getWriter())) {
			return "redirect:/";
		}
		model.addAttribute("question", question);
		return "/EditWrite/index";
	}
	
	@PostMapping("/questions/editAction/{id}")
	public String EditAction(@PathVariable long id, String title, String contents) {
		
		Question question = questionRepository.findById(id).get();
		question.update(title, contents);
		questionRepository.save(question);
		return "redirect:/";
	}
	
	
	
}
