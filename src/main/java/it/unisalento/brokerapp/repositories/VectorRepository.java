package it.unisalento.brokerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.Vector;

@Repository
public interface VectorRepository extends JpaRepository<Vector, Integer>{

	public Vector findByLicensePlate(String licensePlate);

}
