package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.AffittuarioPrenotaVectorRoute;
import it.unisalento.brokerapp.exceptions.VectorRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaVectorRouteException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaVectorRouteNotFoundException;

public interface IAffittuarioPrenotaVectorRouteService {

	
	public AffittuarioPrenotaVectorRoute save(AffittuarioPrenotaVectorRoute affittuarioPrenotaVectorRoute) throws SavingUserPrenotaVectorRouteException;
	
	public AffittuarioPrenotaVectorRoute getById(int id) throws UserPrenotaVectorRouteNotFoundException;
		
	public List<AffittuarioPrenotaVectorRoute> getAll();

	public boolean delete (int id) throws UserPrenotaVectorRouteNotFoundException,IllegalArgumentException;

	public List<AffittuarioPrenotaVectorRoute> findByAffittuarioId(int userId) throws UserNotFoundException;

	public List<AffittuarioPrenotaVectorRoute> findByVectorRouteId(int vectorRouteId) throws VectorRouteNotFoundException;

	public AffittuarioPrenotaVectorRoute findByAffittuarioIdAndVectorRouteId(int userId, int VectorRouteId) throws UserPrenotaVectorRouteNotFoundException; 

}
