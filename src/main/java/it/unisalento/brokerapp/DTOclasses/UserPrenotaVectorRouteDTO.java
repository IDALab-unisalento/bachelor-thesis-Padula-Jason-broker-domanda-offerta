package it.unisalento.brokerapp.DTOclasses;

public class UserPrenotaVectorRouteDTO {

	
	Integer id;
	Integer userId;
	Integer vectorRouteId;
	float   bookedCapacity; // capacità prenotata in Kg
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}





	public Integer getVectorRouteId() {
		return vectorRouteId;
	}
	public void setVectorRouteId(Integer vectorRouteId) {
		this.vectorRouteId = vectorRouteId;
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
