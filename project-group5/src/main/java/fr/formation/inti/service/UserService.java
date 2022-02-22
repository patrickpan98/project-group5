package fr.formation.inti.service;

import java.util.List;

import fr.formation.inti.entity.User;

public interface UserService {

	Integer saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);
	
	void deleteUser(Integer id);
	
	User findById(Integer id);
	
	List<User> findAll();
	
}
