package fr.formation.inti.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.inti.entity.Client;
import fr.formation.inti.entity.Employee;
import fr.formation.inti.entity.Salon;
import fr.formation.inti.entity.User;
import fr.formation.inti.service.ClientService;
import fr.formation.inti.service.EmployeeService;
import fr.formation.inti.service.SalonService;
import fr.formation.inti.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
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
	public String adminRegister(Model model, @ModelAttribute("salon") Salon salon, 
			@RequestParam("firstName") String fname, @RequestParam("lastName") String lname,
			@RequestParam("gender") String gender, @RequestParam("login") String login,
			@RequestParam("password") String password, @RequestParam("sDate") String date, HttpServletRequest request) {
		
		Date startDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		User user = new User(login, password);
		Employee emp = new Employee(gender, lname, fname);
		emp.setTitle("President");
		Salon salonToAdd = new Salon(salon.getName(), startDate, salon.getAddress(), salon.getCity(),
				salon.getPostalCode(), salon.getCountry());
		
		salonService.saveSalon(salonToAdd);
		
		emp.setSalon(salonToAdd);
		empService.saveEmployee(emp);
		
		user.setEmp(emp);
		userService.saveUser(user);
		
		session = request.getSession(true);
		session.setAttribute("user", user);
		model.addAttribute("user", session.getAttribute("user"));
		
		model.addAttribute("homePage", true);
		//Initialisation pour le formulaire 'add employee'
		model.addAttribute("emp", new Employee());
		return "admin";
	}
	
	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public String clientRegister(Model model, @ModelAttribute("client") Client client,
			@RequestParam("login") String login,
			@RequestParam("password") String password, @RequestParam("date") String date, HttpServletRequest request) {
		
		Date birthDate = null;
		try {
			birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		User user = new User(login, password);
		Client clientToAdd = new Client(client.getGender(), client.getFirstName(), client.getFirstName(), birthDate);
		
		clientService.saveClient(clientToAdd);
		
		user.setClient(clientToAdd);
		userService.saveUser(user);
		
		session = request.getSession(true);
		session.setAttribute("user", user);
		model.addAttribute("user", session.getAttribute("user"));
		
		model.addAttribute("galerie", true);
		return "client";
	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public String employeeRegister(Model model, @ModelAttribute("emp") Employee emp,
			@RequestParam("date") String date, HttpServletRequest request) {
		session = request.getSession(false);
		User userAdmin = (User) session.getAttribute("user");
		Employee admin = empService.findById(userAdmin.getEmp().getIdEmployee());
		
		Date startDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		User userToAdd = new User(emp.getFirstName().toLowerCase().charAt(0)+emp.getLastName().toLowerCase(), "abc123");
		Employee empToAdd = new Employee(emp.getGender(), emp.getLastName(), emp.getFirstName());
		empToAdd.setStartDate(startDate);
		empToAdd.setTitle(emp.getTitle());
		empToAdd.setCategory(emp.getCategory());
		empToAdd.setManager(admin);
		empToAdd.setSalon(admin.getSalon());
		
		empService.saveEmployee(empToAdd);
		
		userToAdd.setEmp(empToAdd);
		userService.saveUser(userToAdd);
		
		model.addAttribute("message", "Employee added to the database!");
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("homePage", true);
		return "admin";
	}
	
}
