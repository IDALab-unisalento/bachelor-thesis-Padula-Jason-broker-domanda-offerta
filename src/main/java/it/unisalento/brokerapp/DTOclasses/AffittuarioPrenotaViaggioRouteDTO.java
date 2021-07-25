package it.unisalento.brokerapp.DTOclasses;

import java.util.Date;

public class AffittuarioPrenotaViaggioRouteDTO {

	
	Integer id;
	Integer affittuarioId;
	Integer viaggioRouteId;
	float   bookedCapacity; // capacità prenotata in Kg
	Date    prenotationDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getViaggioRouteId() {
		return viaggioRouteId;
	}
	public void setViaggioRouteId(Integer viaggioRouteId) {
		this.viaggioRouteId = viaggioRouteId;
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

	public Date getPrenotationDate() {
		return prenotationDate;
	}

	public void setPrenotationDate(Date prenotationDate) {
		this.prenotationDate = prenotationDate;
	}

	
	
	
}
