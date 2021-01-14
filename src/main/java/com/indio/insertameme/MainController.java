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
import org.springframework.web.bind.annotation.RequestMapping;


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
}
