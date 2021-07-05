package it.unisalento.brokerapp.DTOclasses;

import java.sql.Date;

public class VectorTreatsDTO {

	Integer id;
	Integer offerVectorId;
	Integer treatsId;
	
	Date  startDate;
	Date  endDate;
	float availableCapacity;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOfferVectorId() {
		return offerVectorId;
	}
	public void setOfferVectorId(Integer offerVectorId) {
		this.offerVectorId = offerVectorId;
	}
	public Integer getTreatsId() {
		return treatsId;
	}
	public void setTreatsId(Integer treatsId) {
		this.treatsId = treatsId;
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
