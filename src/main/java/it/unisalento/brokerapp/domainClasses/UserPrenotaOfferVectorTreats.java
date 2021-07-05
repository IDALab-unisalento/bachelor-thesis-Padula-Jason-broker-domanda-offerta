package it.unisalento.brokerapp.domainClasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserPrenotaOfferVectorTreats {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne(optional = false)
	User user;
	
	@ManyToOne(optional = false)
	OfferVectorTreats offerVectorTreats;
	
	float bookedCapacity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public OfferVectorTreats getOfferVectorTreats() {
		return offerVectorTreats;
	}

	public void setOfferVectorTreats(OfferVectorTreats offerVectorTreats) {
		this.offerVectorTreats = offerVectorTreats;
	}

	public float getBookedCapacity() {
		return bookedCapacity;
	}

	public void setBookedCapacity(float bookedCapacity) {
		this.bookedCapacity = bookedCapacity;
	}


}
