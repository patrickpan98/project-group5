package fr.formation.inti.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	
	
	/* ===================================== */
	
	@Override
	public Page<RDV> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, Integer idSalon) {
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		List<RDV> listRdv = rdvDao.findAll();
		List<RDV> listRdvBySalon = new ArrayList<RDV>();
		for (RDV rdv : listRdv) {
			if (rdv.getEmp().getSalon().getIdSalon()==idSalon)
				listRdvBySalon.add(rdv);
		}
		
		//Ici, le pageable ne s'applique pas...
		Page<RDV> page = new PageImpl<RDV>(listRdvBySalon, pageable, listRdvBySalon.size());
		
		return page;
	}
	

}
