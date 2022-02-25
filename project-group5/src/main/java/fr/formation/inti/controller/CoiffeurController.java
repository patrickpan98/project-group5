package fr.formation.inti.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.formation.inti.entity.RDV;
import fr.formation.inti.entity.User;
import fr.formation.inti.service.RdvService;

@Controller
public class CoiffeurController {

	@Autowired
	RdvService rdvService;
	
	private HttpSession session;
	
	@GetMapping("/employee/profile")
	public String profilePage(Model model) {
		model.addAttribute("profilePage", true);
		return "coiffeur";
		
	}
	
	@RequestMapping(value = "/employee/myrdv", method=RequestMethod.GET)
	public String myRdv(Model model, HttpServletRequest request) {
		session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		List<RDV> listRdv = rdvService.findAll();
		List<RDV> myRdv = new ArrayList<RDV>();

		for (RDV r : listRdv) {
			if (r.getEmp()!=null && r.getClient()!=null && (r.getEmp().getIdEmployee()==user.getEmp().getIdEmployee()))
				myRdv.add(r);
		}
		
		model.addAttribute("myRdv", myRdv);
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("listMyRdvPage", true);
		return "coiffeur";
	}
}
