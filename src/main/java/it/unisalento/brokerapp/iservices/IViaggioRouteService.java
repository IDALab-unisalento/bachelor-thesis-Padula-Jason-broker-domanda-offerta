package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.ViaggioRoute;
import it.unisalento.brokerapp.exceptions.ViaggioRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingViaggioRouteException;
import it.unisalento.brokerapp.exceptions.ViaggioNotFoundException;
import it.unisalento.brokerapp.exceptions.RouteNotFoundException;


public interface IViaggioRouteService {

	
	public ViaggioRoute save(ViaggioRoute viaggioRoute) throws SavingViaggioRouteException;
	
	public ViaggioRoute getById(int id) throws ViaggioRouteNotFoundException;
		
	public List<ViaggioRoute> getAll();

	public boolean delete (int id) throws ViaggioRouteNotFoundException,IllegalArgumentException;

	public List<ViaggioRoute> findByViaggioId(int viaggioId) throws ViaggioNotFoundException;

	public List<ViaggioRoute> findByRouteId(int routeId) throws RouteNotFoundException;

	public ViaggioRoute findByViaggioIdAndRouteId(int viaggioId, int routeId) throws ViaggioRouteNotFoundException; 

}
