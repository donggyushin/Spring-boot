package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Reply {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Lob
	private String contents;
	
	
	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public Reply() {
		
	}
	
	public Reply(String contents, User user, Question question) {
		this.contents = contents;
		this.user = user;
		this.question = question;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reply other = (Reply) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Reply [id=" + id + ", user=" + user + ", contents=" + contents + ", question=" + question + "]";
	}
	
	
	

}
