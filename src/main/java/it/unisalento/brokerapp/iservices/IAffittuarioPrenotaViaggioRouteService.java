package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.AffittuarioPrenotaViaggioRoute;
import it.unisalento.brokerapp.exceptions.ViaggioRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaViaggioRouteException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaViaggioRouteNotFoundException;

public interface IAffittuarioPrenotaViaggioRouteService {

	
	public AffittuarioPrenotaViaggioRoute save(AffittuarioPrenotaViaggioRoute affittuarioPrenotaViaggioRoute) throws SavingUserPrenotaViaggioRouteException;
	
	public AffittuarioPrenotaViaggioRoute getById(int id) throws UserPrenotaViaggioRouteNotFoundException;
		
	public List<AffittuarioPrenotaViaggioRoute> getAll();

	public boolean delete (int id) throws UserPrenotaViaggioRouteNotFoundException,IllegalArgumentException;

	public List<AffittuarioPrenotaViaggioRoute> findByAffittuarioId(int userId) throws UserNotFoundException;

	public List<AffittuarioPrenotaViaggioRoute> findByViaggioRouteId(int viaggioRouteId) throws ViaggioRouteNotFoundException;

	public AffittuarioPrenotaViaggioRoute findByAffittuarioIdAndViaggioRouteId(int userId, int ViaggioRouteId) throws UserPrenotaViaggioRouteNotFoundException; 

}
