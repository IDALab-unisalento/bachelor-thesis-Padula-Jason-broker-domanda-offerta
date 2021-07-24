package it.unisalento.brokerapp.DTOclasses;

import java.util.Date;

public class ViaggioRouteDTO {

	Integer id;
	Integer viaggioId;
	Integer routeId;
	
	
	Date  startDate;
	Date  endDate;
	Date  maximumWithdrawal;

	float availableCapacity;
	
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getViaggioId() {
		return viaggioId;
	}
	public void setViaggioId(Integer viaggioId) {
		this.viaggioId = viaggioId;
	}
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public float getAvailableCapacity() {
		return availableCapacity;
	}
	public void setAvailableCapacity(float availableCapacity) {
		this.availableCapacity = availableCapacity;
	}
	public Date getMaximumWithdrawal() {
		return maximumWithdrawal;
	}
	public void setMaximumWithdrawal(Date maximumWithdrawal) {
		this.maximumWithdrawal = maximumWithdrawal;
	}

	
	
}
