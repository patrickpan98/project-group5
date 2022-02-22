package fr.formation.inti.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	private Integer idRole;
	private String roleName;
	private List<User> users = new ArrayList<User>();
	

	public Role() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role")
	public Integer getIdRole() {
		return idRole;
	}
	
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	
	@Column(name = "role_name")
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String role_name) {
		this.roleName = role_name;
	}
	
	
	@ManyToMany
	@JoinTable(name = "user_has_role", joinColumns = @JoinColumn(name = "id_role"),
				inverseJoinColumns = @JoinColumn(name = "id_user"))
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	@Override
	public String toString() {
		return "role [idRole=" + idRole + ", role_name=" + roleName + "]";
	}
}
