package fr.formation.inti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.formation.inti.entity.Client;
import fr.formation.inti.entity.Salon;
import fr.formation.inti.entity.User;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		//Initialisation des objets pour la page home.html (formulaires)
		model.addAttribute("salon", new Salon());
		model.addAttribute("client", new Client());
		model.addAttribute("user", new User());
		
		return "home";
	}
}
