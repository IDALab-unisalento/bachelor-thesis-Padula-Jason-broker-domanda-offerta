package it.unisalento.brokerapp.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unisalento.brokerapp.domainClasses.Administrator;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;


public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	public Administrator findByUsernameAndPassword(String username, String password);

	/*********************** enable user method *************************/

	@Query("UPDATE User u SET u.disabilitated = false , u.abilitationDate= :date  WHERE u.username = :username ")
	@Modifying
	public int EnableUser(@Param("username") String username, @Param("date") Date abilitationDate)
			throws UserNotFoundException;

	@Query("UPDATE User u SET u.disabilitated = false ,u.abilitationDate= :date  WHERE u.id = :id ")
	@Modifying
	public int EnableUserById(@Param("id") int id_user, @Param("date") Date abilitationDate)
			throws UserNotFoundException;

	
}
