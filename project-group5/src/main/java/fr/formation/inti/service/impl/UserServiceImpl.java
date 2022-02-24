package fr.formation.inti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.inti.dao.UserDao;
import fr.formation.inti.entity.User;
import fr.formation.inti.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserDao userDao;
	
	
	@Override
	public Integer saveUser(User user) {
		return userDao.save(user).getIdUser();
	}

	@Override
	public void updateUser(User user) {
		userDao.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
		
	}

	@Override
	public void deleteUser(Integer id) {
		userDao.deleteById(id);
		
	}

	@Override
	public User findById(Integer id) {
		return userDao.findById(id).get();
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		return userDao.findByLoginAndPassword(login, password);
	}
	
}
