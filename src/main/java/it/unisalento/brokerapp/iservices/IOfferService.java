package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.Offer;
import it.unisalento.brokerapp.exceptions.OfferNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingOfferException;

public interface IOfferService {

	
	public Offer save(Offer offer) throws SavingOfferException;
	
	public Offer getById(int id) throws OfferNotFoundException;
		
	public List<Offer> getAll();

	public boolean delete (int id) throws OfferNotFoundException,IllegalArgumentException;
	
}
