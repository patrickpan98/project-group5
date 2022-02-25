package fr.formation.inti.controller;

import java.util.ArrayList;
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

import fr.formation.inti.entity.RDV;
import fr.formation.inti.entity.Salon;
import fr.formation.inti.entity.User;
import fr.formation.inti.service.RdvService;
import fr.formation.inti.service.SalonService;

@Controller
public class ClientController {

	@Autowired
	SalonService salonService;
	
	@Autowired
	RdvService rdvService;
	
	private HttpSession session;
	
	@GetMapping("/client/profile")
	public String profilePage(Model model) {
		model.addAttribute("profilePage", true);
		return "client";
		
	}
	
	@RequestMapping(value = "/salon/list", method = RequestMethod.GET)
	public String listEmployee(@RequestParam("cityRequested") String city, Model model, HttpServletRequest request) {
		session = request.getSession(false);
		session.setAttribute("cityRequested", city);
		return findPaginated(model, request, 1, "idSalon", "asc");
	}
	
	@RequestMapping(value = "/salon/list/page/{pageNo}", method = RequestMethod.GET)
	public String findPaginated(Model model, HttpServletRequest request,
			@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir) {
		
		int pageSize = 5;
		session = request.getSession(false);
		
		String city = (String) session.getAttribute("cityRequested");
		
		Page<Salon> page = salonService.findPaginated(pageNo, pageSize, sortField, sortDir, city);
		List<Salon> listSalons = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listSalons", listSalons);
		
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("listSalonPage", true);
		model.addAttribute("cityRequested", city);
		
		return "client";
	}
	
	
	
	
	@RequestMapping(value = "/rdv/list", method=RequestMethod.GET)
	public String listRdv(@RequestParam("salonRequested") Integer idSalon, Model model, HttpServletRequest request) {
		session = request.getSession(false);
		session.setAttribute("salonRequested", idSalon);
		return findPaginatedRdv(model, request, 1, "id", "asc");
	}
	
	@RequestMapping(value = "/rdv/list/page/{pageNo}", method = RequestMethod.GET)
	public String findPaginatedRdv(Model model, HttpServletRequest request,
			@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir) {
		
		int pageSize = 5;
		session = request.getSession(false);
		
		Integer idSalon = (Integer) session.getAttribute("salonRequested");
		
		Page<RDV> page = rdvService.findPaginated(pageNo, pageSize, sortField, sortDir, idSalon);
		
		List<RDV> listRdv = page.getContent();
		
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listRdv", listRdv);
		
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("listRdvPage", true);
		model.addAttribute("salonName", salonService.findById(idSalon).getName());
		
		return "client";
	}
	
	
	@RequestMapping(value = "/addrdv", method=RequestMethod.GET)
	public String addRdv(Model model, HttpServletRequest request, @RequestParam("idrdv") Integer idRdv) {
		session = request.getSession(false);
		
		User user = (User) session.getAttribute("user");
		RDV rdv = rdvService.findById(idRdv);
		
		//Avant de setter, il faudrait vérifier le 'state' du rdv
		rdv.setClient(user.getClient());
		rdvService.updateRDV(rdv);
		
		List<RDV> listRdv = rdvService.findAll();
		List<RDV> myRdv = new ArrayList<RDV>();
		for (RDV r : listRdv) {
			if (r.getClient()!=null && (r.getClient().getIdClient()==user.getClient().getIdClient()))
				myRdv.add(r);
		}
		
		model.addAttribute("myRdv", myRdv);
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("listMyRdvPage", true);
		return "client";
	}
	
	
	@RequestMapping(value = "/client/myrdv", method=RequestMethod.GET)
	public String myRdv(Model model, HttpServletRequest request) {
		session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		List<RDV> listRdv = rdvService.findAll();
		List<RDV> myRdv = new ArrayList<RDV>();
		
		
		for (RDV r : listRdv) {
			if (r.getClient()!=null && (r.getClient().getIdClient()==user.getClient().getIdClient()))
				myRdv.add(r);
		}
		
		model.addAttribute("myRdv", myRdv);
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("listMyRdvPage", true);
		return "client";
	}
	
	
}
