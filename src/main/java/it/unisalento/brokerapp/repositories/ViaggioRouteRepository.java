package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.ViaggioRoute;

@Repository
public interface ViaggioRouteRepository extends JpaRepository<ViaggioRoute, Integer>{

	
	public List<ViaggioRoute> findByViaggioId(int viaggioId);

	public List<ViaggioRoute> findByRouteId(int routeId);

	public ViaggioRoute findByViaggioIdAndRouteId(int viaggioId, int routeId);
}
