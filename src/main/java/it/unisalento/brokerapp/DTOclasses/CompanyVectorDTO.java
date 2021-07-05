package it.unisalento.brokerapp.DTOclasses;

public class CompanyVectorDTO {

	
	Integer id;
	Integer companyId;
	Integer vectorId;
	float 	initialFreeCapacity;
	
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

	
	
	
}
