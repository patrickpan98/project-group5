package fr.formation.inti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.inti.dao.RoleDao;
import fr.formation.inti.entity.Role;
import fr.formation.inti.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	
	@Autowired
	private RoleDao roleDao;
	
	
	@Override
	public Integer saveRole(Role role) {
		return roleDao.save(role).getIdRole();
	}

	@Override
	public void updateRole(Role role) {
		roleDao.save(role);
	}

	@Override
	public void deleteRole(Role role) {
		roleDao.delete(role);
		
	}

	@Override
	public void deleteRole(Integer id) {
		roleDao.deleteById(id);
		
	}

	@Override
	public Role findById(Integer id) {
		return roleDao.findById(id).get();
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

}
