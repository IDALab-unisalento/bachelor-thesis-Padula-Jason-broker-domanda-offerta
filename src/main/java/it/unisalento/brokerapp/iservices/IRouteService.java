package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.Route;
import it.unisalento.brokerapp.exceptions.SavingRouteException;
import it.unisalento.brokerapp.exceptions.RouteNotFoundException;

public interface IRouteService {

	
	public Route save(Route route) throws SavingRouteException;
	
	public Route getById(int id) throws RouteNotFoundException;
		
	public List<Route> getAll();

    boolean delete (int id) throws RouteNotFoundException,IllegalArgumentException;

	public Route getByStartCityAndEndCity(String startCity, String endCity) throws RouteNotFoundException;
 
}
