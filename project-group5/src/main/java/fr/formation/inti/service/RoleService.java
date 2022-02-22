package fr.formation.inti.service;

import java.util.List;

import fr.formation.inti.entity.Role;

public interface RoleService {

	Integer saveRole(Role role);
	
	void updateRole(Role role);
	
	void deleteRole(Role role);
	
	void deleteRole(Integer id);
	
	Role findById(Integer id);
	
	List<Role> findAll();
	
}
