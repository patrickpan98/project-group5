package fr.formation.inti.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	private Integer idUser;
	private String login;
	private String password;
	private String connectionNumber;
	private Employee emp;
	private Client client;
	private List<Role> roles = new ArrayList<Role>();
	
	
	public User() {
		
	}
	
	
	
	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_user", unique = true, nullable = false)
	public Integer getIdUser() {
		return idUser;
	}
	
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	@Column(name = "login")
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "connection_number")
	public String getConnectionNumber() {
		return connectionNumber;
	}
	
	public void setConnectionNumber(String connection_number) {
		this.connectionNumber = connection_number;
	}
	
	@OneToOne
	@JoinColumn(name="id_employee", nullable=true)
	public Employee getEmp() {
		return emp;
	}
	
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
	@OneToOne
	@JoinColumn(name="id_client", nullable=true)
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	@ManyToMany
	@JoinTable(name = "user_has_role", joinColumns = @JoinColumn(name = "id_user"),
				inverseJoinColumns = @JoinColumn(name = "id_role"))
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", login=" + login + ", password=" + password + ", connection_number="
				+ connectionNumber + "]";
	}
}
