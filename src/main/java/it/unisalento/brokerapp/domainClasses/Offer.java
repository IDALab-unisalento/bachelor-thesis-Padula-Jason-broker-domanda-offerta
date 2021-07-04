package it.unisalento.brokerapp.domainClasses;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Offer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	 
	String startingWarehouseCity;
	String endingWarehouseCity;

	@ManyToOne(optional = false)
	Company company;
	
	
	@OneToMany(mappedBy = "vector", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<OfferVector> offerVectorList;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


	
	public List<OfferVector> getOfferVectorList() {
		return offerVectorList;
	}

	public void setOfferVectorList(List<OfferVector> offerVectorList) {
		this.offerVectorList = offerVectorList;
	}

	public String getStartingWarehouseCity() {
		return startingWarehouseCity;
	}

	public void setStartingWarehouseCity(String startingWarehouseCity) {
		this.startingWarehouseCity = startingWarehouseCity;
	}

	public String getEndingWarehouseCity() {
		return endingWarehouseCity;
	}

	public void setEndingWarehouseCity(String endingWarehouseCity) {
		this.endingWarehouseCity = endingWarehouseCity;
	}
	

	
	
	

}
