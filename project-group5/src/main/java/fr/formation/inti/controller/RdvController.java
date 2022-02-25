package fr.formation.inti.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
		
		//Filtrer
		List<RDV> listRdvSalon = new ArrayList<RDV>();
		for (RDV r : list) {
			if(r.getEmp()!=null && (r.getEmp().getSalon().getIdSalon()==userAdmin.getEmp().getSalon().getIdSalon()))
					listRdvSalon.add(r);
		}
		
		Integer size = listRdvSalon.size();
		Integer[] listId = new Integer[size];
		String[] listStart = new String[size];
		String[] listEnd = new String[size];
		String[] listTitle = new String[size];
		for (int i = 0; i < size; i++) {
			listId[i] = listRdvSalon.get(i).getId();
			listStart[i] = listRdvSalon.get(i).getStart().format(formatter);
			listEnd[i] = listRdvSalon.get(i).getEnd().format(formatter);
			listTitle[i] = listRdvSalon.get(i).getTitle();
		}
		model.addAttribute("listId", listId);
		model.addAttribute("listStart", listStart);
		model.addAttribute("listEnd", listEnd);
		model.addAttribute("listTitle", listTitle);
		
		return "fullcalendar";
	}
	
	@RequestMapping(value = "/addcalendar", method = RequestMethod.POST)
	public String addRdv(Model model, @RequestParam("empId") Integer idEmployee,
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime start, 
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime end) {

		// Liste des employes pour le select
		User userAdmin = (User) session.getAttribute("user");
		List<Employee> listEmployeeBySalon = empService.findBySalon(userAdmin.getEmp().getSalon());
		model.addAttribute("listEmployeesBySalon", listEmployeeBySalon);
		
		//Create RDV
		RDV rdvToAdd = new RDV();
		Employee emp = empService.findById(idEmployee);
		rdvToAdd.setStart(start);
		rdvToAdd.setEnd(end);
		rdvToAdd.setTitle(emp.getFirstName().charAt(0)+". "+emp.getLastName());
		rdvToAdd.setState("Available");
		rdvToAdd.setEmp(emp);
		
		rdvService.saveRDV(rdvToAdd);
		
		//Load RDV
		List<RDV> list = rdvService.findAll();
		
		// Filtrer
		List<RDV> listRdvSalon = new ArrayList<RDV>();
		for (RDV r : list) {
			if (r.getEmp() != null
					&& (r.getEmp().getSalon().getIdSalon() == userAdmin.getEmp().getSalon().getIdSalon()))
				listRdvSalon.add(r);
		}
		
		
		Integer size = listRdvSalon.size();
		Integer[] listId = new Integer[size];
		String[] listStart = new String[size];
		String[] listEnd = new String[size];
		String[] listTitle = new String[size];
		for (int i=0;i<size;i++) {
			listId[i] = listRdvSalon.get(i).getId();
			listStart[i] = listRdvSalon.get(i).getStart().format(formatter);
			listEnd[i] = listRdvSalon.get(i).getEnd().format(formatter);
			listTitle[i] = listRdvSalon.get(i).getTitle();
		}
		model.addAttribute("listId", listId);
		model.addAttribute("listStart", listStart);
		model.addAttribute("listEnd", listEnd);
		model.addAttribute("listTitle", listTitle);
		
		return "fullcalendar";
	}
	
	
	
	
}
