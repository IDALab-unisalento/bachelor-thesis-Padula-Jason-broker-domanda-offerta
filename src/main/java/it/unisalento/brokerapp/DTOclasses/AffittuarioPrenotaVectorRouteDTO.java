package it.unisalento.brokerapp.DTOclasses;

public class AffittuarioPrenotaVectorRouteDTO {

	
	Integer id;
	Integer affittuarioId;
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
	public Integer getAffittuarioId() {
		return affittuarioId;
	}
	public void setAffittuarioId(Integer affittuarioId) {
		this.affittuarioId = affittuarioId;
	}

	public float getBookedCapacity() {
		return bookedCapacity;
	}
	public void setBookedCapacity(float bookedCapacity) {
		this.bookedCapacity = bookedCapacity;
	}

	
	
	
	
}
