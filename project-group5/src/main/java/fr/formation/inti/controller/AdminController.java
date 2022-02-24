package fr.formation.inti.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.inti.entity.Employee;
import fr.formation.inti.entity.User;
import fr.formation.inti.service.EmployeeService;

@Controller
public class AdminController {

	@Autowired
	EmployeeService empService;
	
	private HttpSession session;
	
	@GetMapping("/admin/profile")
	public String profilePage(Model model) {
		model.addAttribute("profilePage", true);
		return "admin";
		
	}
	
	@RequestMapping(value = "/employee/add", method = RequestMethod.GET)
	public String addEmployee(Model model, HttpServletRequest request) {
		session = request.getSession(false);
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("addEmployeePage", true);
		//Initialisation pour le formulaire 'add employee'
		model.addAttribute("emp", new Employee());
		return "admin";
	}
	
	
	
	@GetMapping("/employee/list")
	public String listEmployee(Model model, HttpServletRequest request) {
		return findPaginated(model, request, 1, "idEmployee", "asc");
	}
	
	@RequestMapping(value = "/employee/list/page/{pageNo}", method = RequestMethod.GET)
	public String findPaginated(Model model, HttpServletRequest request,
			@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir) {
		int pageSize = 5;
		session = request.getSession(false);
		
		User userAdmin = (User) session.getAttribute("user");
		Employee admin = userAdmin.getEmp();
		
		Page<Employee> page = empService.findPaginated(pageNo, pageSize, sortField, sortDir, admin.getSalon());
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("listEmployeePage", true);
		//Initialisation pour le formulaire 'add employee'
		model.addAttribute("emp", new Employee());
		
		return "admin";
	}
	
}
