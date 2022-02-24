package fr.formation.inti.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.inti.entity.Employee;
import fr.formation.inti.entity.RDV;
import fr.formation.inti.entity.User;
import fr.formation.inti.service.EmployeeService;
import fr.formation.inti.service.RdvService;


@Controller
public class RdvController {
	
	@Autowired
	RdvService rdvService;
	
	@Autowired
	EmployeeService empService;
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
	private HttpSession session;

	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public String indexRdv(Model model, HttpServletRequest request) {
		//Initialisation pour le formulaire '/addcalendar'
		model.addAttribute("rdv", new RDV());
		
		//Liste des employes pour le select
		session = request.getSession(false);
		User userAdmin = (User) session.getAttribute("user");
		List<Employee> listEmployeeBySalon = empService.findBySalon(userAdmin.getEmp().getSalon());
		model.addAttribute("listEmployeeBySalon",listEmployeeBySalon);
		
		// Load RDV
		List<RDV> list = rdvService.findAll();
		Integer size = list.size();
		Integer[] listId = new Integer[size];
		String[] listStart = new String[size];
		String[] listEnd = new String[size];
		String[] listTitle = new String[size];
		for (int i = 0; i < size; i++) {
			listId[i] = list.get(i).getId();
			listStart[i] = list.get(i).getStart().format(formatter);
			listEnd[i] = list.get(i).getEnd().format(formatter);
			listTitle[i] = list.get(i).getTitle();
		}
		model.addAttribute("listId", listId);
		model.addAttribute("listStart", listStart);
		model.addAttribute("listEnd", listEnd);
		model.addAttribute("listTitle", listTitle);
		
		return "fullcalendar";
	}
	
	@RequestMapping(value = "/addcalendar", method = RequestMethod.POST)
	public String addRdv(Model model,
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime start, 
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime end) {

		// Liste des employes pour le select
		User userAdmin = (User) session.getAttribute("user");
		List<Employee> listEmployeeBySalon = empService.findBySalon(userAdmin.getEmp().getSalon());
		model.addAttribute("listEmployeesBySalon", listEmployeeBySalon);
		
		//Create RDV
		RDV rdvToAdd = new RDV();
		rdvToAdd.setStart(start);
		rdvToAdd.setEnd(end);
		rdvToAdd.setTitle("Title RDV");
		rdvToAdd.setState("Available");
		
		rdvService.saveRDV(rdvToAdd);
		
		//Load RDV
		List<RDV> list = rdvService.findAll();
		Integer size = list.size();
		Integer[] listId = new Integer[size];
		String[] listStart = new String[size];
		String[] listEnd = new String[size];
		String[] listTitle = new String[size];
		for (int i=0;i<size;i++) {
			listId[i] = list.get(i).getId();
			listStart[i] = list.get(i).getStart().format(formatter);
			listEnd[i] = list.get(i).getEnd().format(formatter);
			listTitle[i] = list.get(i).getTitle();
		}
		model.addAttribute("listId", listId);
		model.addAttribute("listStart", listStart);
		model.addAttribute("listEnd", listEnd);
		model.addAttribute("listTitle", listTitle);
		
		return "fullcalendar";
	}
	
	
	
	
}
