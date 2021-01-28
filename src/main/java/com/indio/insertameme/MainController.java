package com.indio.insertameme;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {
	
	@RequestMapping
	public String getIndex(Model model) {
		
		MemePublication memepublication = new MemePublication();
		model.addAttribute("meme" , memepublication);
		
		return "index.jsp";
	}
	
	@RequestMapping("/postMeme")
	public String postMeme(@ModelAttribute("meme") MemePublication memepublication) {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(MemePublication.class)
				.buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		miSession.beginTransaction();
		
		String q = "from MemePublication";
		
		List<MemePublication> memes = miSession.createQuery(q).list();
		
		for (MemePublication meme : memes) {
			
			if (meme.getImageUrl().equals(memepublication.getImageUrl())) {
				return "/";
			}
		}
		
		miSession.getTransaction().commit();
		
		try {
			miSession.beginTransaction();
			String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
			memepublication.setDate(date);
			miSession.save(memepublication);
			miSession.getTransaction().commit();
			System.out.println("Registro guardado con exito!");
		} finally {
			miSession.close();
			miFactory.close();
		}
		
		return "/";
	}	
	
	@RequestMapping("/{id}")
	public String getUrlMeme(@PathVariable("id") int id, Model model) {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(MemePublication.class)
				.addAnnotatedClass(Comment.class)
				.buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		String q = "from MemePublication cl where cl.id=" + id;
		
		try {
			
			miSession.beginTransaction();
			
			List<MemePublication> memeList = miSession.createQuery(q).list();
			
			MemePublication meme = memeList.get(0);
			
			System.out.println(meme.getTitle());
			
			model.addAttribute("meme", meme);
			
		} finally {
			
			miSession.close();
			miFactory.close();
			
			Comment comment = new Comment();
			model.addAttribute("comment", comment);
			
		}
		
		
		return "memeurl.jsp";
	}
	
	@RequestMapping("/postComment")
	public String postComment(@ModelAttribute("comment") Comment comment, Model model) {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(MemePublication.class)
				.addAnnotatedClass(Comment.class)
				.buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		String q = "from Comment";
		
		List<Comment> comentarios = miSession.createQuery(q).list();
		
		for (Comment comm : comentarios) {
			
			if (comm.getText().equals(comment.getText())) {
				
				String qq = "from MemePublication cl where cl.id=" + comment.getMemeId();
				
				miSession.beginTransaction();
				
				List<MemePublication> memeList = miSession.createQuery(qq).list();
				
				MemePublication meme = memeList.get(0);
				
				model.addAttribute("meme", meme);
				
				return "memeurl.jsp";
			}
		}
		
		try {
			
			miSession.beginTransaction();
			String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
			comment.setDate(date);
			miSession.save(comment);
			miSession.getTransaction().commit();
			System.out.println("Registro guardado con exito!");
			
		
			String qq = "from MemePublication cl where cl.id=" + comment.getMemeId();
		
			miSession.beginTransaction();
			
			List<MemePublication> memeList = miSession.createQuery(qq).list();
			
			MemePublication meme = memeList.get(0);
			
			System.out.println(meme.getTitle());
			
			model.addAttribute("meme", meme);
			
			
			
		} finally {
			
			miSession.close();
			miFactory.close();
			
			
		}
		
		return "memeurl.jsp";
	}
}
