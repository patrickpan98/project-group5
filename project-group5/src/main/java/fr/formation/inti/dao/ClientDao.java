package fr.formation.inti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Client;

public interface ClientDao extends JpaRepository<Client, Integer> {

}
