package it.unisalento.brokerapp.domainClasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Viaggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;


	float 	initialFreeCapacity;

	@ManyToOne(optional = true)
	Vector vector;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Vector getVector() {
		return vector;
	}



	public void setVector(Vector vector) {
		this.vector = vector;
	}



	public float getInitialFreeCapacity() {
		return initialFreeCapacity;
	}



	public void setInitialFreeCapacity(float initialFreeCapacity) {
		this.initialFreeCapacity = initialFreeCapacity;
	}


	


	
}
