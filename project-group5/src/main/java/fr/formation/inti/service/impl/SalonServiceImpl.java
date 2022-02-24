package fr.formation.inti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.formation.inti.dao.SalonDao;
import fr.formation.inti.entity.Salon;
import fr.formation.inti.service.SalonService;

@Service
public class SalonServiceImpl implements SalonService {

	
	@Autowired
	private SalonDao salonDao;
	
	
	@Override
	public Integer saveSalon(Salon salon) {
		return salonDao.save(salon).getIdSalon();
	}

	@Override
	public void updateSalon(Salon salon) {
		salonDao.save(salon);
	}

	@Override
	public void deleteSalon(Salon salon) {
		salonDao.delete(salon);
		
	}

	@Override
	public void deleteSalon(Integer id) {
		salonDao.deleteById(id);
		
	}

	@Override
	public Salon findById(Integer id) {
		return salonDao.findById(id).get();
	}

	@Override
	public List<Salon> findAll() {
		return salonDao.findAll();
	}

	@Override
	public Page<Salon> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String city) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		return salonDao.findByCity(city, pageable);
	}

}
