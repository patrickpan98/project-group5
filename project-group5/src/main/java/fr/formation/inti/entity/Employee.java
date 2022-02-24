package fr.formation.inti.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="employee")
public class Employee {
	
	private Integer idEmployee;
	private String gender;
	private String lastName;
	private String firstName;
	private Date startDate;
	private String title; 
	private String category;
	private String phoneNumber;
	private Employee manager;
	private Salon salon;
	
	
	public Employee() {
		
	}
	
	
	public Employee(String gender, String lastName, String firstName) {
		super();
		this.gender = gender;
		this.lastName = lastName;
		this.firstName = firstName;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_employee", unique = true, nullable = false)
	public Integer getIdEmployee() {
		return idEmployee;
	}
	
	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}
	
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name = "last_name", nullable = false, length = 20)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	@Column(name = "first_name", nullable = false, length = 20)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = true)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name = "title", length = 20)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "category", length = 20)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@ManyToOne
	@JoinColumn(name = "superior_id")
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_salon")
	public Salon getSalon() {
		return salon;
	}
	public void setSalon(Salon salon) {
		this.salon = salon;
	}
	
	
	@Override
	public String toString() {
		return "Employee [idEmployee=" + idEmployee + ", gender=" + gender + ", lastName=" + lastName + ", firstName="
				+ firstName + ", startDate=" + startDate + ", title=" + title + ", category=" + category
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
	

	
}
