package fr.formation.inti.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.inti.entity.RDV;
import fr.formation.inti.service.RdvService;


@Controller
public class RdvController {
	
	@Autowired
	RdvService rdvService;
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
	

	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public String indexRdv(Model model) {
		//Initialisation pour le formulaire '/addcalendar'
		model.addAttribute("rdv", new RDV());
		
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
