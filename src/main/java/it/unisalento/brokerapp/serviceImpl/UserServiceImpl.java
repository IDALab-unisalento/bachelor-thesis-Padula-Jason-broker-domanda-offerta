package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.User;
import it.unisalento.brokerapp.exceptions.SavingUserException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.iservices.IUserService;
import it.unisalento.brokerapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional(rollbackOn =SavingUserException.class)
	public User save(User user) throws SavingUserException{
		
		try {
			return userRepository.save(user);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingUserException();
			}
		
		}
	
	
	@Transactional(rollbackOn = UserNotFoundException.class)
	@Override
	public User getById(int userId) throws UserNotFoundException{
		return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
		
	}
	
	@Transactional
	@Override
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = {UserNotFoundException.class, EntityNotFoundException.class})
	public boolean delete(int id) throws UserNotFoundException, IllegalArgumentException {

		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
		userRepository.delete(user);
		return true;
	}
	
	@Override
	@Transactional(rollbackOn = UserNotFoundException.class)
	public User getByUsernameAndPassword(String username, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		try {
			return userRepository.findByUsernameAndPassword(username, password);

		} catch (Exception e) {
			throw new UserNotFoundException();
		}

	}
	

	@Override
	@Transactional(rollbackOn = UserNotFoundException.class)
	public User getByUsernameLike(String username) throws UserNotFoundException {
		try {
			return userRepository.findByUsername(username);

		} catch (Exception e) {
			throw new UserNotFoundException();
		}

	}
}
