package com.example.demo.webs;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Question;
import com.example.demo.domain.QuestionRepository;
import com.example.demo.domain.Reply;
import com.example.demo.domain.ReplyRepository;
import com.example.demo.domain.User;

@Controller
public class ReplyController {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	ReplyRepository replyrepository;
	
	@PostMapping("/reply/create/{id}")
	public String create(String contents, HttpSession session, @PathVariable long id) {
		User user = (User)session.getAttribute("sessionedUser");
		Question question = questionRepository.findById(id).get();
		Reply reply = new Reply();
		reply.setUser(user);
		reply.setQuestion(question);
		reply.setContents(contents);
		replyrepository.save(reply);
		return String.format("redirect:/question/%d", id);
	}

}
