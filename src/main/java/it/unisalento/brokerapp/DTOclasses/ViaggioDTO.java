package it.unisalento.brokerapp.DTOclasses;


public class ViaggioDTO {

	Integer id;
	float 	initialFreeCapacity;


	Integer vectorId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVectorId() {
		return vectorId;
	}
	public void setVectorId(Integer vectorId) {
		this.vectorId = vectorId;
	}
	public float getInitialFreeCapacity() {
		return initialFreeCapacity;
	}
	public void setInitialFreeCapacity(float initialFreeCapacity) {
		this.initialFreeCapacity = initialFreeCapacity;
	}


	
	
	
}
