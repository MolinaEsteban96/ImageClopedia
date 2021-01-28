package com.indio.insertameme;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="memepublication")
public class MemePublication {
	
	public MemePublication(String title, String imageUrl, String category, String memeText) {
		this.title = title;
		this.imageUrl = imageUrl;
		this.category = category;
		this.memeText = memeText;
	}


	public MemePublication() {}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public String getMemeText() {
		return memeText;
	}


	public void setMemeText(String memeText) {
		this.memeText = memeText;
	}

	


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="imageUrl")
	private String imageUrl;
	
	@Column(name="category")
	private String category;
	
	@Column(name="date")
	private String date;
	
	@Column(name="memeText")
	private String memeText;
	
}
