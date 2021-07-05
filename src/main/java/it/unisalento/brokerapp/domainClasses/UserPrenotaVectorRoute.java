package it.unisalento.brokerapp.domainClasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserPrenotaVectorRoute {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne(optional = false)
	User user;
	
	@ManyToOne(optional = false)
	VectorRoute vectorRoute;
	
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

	


	public VectorRoute getVectorRoute() {
		return vectorRoute;
	}

	public void setVectorRoute(VectorRoute vectorRoute) {
		this.vectorRoute = vectorRoute;
	}

	public float getBookedCapacity() {
		return bookedCapacity;
	}

	public void setBookedCapacity(float bookedCapacity) {
		this.bookedCapacity = bookedCapacity;
	}


}
