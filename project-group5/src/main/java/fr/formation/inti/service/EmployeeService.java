package fr.formation.inti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import fr.formation.inti.entity.Employee;
import fr.formation.inti.entity.Salon;

public interface EmployeeService {

	Integer saveEmployee(Employee emp);
	
	void updateEmployee(Employee emp);
	
	void deleteEmployee(Employee emp);
	
	void deleteEmployee(Integer id);
	
	Employee findById(Integer id);
	
	List<Employee> findAll();
	
	
	List<Employee> findBySalon(Salon salon);
	
	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, Salon salon);
	
}
