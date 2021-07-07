package it.unisalento.brokerapp.domainClasses;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	String name;
	int capacity; //kg trasportabili
	String brand; // marca
	
	@OneToMany(mappedBy = "vector", targetEntity = CompanyVector.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<CompanyVector> companyVectorList;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public List<CompanyVector> getCompanyVectorList() {
		return companyVectorList;
	}
	public void setCompanyVectorList(List<CompanyVector> companyVectorList) {
		this.companyVectorList = companyVectorList;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
}

