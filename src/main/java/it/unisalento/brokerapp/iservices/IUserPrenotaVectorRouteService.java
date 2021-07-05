package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.UserPrenotaVectorRoute;
import it.unisalento.brokerapp.exceptions.VectorRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaVectorRouteException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaVectorRouteNotFoundException;

public interface IUserPrenotaVectorRouteService {

	
	public UserPrenotaVectorRoute save(UserPrenotaVectorRoute userPrenotaVectorRoute) throws SavingUserPrenotaVectorRouteException;
	
	public UserPrenotaVectorRoute getById(int id) throws UserPrenotaVectorRouteNotFoundException;
		
	public List<UserPrenotaVectorRoute> getAll();

	public boolean delete (int id) throws UserPrenotaVectorRouteNotFoundException,IllegalArgumentException;

	public List<UserPrenotaVectorRoute> findByUserId(int userId) throws UserNotFoundException;

	public List<UserPrenotaVectorRoute> findByVectorRouteId(int vectorRouteId) throws VectorRouteNotFoundException;

	public UserPrenotaVectorRoute findByUserIdAndVectorRouteId(int userId, int VectorRouteId) throws UserPrenotaVectorRouteNotFoundException; 

}
