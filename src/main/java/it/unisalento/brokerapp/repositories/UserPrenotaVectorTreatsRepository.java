package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.UserPrenotaVectorTreats;

@Repository
public interface UserPrenotaVectorTreatsRepository extends JpaRepository<UserPrenotaVectorTreats, Integer>{

	
	public List<UserPrenotaVectorTreats> findByUserId(int userId);

	public List<UserPrenotaVectorTreats> findByVectorTreatsId(int vectorTreatsId);

	public UserPrenotaVectorTreats findByUserIdAndVectorTreatsId(int userId, int vectorTreatsId);

}
