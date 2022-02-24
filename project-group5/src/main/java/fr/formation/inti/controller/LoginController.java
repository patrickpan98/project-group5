package fr.formation.inti.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.formation.inti.entity.Employee;
import fr.formation.inti.entity.User;
import fr.formation.inti.service.ClientService;
import fr.formation.inti.service.EmployeeService;
import fr.formation.inti.service.SalonService;
import fr.formation.inti.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	EmployeeService empService;
	@Autowired
	SalonService salonService;
	@Autowired
	UserService userService;
	@Autowired
	ClientService clientService;
	
	private HttpSession session;
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String adminLogin(Model model, @ModelAttribute("user") User user, HttpServletRequest request) {
		
		User u = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
		if(u!=null && u.getEmp()!=null) {
			session = request.getSession(true);
			session.setAttribute("user", u);
			model.addAttribute("user", session.getAttribute("user"));
			model.addAttribute("homePage", true);
			//Initialisation pour le formulaire 'add employee'
			model.addAttribute("emp", new Employee());
			return "admin";
		}
		else {
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public String clientLogin(Model model, @ModelAttribute("user") User user, HttpServletRequest request) {
		
		User u = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
		if (u != null && u.getClient() != null) {
			session = request.getSession(true);
			session.setAttribute("user", u);
			model.addAttribute("user", session.getAttribute("user"));
			return "client";
		}
		else {
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminLogin(Model model) {
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("homePage", true);
		//Initialisation pour le formulaire 'add employee'
		model.addAttribute("emp", new Employee());
		return "admin";
	}
	
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public String clientLogin(Model model) {
		model.addAttribute("user", session.getAttribute("user"));
		return "client";
	}
	
}
