package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.OfferVectorTreats;
import it.unisalento.brokerapp.exceptions.OfferVectorNotFoundException;
import it.unisalento.brokerapp.exceptions.OfferVectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingOfferVectorTreatsException;
import it.unisalento.brokerapp.exceptions.TreatsNotFoundException;


public interface IOfferVectorTreatsService {

	
	public OfferVectorTreats save(OfferVectorTreats offerVectorTreats) throws SavingOfferVectorTreatsException;
	
	public OfferVectorTreats getById(int id) throws OfferVectorTreatsNotFoundException;
		
	public List<OfferVectorTreats> getAll();

	public boolean delete (int id) throws OfferVectorTreatsNotFoundException,IllegalArgumentException;

	public List<OfferVectorTreats> findByOfferVectorId(int offerVectorId) throws OfferVectorNotFoundException;

	public List<OfferVectorTreats> findByTreatsId(int treatsId) throws TreatsNotFoundException;

	public OfferVectorTreats findByOfferVectorIdAndTreatsId(int offerVectorId, int treatsId) throws OfferVectorTreatsNotFoundException; 

}
