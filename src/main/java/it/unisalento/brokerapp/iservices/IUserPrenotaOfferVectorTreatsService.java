package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.UserPrenotaOfferVectorTreats;
import it.unisalento.brokerapp.exceptions.OfferVectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaOfferVectorTreatsException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaOfferVectorTreatsNotFoundException;

public interface IUserPrenotaOfferVectorTreatsService {

	
	public UserPrenotaOfferVectorTreats save(UserPrenotaOfferVectorTreats userPrenotaOfferVectorTreats) throws SavingUserPrenotaOfferVectorTreatsException;
	
	public UserPrenotaOfferVectorTreats getById(int id) throws UserPrenotaOfferVectorTreatsNotFoundException;
		
	public List<UserPrenotaOfferVectorTreats> getAll();

	public boolean delete (int id) throws UserPrenotaOfferVectorTreatsNotFoundException,IllegalArgumentException;

	public List<UserPrenotaOfferVectorTreats> findByUserId(int userId) throws UserNotFoundException;

	public List<UserPrenotaOfferVectorTreats> findByOfferVectorTreatsId(int offerVectorTreatsId) throws OfferVectorTreatsNotFoundException;

	public UserPrenotaOfferVectorTreats findByUserIdAndOfferVectorTreatsId(int userId, int offerVectorTreadsId) throws UserPrenotaOfferVectorTreatsNotFoundException; 

}
