package it.unisalento.brokerapp.DTOclasses;

public class UserPrenotaOfferVectorTreatsDTO {

	
	Integer id;
	Integer userId;
	Integer offerVectorTreatsId;
	float   bookedCapacity; // capacità prenotata in Kg
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}




	public Integer getOfferVectorTreatsId() {
		return offerVectorTreatsId;
	}
	public void setOfferVectorTreatsId(Integer offerVectorTreatsId) {
		this.offerVectorTreatsId = offerVectorTreatsId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public float getBookedCapacity() {
		return bookedCapacity;
	}
	public void setBookedCapacity(float bookedCapacity) {
		this.bookedCapacity = bookedCapacity;
	}

	
	
	
	
}
