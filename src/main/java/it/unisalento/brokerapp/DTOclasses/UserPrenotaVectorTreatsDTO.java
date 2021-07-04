package it.unisalento.brokerapp.DTOclasses;

public class UserPrenotaVectorTreatsDTO {

	
	Integer id;
	Integer userId;
	Integer vectorTreatsId;
	float   bookedCapacity; // capacità prenotata in Kg
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getVectorTreatsId() {
		return vectorTreatsId;
	}
	public void setVectorTreatsId(Integer vectorTreatsId) {
		this.vectorTreatsId = vectorTreatsId;
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
