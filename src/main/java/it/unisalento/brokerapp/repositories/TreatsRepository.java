package it.unisalento.brokerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.unisalento.brokerapp.domainClasses.Treats;

@Repository
public interface TreatsRepository extends JpaRepository<Treats, Integer>{

}
