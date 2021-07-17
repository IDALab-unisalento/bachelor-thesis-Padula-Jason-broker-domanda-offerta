package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.ViaggioRoute;

@Repository
public interface ViaggioRouteRepository extends JpaRepository<ViaggioRoute, Integer>{

	
	public List<ViaggioRoute> findByViaggioId(int viaggioId);

	public List<ViaggioRoute> findByRouteId(int routeId);

	public ViaggioRoute findByViaggioIdAndRouteId(int viaggioId, int routeId);
	
	@Query(value="update brokerappdb.viaggio_route set brokerappdb.viaggio_route.available_capacity=?1 where id = ?2", nativeQuery=true)
	@Modifying
	public int updateCapacityByViaggioRouteId(double capacity, int viaggioRouteId);
}
