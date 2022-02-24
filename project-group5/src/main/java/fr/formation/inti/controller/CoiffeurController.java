package fr.formation.inti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoiffeurController {

	@GetMapping("/employee/profile")
	public String profilePage(Model model) {
		model.addAttribute("profilePage", true);
		return "coiffeur";
		
	}
}
