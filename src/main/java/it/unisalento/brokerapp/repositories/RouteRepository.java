package it.unisalento.brokerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.unisalento.brokerapp.domainClasses.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>{

}
