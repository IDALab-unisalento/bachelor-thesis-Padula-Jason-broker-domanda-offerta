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


	


	
}
