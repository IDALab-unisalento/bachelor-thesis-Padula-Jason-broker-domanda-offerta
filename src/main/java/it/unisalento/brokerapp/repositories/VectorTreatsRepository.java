package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.VectorTreats;

@Repository
public interface VectorTreatsRepository extends JpaRepository<VectorTreats, Integer>{

	
	public List<VectorTreats> findByVectorId(int vectorId);

	public List<VectorTreats> findByTreatsId(int treatsId);

	public VectorTreats findByVectorIdAndTreatsId(int vectorId, int treatsId);
}
