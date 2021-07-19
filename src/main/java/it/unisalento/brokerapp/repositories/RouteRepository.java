package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>{

	public Route findByStartCityAndEndCity(String startCity, String endCity);
	
	// trova tutte le rotte di uno specifico viaggio 
	@Query(value="select * from brokerappdb.route where id in (\r\n"
			+ "SELECT route_id FROM brokerappdb.viaggio_route where viaggio_id= ?1 \r\n"
			+ ")\r\n"
			+ "", nativeQuery=true)
	public List<Route> findAllRouteOfViaggioId (int viaggioId);
	
	
	@Query(value="SELECT * FROM brokerappdb.route where LOWER(end_city) like LOWER(?1)", nativeQuery=true)
	public List<Route> findByEndCity(String endCity);

}
