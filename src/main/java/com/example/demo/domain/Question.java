package com.example.demo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Question {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String writer;
	@Column
	@Lob
	private String contents;
	@Column
	private String title;
	
	@OneToMany(mappedBy = "question")
	@OrderBy("id ASC")
	private List<Reply> replys;
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", writer=" + writer + ", contents=" + contents + ", title=" + title + "]";
	}
	public Question(String writer, String contents, String title) {
		super();
		this.writer = writer;
		this.contents = contents;
		this.title = title;
	}
	
	public Question() {
		
	}
	
	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
	
	
	
}
