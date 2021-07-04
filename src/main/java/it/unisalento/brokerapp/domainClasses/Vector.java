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
	
	@OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<OfferVector> offerVectorList;
	
	@OneToMany(mappedBy = "treats", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<VectorTreats> vectorTreatsList;
	
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
	public List<OfferVector> getOfferVectorList() {
		return offerVectorList;
	}
	public void setOfferVectorList(List<OfferVector> offerVectorList) {
		this.offerVectorList = offerVectorList;
	}
	public List<VectorTreats> getVectorTreatsList() {
		return vectorTreatsList;
	}
	public void setVectorTreatsList(List<VectorTreats> vectorTreatsList) {
		this.vectorTreatsList = vectorTreatsList;
	}
	
	
}

