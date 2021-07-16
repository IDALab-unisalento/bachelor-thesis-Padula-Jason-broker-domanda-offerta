package it.unisalento.brokerapp.DTOclasses;


public class ViaggioDTO {

	Integer id;
	float 	initialFreeCapacity;
	float 	costoPerKm;

	Integer vectorId;
	Integer companyId;
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
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
	public float getInitialFreeCapacity() {
		return initialFreeCapacity;
	}
	public void setInitialFreeCapacity(float initialFreeCapacity) {
		this.initialFreeCapacity = initialFreeCapacity;
	}


	public float getCostoPerKm() {
		return costoPerKm;
	}



	public void setCostoPerKm(float costoPerKm) {
		this.costoPerKm = costoPerKm;
	}



	
	
	
}
