package it.unisalento.brokerapp.domainClasses;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("affittuario")
public class Affittuario extends User {

	String surname;
	
	String nation;
	String phoneNumber;
	Date bornDate;
	boolean disabilitated;
	Date abilitationDate;
	
	@OneToMany(mappedBy = "affittuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<AffittuarioPrenotaViaggioRoute> prenotazioniList;
	

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
	public List<AffittuarioPrenotaViaggioRoute> getPrenotazioniList() {
		return prenotazioniList;
	}
	public void setPrenotazioniList(List<AffittuarioPrenotaViaggioRoute> prenotazioniList) {
		this.prenotazioniList = prenotazioniList;
	}

	
	
	
	
}
