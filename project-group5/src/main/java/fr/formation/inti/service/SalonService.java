package fr.formation.inti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import fr.formation.inti.entity.Salon;

public interface SalonService {

	Integer saveSalon(Salon salon);
	
	void updateSalon(Salon salon);
	
	void deleteSalon(Salon salon);
	
	void deleteSalon(Integer id);
	
	Salon findById(Integer id);
	
	List<Salon> findAll();
	
	Page<Salon> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String city);

	
}
