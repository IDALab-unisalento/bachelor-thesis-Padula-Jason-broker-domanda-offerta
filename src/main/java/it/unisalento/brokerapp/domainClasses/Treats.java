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
public class Treats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	 
	String startCity;
	String endCity;

	@OneToMany(mappedBy = "vector", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<VectorTreats> vectorTreatsList;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	public List<VectorTreats> getVectorTreatsList() {
		return vectorTreatsList;
	}
	public void setVectorTreatsList(List<VectorTreats> vectorTreatsList) {
		this.vectorTreatsList = vectorTreatsList;
	}


	
}
