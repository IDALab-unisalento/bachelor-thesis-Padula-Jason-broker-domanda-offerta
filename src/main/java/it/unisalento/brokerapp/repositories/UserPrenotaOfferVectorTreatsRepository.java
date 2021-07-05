package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.UserPrenotaOfferVectorTreats;

@Repository
public interface UserPrenotaOfferVectorTreatsRepository extends JpaRepository<UserPrenotaOfferVectorTreats, Integer>{

	
	public List<UserPrenotaOfferVectorTreats> findByUserId(int userId);

	public List<UserPrenotaOfferVectorTreats> findByOfferVectorTreatsId(int offerVectorTreatsId);

	public UserPrenotaOfferVectorTreats findByUserIdAndOfferVectorTreatsId(int userId, int offerVectorTreatsId);

}
