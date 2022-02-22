package fr.formation.inti.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	private Integer idClient;
	private String gender; 
	private String firstName;
	private String lastName;
	private Date birthDay;
	private String phoneNumber;
	
	public Client() {
		
	}
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idClient", unique = true, nullable = false)
	public Integer getIdClient() {
		return idClient;
	}
	
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
	
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name = "firstName")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "lastName")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "birthDay")
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	@Column(name = "phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", gender=" + gender + ", firstName=" + firstName + ", lastName="
				+ lastName + ", birthDay=" + birthDay + ", phoneNumber=" + phoneNumber + "]";
	}
}
