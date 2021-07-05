package it.unisalento.brokerapp.domainClasses;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OfferVectorTreats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	float availableCapacity; // questo è il peso che sarà da aggiungere alla disponibilità dell'offerVector
	
	Date startDate;
	Date endDate;
	
	@ManyToOne(optional = false)
	OfferVector offerVector;
	
	@ManyToOne(optional = false)
	Treats treats;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public OfferVector getOfferVector() {
		return offerVector;
	}

	public void setOfferVector(OfferVector offerVector) {
		this.offerVector = offerVector;
	}

	public Treats getTreats() {
		return treats;
	}

	public void setTreats(Treats treats) {
		this.treats = treats;
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


 
	
}
