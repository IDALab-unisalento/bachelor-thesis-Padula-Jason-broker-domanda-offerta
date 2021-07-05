package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.OfferVectorTreats;

@Repository
public interface OfferVectorTreatsRepository extends JpaRepository<OfferVectorTreats, Integer>{

	
	public List<OfferVectorTreats> findByOfferVectorId(int offerVectorId);

	public List<OfferVectorTreats> findByTreatsId(int treatsId);

	public OfferVectorTreats findByOfferVectorIdAndTreatsId(int offerVectorId, int treatsId);
}
