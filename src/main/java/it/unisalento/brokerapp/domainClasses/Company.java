package it.unisalento.brokerapp.domainClasses;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("company")
public class Company extends User {

	String webSite;
	String phoneNumber;
	String headquarters;
	String jobSector;
	String legalForm;
	String iva;
	boolean disabilitated;
	Date abilitationDate;
	
	@OneToMany(mappedBy = "company", targetEntity = CompanyVector.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<CompanyVector> companyVectorList; 

	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getHeadquarters() {
		return headquarters;
	}
	public void setHeadquarters(String headquarters) {
		this.headquarters = headquarters;
	}
	public String getJobSector() {
		return jobSector;
	}
	public void setJobSector(String jobSector) {
		this.jobSector = jobSector;
	}
	public String getLegalForm() {
		return legalForm;
	}
	public void setLegalForm(String legalForm) {
		this.legalForm = legalForm;
	}
	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public List<CompanyVector> getCompanyVectorList() {
		return companyVectorList;
	}
	public void setCompanyVectorList(List<CompanyVector> companyVectorList) {
		this.companyVectorList = companyVectorList;
	}
	public boolean isDisabilitated() {
		return disabilitated;
	}
	public void setDisabilitated(boolean disabilitated) {
		this.disabilitated = disabilitated;
	}
	public Date getAbilitationDate() {
		return abilitationDate;
	}
	public void setAbilitationDate(Date abilitationDate) {
		this.abilitationDate = abilitationDate;
	}

	
	
}
