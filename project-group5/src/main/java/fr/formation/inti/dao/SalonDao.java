package fr.formation.inti.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Salon;

public interface SalonDao extends JpaRepository<Salon, Integer> {

	Page<Salon> findByCity(String city, Pageable pageable);
}
