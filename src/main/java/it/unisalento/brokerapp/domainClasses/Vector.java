package it.unisalento.brokerapp.domainClasses;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Vector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	String name;
	int capacity; //kg trasportabili
	String brand; // marca
	String licensePlate;
	Boolean frozenProduct;
	Boolean biomedicalProducts;
	
	@OneToMany(mappedBy = "vector", targetEntity = CompanyVector.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<CompanyVector> companyVectorList;

	@OneToMany(mappedBy = "vector", targetEntity = Viaggio.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Viaggio> viaggi;
	
	@OneToMany(mappedBy = "vector", targetEntity = Anomaly.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Anomaly> anomaliesList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public List<CompanyVector> getCompanyVectorList() {
		return companyVectorList;
	}
	public void setCompanyVectorList(List<CompanyVector> companyVectorList) {
		this.companyVectorList = companyVectorList;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public List<Viaggio> getViaggi() {
		return viaggi;
	}
	public void setViaggi(List<Viaggio> viaggi) {
		this.viaggi = viaggi;
	}
	public Boolean getFrozenProduct() {
		return frozenProduct;
	}
	public void setFrozenProduct(Boolean frozenProduct) {
		this.frozenProduct = frozenProduct;
	}
	public Boolean getBiomedicalProducts() {
		return biomedicalProducts;
	}
	public void setBiomedicalProducts(Boolean biomedicalProducts) {
		this.biomedicalProducts = biomedicalProducts;
	}
	public List<Anomaly> getAnomaliesList() {
		return anomaliesList;
	}
	public void setAnomaliesList(List<Anomaly> anomaliesList) {
		this.anomaliesList = anomaliesList;
	}
	
	
	
}

