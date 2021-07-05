package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.UserPrenotaVectorRoute;

@Repository
public interface UserPrenotaVectorRouteRepository extends JpaRepository<UserPrenotaVectorRoute, Integer>{

	
	public List<UserPrenotaVectorRoute> findByUserId(int userId);

	public List<UserPrenotaVectorRoute> findByVectorRouteId(int vectorRouteId);

	public UserPrenotaVectorRoute findByUserIdAndVectorRouteId(int userId, int vectorRouteId);

}
