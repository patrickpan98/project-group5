package fr.formation.inti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.inti.dao.RdvDao;
import fr.formation.inti.entity.RDV;
import fr.formation.inti.service.RdvService;

@Service
public class RdvServiceImpl implements RdvService {

	
	@Autowired
	private RdvDao rdvDao;
	
	
	@Override
	public Integer saveRDV(RDV rdv) {
		return rdvDao.save(rdv).getId();
	}

	@Override
	public void updateRDV(RDV rdv) {
		rdvDao.save(rdv);
	}

	@Override
	public void deleteRDV(RDV rdv) {
		rdvDao.delete(rdv);
		
	}

	@Override
	public void deleteRDV(Integer id) {
		rdvDao.deleteById(id);
		
	}

	@Override
	public RDV findById(Integer id) {
		return rdvDao.findById(id).get();
	}

	@Override
	public List<RDV> findAll() {
		return rdvDao.findAll();
	}

}
