package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.VectorRoute;
import it.unisalento.brokerapp.exceptions.VectorRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingVectorRouteException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.exceptions.RouteNotFoundException;


public interface IVectorRouteService {

	
	public VectorRoute save(VectorRoute vectorRoute) throws SavingVectorRouteException;
	
	public VectorRoute getById(int id) throws VectorRouteNotFoundException;
		
	public List<VectorRoute> getAll();

	public boolean delete (int id) throws VectorRouteNotFoundException,IllegalArgumentException;

	public List<VectorRoute> findByVectorId(int vectorId) throws VectorNotFoundException;

	public List<VectorRoute> findByRouteId(int routeId) throws RouteNotFoundException;

	public VectorRoute findByVectorIdAndRouteId(int vectorId, int routeId) throws VectorRouteNotFoundException; 

}
