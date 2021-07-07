
package it.unisalento.brokerapp.DTOclasses;

import java.util.Date;

public class AffittuarioDTO extends UserDTO{
	
	Integer id;
	String surname;
	String nation;
	String phoneNumber;
	Date bornDate;
	boolean disabilitated;
	Date abilitationDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
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
