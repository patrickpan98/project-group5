package fr.formation.inti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
