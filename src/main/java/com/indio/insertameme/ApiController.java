package com.indio.insertameme;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	@RequestMapping("/api")
	public List<MemePublication> getMemes() {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(MemePublication.class)
				.buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		try {
			miSession.beginTransaction();
			
			String q = "from MemePublication";
			
			List<MemePublication> memes = miSession.createQuery(q).list();
			
			Collections.sort(memes, (d1, d2) -> {
				return d2.getId() - d1.getId();
			});
			return memes;
			
		} finally {
			miSession.close();
			miFactory.close();
		}
		
	}
	
	@RequestMapping("/comments")
	public List<Comment> getComments() {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Comment.class)
				.buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		try {
			miSession.beginTransaction();
			
			String q = "from Comment";
			
			List<Comment> comments = miSession.createQuery(q).list();
			
			Collections.sort(comments, (d1, d2) -> {
				return d2.getId() - d1.getId();
			});
			return comments;
			
		} finally {
			miSession.close();
			miFactory.close();
		}
	}
	
}
