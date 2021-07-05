package it.unisalento.brokerapp.DTOclasses;

import java.sql.Date;

public class VectorRouteDTO {

	Integer id;
	Integer vectorId;
	Integer routeId;
	
	Date  startDate;
	Date  endDate;
	float availableCapacity;
	
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
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public float getAvailableCapacity() {
		return availableCapacity;
	}
	public void setAvailableCapacity(float availableCapacity) {
		this.availableCapacity = availableCapacity;
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

	
	
}
