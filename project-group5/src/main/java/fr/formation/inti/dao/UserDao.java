package fr.formation.inti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	User findByLoginAndPassword(String login, String password);
}
