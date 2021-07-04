package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.OfferVector;
import it.unisalento.brokerapp.exceptions.OfferNotFoundException;
import it.unisalento.brokerapp.exceptions.OfferVectorNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingOfferVectorException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;

public interface IOfferVectorService {

	
	public OfferVector save(OfferVector offerVector) throws SavingOfferVectorException;
	
	public OfferVector getById(int id) throws OfferVectorNotFoundException;
		
	public List<OfferVector> getAll();

	public boolean delete (int id) throws OfferVectorNotFoundException,IllegalArgumentException;

	
	public List<OfferVector> findByOfferId(int offerId) throws OfferNotFoundException;

	public List<OfferVector> findByVectorId(int vectorId) throws VectorNotFoundException;

	public OfferVector findByOfferIdAndVectorId(int offerId, int vectorId) throws OfferVectorNotFoundException; 

}
