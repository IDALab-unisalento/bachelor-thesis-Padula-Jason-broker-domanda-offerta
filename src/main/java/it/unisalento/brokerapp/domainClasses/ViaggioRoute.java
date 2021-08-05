package it.unisalento.brokerapp.domainClasses;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne; 
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ViaggioRoute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id; 

	@ManyToOne(optional = false)
	Route route;
	
	@ManyToOne(optional = false)
	Viaggio viaggio;

	float availableCapacity; // questo è il peso che sarà da aggiungere alla disponibilità dell'companyVector
	

	Date startDate;
	Date endDate;
	Date maximumWithdrawal; //data masisma entro la quale cancellare la prenotazione
	Date maximumBookingDate; //data entro la quel � possibile FARE la prenotazione
	
	public Date getMaximumBookingDate() {
		return maximumBookingDate;
	}

	public void setMaximumBookingDate(Date maximumBookingDate) {
		this.maximumBookingDate = maximumBookingDate;
	}

	@OneToMany(mappedBy = "viaggioRoute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<AffittuarioPrenotaViaggioRoute> prenotazioniList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Viaggio getViaggio() {
		return viaggio;
	}

	public void setViaggio(Viaggio viaggio) {
		this.viaggio = viaggio;
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

	public List<AffittuarioPrenotaViaggioRoute> getPrenotazioniList() {
		return prenotazioniList;
	}

	public void setPrenotazioniList(List<AffittuarioPrenotaViaggioRoute> prenotazioniList) {
		this.prenotazioniList = prenotazioniList;
	}

	public Date getMaximumWithdrawal() {
		return maximumWithdrawal;
	}

	public void setMaximumWithdrawal(Date maximumWithdrawal) {
		this.maximumWithdrawal = maximumWithdrawal;
	}
	
	

 
	
}
