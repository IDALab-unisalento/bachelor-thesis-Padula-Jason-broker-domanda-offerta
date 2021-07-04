package it.unisalento.brokerapp.DTOclasses;

public class OfferVectorDTO {

	
	Integer id;
	Integer offerId;
	Integer vectorId;
	float 	initialFreeCapacity;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOfferId() {
		return offerId;
	}
	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
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
