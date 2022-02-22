package fr.formation.inti.service;

import java.util.List;

import fr.formation.inti.entity.Client;

public interface ClientService {

	Integer saveClient(Client client);
	
	void updateClient(Client client);
	
	void deleteClient(Client client);
	
	void deleteClient(Integer id);
	
	Client findById(Integer id);
	
	List<Client> findAll();
	
}
