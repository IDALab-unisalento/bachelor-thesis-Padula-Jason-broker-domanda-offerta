package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.UserPrenotaVectorTreats;
import it.unisalento.brokerapp.exceptions.VectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaVectorTreatsException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaVectorTreatsNotFoundException;

public interface IUserPrenotaVectorTreatsService {

	
	public UserPrenotaVectorTreats save(UserPrenotaVectorTreats userPrenotaVectorTreats) throws SavingUserPrenotaVectorTreatsException;
	
	public UserPrenotaVectorTreats getById(int id) throws UserPrenotaVectorTreatsNotFoundException;
		
	public List<UserPrenotaVectorTreats> getAll();

	public boolean delete (int id) throws UserPrenotaVectorTreatsNotFoundException,IllegalArgumentException;

	public List<UserPrenotaVectorTreats> findByUserId(int userId) throws UserNotFoundException;

	public List<UserPrenotaVectorTreats> findByVectorTreatsId(int vectorTreatsId) throws VectorTreatsNotFoundException;

	public UserPrenotaVectorTreats findByUserIdAndVectorTreatsId(int userId, int vectorTreadsId) throws UserPrenotaVectorTreatsNotFoundException; 

}
