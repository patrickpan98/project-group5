package fr.formation.inti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.inti.dao.ClientDao;
import fr.formation.inti.entity.Client;
import fr.formation.inti.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	
	@Autowired
	private ClientDao clientDao;
	
	
	@Override
	public Integer saveClient(Client client) {
		return clientDao.save(client).getIdClient();
	}

	@Override
	public void updateClient(Client client) {
		clientDao.save(client);
	}

	@Override
	public void deleteClient(Client client) {
		clientDao.delete(client);
		
	}

	@Override
	public void deleteClient(Integer id) {
		clientDao.deleteById(id);
		
	}

	@Override
	public Client findById(Integer id) {
		return clientDao.findById(id).get();
	}

	@Override
	public List<Client> findAll() {
		return clientDao.findAll();
	}

}
