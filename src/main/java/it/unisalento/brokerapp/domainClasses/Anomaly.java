package it.unisalento.brokerapp.domainClasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Anomaly {

	//Richieste dalle specifiche
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@NotNull 
	Boolean gps_err;
	@NotNull
	Boolean fridge_err;
	@NotNull
	Boolean temp_err;
	@NotNull
	Boolean tail_err;

	//Riservate a sviluppi futuri
	@NotNull
	Boolean gen1_err;
	@NotNull
	Boolean gen2_err;
	@NotNull
	Boolean gen3_err;
	
	
	@ManyToOne (optional = false)
	Vector vector;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getGps_err() {
		return gps_err;
	}

	public void setGps_err(Boolean gps_err) {
		this.gps_err = gps_err;
	}

	public Boolean getFridge_err() {
		return fridge_err;
	}

	public void setFridge_err(Boolean fridge_err) {
		this.fridge_err = fridge_err;
	}

	public Boolean getTemp_err() {
		return temp_err;
	}

	public void setTemp_err(Boolean temp_err) {
		this.temp_err = temp_err;
	}

	public Boolean getTail_err() {
		return tail_err;
	}

	public void setTail_err(Boolean tail_err) {
		this.tail_err = tail_err;
	}

	public Boolean getGen1_err() {
		return gen1_err;
	}

	public void setGen1_err(Boolean gen1_err) {
		this.gen1_err = gen1_err;
	}

	public Boolean getGen2_err() {
		return gen2_err;
	}

	public void setGen2_err(Boolean gen2_err) {
		this.gen2_err = gen2_err;
	}

	public Boolean getGen3_err() {
		return gen3_err;
	}

	public void setGen3_err(Boolean gen3_err) {
		this.gen3_err = gen3_err;
	}

	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}
	
	
}
