
package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.Affittuario;
import it.unisalento.brokerapp.exceptions.SavingUserException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;


public interface IAffittuarioService {

	public Affittuario save(Affittuario affittuario) throws SavingUserException;

	public Affittuario getById(int id) throws UserNotFoundException;

	public List<Affittuario> getAll();

	public boolean delete(int id) throws UserNotFoundException, IllegalArgumentException;

	
}