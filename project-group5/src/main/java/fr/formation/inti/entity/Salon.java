package fr.formation.inti.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "salon")
public class Salon {
	
	private Integer idSalon;
	private String name;
	private Date startDate;
	private String address;
	private String city;
	private String postalCode;
	private String country;
	
	
	public Salon() {
		
	}
	
	
	
	public Salon(String name, Date startDate, String address, String city, String postalCode, String country) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = " id_salon", unique= true, nullable= false)
	public Integer getIdSalon() {
		return idSalon;
	}
	
	public void setIdSalon(Integer idSalon) {
		this.idSalon = idSalon;
	}
	
	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false)
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column( name ="adress")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="city")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name ="postal_code")
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@Column(name="country")
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "Salon [idSalon=" + idSalon + ", name=" + name + ", startDate=" + startDate + ", address=" + address
				+ ", city=" + city + ", postalCode=" + postalCode + ", country=" + country + "]";
	} 

	
	
	
}
