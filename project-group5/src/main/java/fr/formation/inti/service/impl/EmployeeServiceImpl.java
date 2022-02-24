package fr.formation.inti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.formation.inti.dao.EmployeeDao;
import fr.formation.inti.entity.Employee;
import fr.formation.inti.entity.Salon;
import fr.formation.inti.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	private EmployeeDao empDao;
	
	
	@Override
	public Integer saveEmployee(Employee emp) {
		return empDao.save(emp).getIdEmployee();
	}

	@Override
	public void updateEmployee(Employee emp) {
		empDao.save(emp);
	}

	@Override
	public void deleteEmployee(Employee emp) {
		empDao.delete(emp);
		
	}

	@Override
	public void deleteEmployee(Integer id) {
		empDao.deleteById(id);
		
	}

	@Override
	public Employee findById(Integer id) {
		return empDao.findById(id).get();
	}

	@Override
	public List<Employee> findAll() {
		return empDao.findAll();
	}

	
	
	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, Salon salon) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		return empDao.findBySalon(salon, pageable);
		
	}

}
