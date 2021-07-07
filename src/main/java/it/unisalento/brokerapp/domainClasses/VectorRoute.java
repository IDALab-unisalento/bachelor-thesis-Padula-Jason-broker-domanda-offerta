package it.unisalento.brokerapp.domainClasses;

import java.sql.Date;
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
public class VectorRoute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	float availableCapacity; // questo è il peso che sarà da aggiungere alla disponibilità dell'companyVector
	
	Date startDate;
	Date endDate;
	
	@ManyToOne(optional = false)
	Route route;
	
	
	@ManyToOne(optional = false)
	Vector vector;

	
	@OneToMany(mappedBy = "vectorRoute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<AffittuarioPrenotaVectorRoute> prenotazioniList;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public float getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(float availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}

	public List<AffittuarioPrenotaVectorRoute> getPrenotazioniList() {
		return prenotazioniList;
	}

	public void setPrenotazioniList(List<AffittuarioPrenotaVectorRoute> prenotazioniList) {
		this.prenotazioniList = prenotazioniList;
	}


 
	
}
