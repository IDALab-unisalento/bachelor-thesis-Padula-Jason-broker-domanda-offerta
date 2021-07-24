package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.Route;
import it.unisalento.brokerapp.exceptions.SavingRouteException;
import it.unisalento.brokerapp.exceptions.RouteNotFoundException;
import it.unisalento.brokerapp.iservices.IRouteService;
import it.unisalento.brokerapp.repositories.RouteRepository;

@Service
public class RouteServiceImpl implements IRouteService{

	
	@Autowired
	RouteRepository routeRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingRouteException.class)
	public Route save(Route route) throws SavingRouteException{
		
		try {
			return routeRepository.save(route);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingRouteException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = RouteNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public Route getById(int id) throws RouteNotFoundException {
		
														//function arrow
		return routeRepository.findById(id).orElseThrow(()->new RouteNotFoundException());
	}
	
	@Override
	@Transactional(rollbackOn = RouteNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public Route getByStartCityAndEndCity(String startCity, String endCity) throws RouteNotFoundException {
		
		try {
			return routeRepository.findByStartCityAndEndCity(startCity,endCity);

		} catch (Exception e) {
			throw new RouteNotFoundException();
		}
	}
	
	@Override
	@Transactional
	public List<Route> getAll() {
		
		return routeRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = RouteNotFoundException.class)
	public boolean delete (int id) throws RouteNotFoundException, IllegalArgumentException{
		
		Route route = routeRepository.findById(id).orElseThrow(()-> new RouteNotFoundException());
		routeRepository.delete(route);
		return true;
	}

	@Override
	@Transactional(rollbackOn = RouteNotFoundException.class)
	public List<Route> findAllRouteOfViaggioId(int viaggioId) {
		return routeRepository.findAllRouteOfViaggioId(viaggioId);
	}

	@Override
	public List<Route> findByEndCity(String endCity) {
		return routeRepository.findByEndCity(endCity);
	}
}
