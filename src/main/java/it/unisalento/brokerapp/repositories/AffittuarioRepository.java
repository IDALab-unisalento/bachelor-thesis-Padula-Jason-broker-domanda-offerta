package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.Affittuario;


@Repository
public interface AffittuarioRepository extends JpaRepository<Affittuario, Integer>{
	
	public List<Affittuario> findByDisabilitated(boolean disabilitated);

}
