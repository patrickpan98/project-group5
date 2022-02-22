package fr.formation.inti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.RDV;

public interface RdvDao extends JpaRepository<RDV, Integer> {

}
