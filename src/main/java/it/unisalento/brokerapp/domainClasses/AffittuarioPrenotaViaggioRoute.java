package it.unisalento.brokerapp.domainClasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AffittuarioPrenotaViaggioRoute {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne(optional = false)
	Affittuario affittuario;
	
	@ManyToOne(optional = false)
	ViaggioRoute viaggioRoute;
	
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

	public ViaggioRoute getViaggioRoute() {
		return viaggioRoute;
	}

	public void setViaggioRoute(ViaggioRoute viaggioRoute) {
		this.viaggioRoute = viaggioRoute;
	}

	public float getBookedCapacity() {
		return bookedCapacity;
	}

	public void setBookedCapacity(float bookedCapacity) {
		this.bookedCapacity = bookedCapacity;
	}



}
