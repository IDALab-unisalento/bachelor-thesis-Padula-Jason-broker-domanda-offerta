package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.unisalento.brokerapp.domainClasses.Viaggio;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Integer>{

	public List<Viaggio> findByVectorId(int viaggioId);

}
