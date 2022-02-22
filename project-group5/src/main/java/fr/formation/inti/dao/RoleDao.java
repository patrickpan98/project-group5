package fr.formation.inti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {

}
