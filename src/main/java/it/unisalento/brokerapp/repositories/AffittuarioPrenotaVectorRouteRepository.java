package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.AffittuarioPrenotaVectorRoute;

@Repository
public interface AffittuarioPrenotaVectorRouteRepository extends JpaRepository<AffittuarioPrenotaVectorRoute, Integer>{

	
	public List<AffittuarioPrenotaVectorRoute> findByAffittuarioId(int userId);

	public List<AffittuarioPrenotaVectorRoute> findByVectorRouteId(int vectorRouteId);

	public AffittuarioPrenotaVectorRoute findByAffittuarioIdAndVectorRouteId(int userId, int vectorRouteId);

}
