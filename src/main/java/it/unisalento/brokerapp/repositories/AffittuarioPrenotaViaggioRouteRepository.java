package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.AffittuarioPrenotaViaggioRoute;

@Repository
public interface AffittuarioPrenotaViaggioRouteRepository extends JpaRepository<AffittuarioPrenotaViaggioRoute, Integer>{

	
	public List<AffittuarioPrenotaViaggioRoute> findByAffittuarioId(int userId);

	public List<AffittuarioPrenotaViaggioRoute> findByViaggioRouteId(int viaggioRouteId);

	public AffittuarioPrenotaViaggioRoute findByAffittuarioIdAndViaggioRouteId(int userId, int viaggioRouteId);

}
