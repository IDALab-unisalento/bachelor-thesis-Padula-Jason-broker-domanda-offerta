package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.User;
import it.unisalento.brokerapp.exceptions.SavingUserException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;

public interface IUserService {

	public User getById(int id) throws UserNotFoundException;
	
	public List<User> getAll();
	
	public User save(User user) throws SavingUserException;
	
	public boolean delete(int id) throws UserNotFoundException;
	
	public User getByUsernameLike(String username) throws UserNotFoundException;

	public User getByUsernameAndPassword(String username, String password) throws UserNotFoundException;

	
}
