package fr.formation.inti.service;

import java.util.List;

import fr.formation.inti.entity.Employee;

public interface EmployeeService {

	Integer saveEmployee(Employee emp);
	
	void updateEmployee(Employee emp);
	
	void deleteEmployee(Employee emp);
	
	void deleteEmployee(Integer id);
	
	Employee findById(Integer id);
	
	List<Employee> findAll();
	
}
