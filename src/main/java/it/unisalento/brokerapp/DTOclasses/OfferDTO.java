package it.unisalento.brokerapp.DTOclasses;


public class OfferDTO {

	Integer id;

	Integer companyId;
	String startingWarehouseCity;
	String endingWarehouseCity;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getStartingWarehouseCity() {
		return startingWarehouseCity;
	}
	public void setStartingWarehouseCity(String startingWarehouseCity) {
		this.startingWarehouseCity = startingWarehouseCity;
	}
	public String getEndingWarehouseCity() {
		return endingWarehouseCity;
	}
	public void setEndingWarehouseCity(String endingWarehouseCity) {
		this.endingWarehouseCity = endingWarehouseCity;
	}

	
	
}
