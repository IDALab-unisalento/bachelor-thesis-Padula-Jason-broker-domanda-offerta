package it.unisalento.brokerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.Offer;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>{

}
