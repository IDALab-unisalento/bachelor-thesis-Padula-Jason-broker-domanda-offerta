package it.unisalento.brokerapp.DTOclasses;

public class VectorDTO {

	Integer id;
	String name;
	String brand;
	int capacity; //kg trasportabili
	String licensePlate; 
	Boolean frozenProduct;
	Boolean biomedicalProducts; 
	
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
	
	
	
}
