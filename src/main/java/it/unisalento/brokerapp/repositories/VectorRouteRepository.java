package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.VectorRoute;

@Repository
public interface VectorRouteRepository extends JpaRepository<VectorRoute, Integer>{

	
	public List<VectorRoute> findByVectorId(int vectorId);

	public List<VectorRoute> findByRouteId(int routeId);

	public VectorRoute findByVectorIdAndRouteId(int vectorId, int routeId);
}
