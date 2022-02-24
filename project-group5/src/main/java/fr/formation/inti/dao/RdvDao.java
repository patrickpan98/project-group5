package fr.formation.inti.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.RDV;

public interface RdvDao extends JpaRepository<RDV, Integer> {
	
//	@Query("select r from RDV r where r.emp.salon.idSalon = ?1")
//	Page<RDV> findRdvBySalon(Integer idSalon, Pageable pageable);

}
