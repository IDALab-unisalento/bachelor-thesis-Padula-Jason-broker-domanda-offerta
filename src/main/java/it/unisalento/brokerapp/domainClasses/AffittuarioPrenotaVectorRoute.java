package it.unisalento.brokerapp.domainClasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AffittuarioPrenotaVectorRoute {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne(optional = false)
	Affittuario affittuario;
	
	@ManyToOne(optional = false)
	VectorRoute vectorRoute;
	
	float bookedCapacity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Affittuario getAffittuario() {
		return affittuario;
	}

	public void setAffittuario(Affittuario affittuario) {
		this.affittuario = affittuario;
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
