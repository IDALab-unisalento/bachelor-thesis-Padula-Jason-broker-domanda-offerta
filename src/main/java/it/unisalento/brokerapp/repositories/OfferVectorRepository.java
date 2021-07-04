package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.OfferVector;

@Repository
public interface OfferVectorRepository extends JpaRepository<OfferVector, Integer>{

	
	public List<OfferVector> findByOfferId(int offerId);

	public List<OfferVector> findByVectorId(int vectorId);

	public OfferVector findByOfferIdAndVectorId(int offerId, int vectorId);
}
