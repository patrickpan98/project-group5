package fr.formation.inti.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Employee;
import fr.formation.inti.entity.Salon;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
	List<Employee> findBySalon(Salon salon);
	Page<Employee> findBySalon(Salon salon, Pageable pageable);

}
