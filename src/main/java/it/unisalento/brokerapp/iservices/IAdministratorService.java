
package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.Administrator;
import it.unisalento.brokerapp.exceptions.SavingUserException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;


public interface IAdministratorService {

	public Administrator save(Administrator admin) throws SavingUserException;

	public Administrator getById(int id) throws UserNotFoundException;

	public List<Administrator> getAll();

	public boolean delete(int id) throws UserNotFoundException, IllegalArgumentException;


	public boolean EnableUserById(int id_User) throws UserNotFoundException;

	
}