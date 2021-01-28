package com.indio.insertameme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment {

	public Comment(String text, int memeId) {
		this.text = text;
		this.memeId = memeId;
	}

	public Comment() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMemeId() {
		return memeId;
	}

	public void setMemeId(int memeId) {
		this.memeId = memeId;
	}





	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="text")
	private String text;
	
	@Column(name="date")
	private String date;
	
	@Column(name="memeId")
	private int memeId;
}
