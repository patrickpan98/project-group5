package fr.formation.inti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import fr.formation.inti.entity.RDV;

public interface RdvService {

	Integer saveRDV(RDV rdv);
	
	void updateRDV(RDV rdv);
	
	void deleteRDV(RDV rdv);
	
	void deleteRDV(Integer id);
	
	RDV findById(Integer id);
	
	List<RDV> findAll();
	
	
	Page<RDV> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, Integer idSalon);
	
}
