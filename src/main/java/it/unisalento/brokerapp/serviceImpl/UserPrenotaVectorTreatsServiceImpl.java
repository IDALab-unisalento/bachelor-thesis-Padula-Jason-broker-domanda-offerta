package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.UserPrenotaVectorTreats;
import it.unisalento.brokerapp.exceptions.VectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaVectorTreatsException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaVectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.iservices.IUserPrenotaVectorTreatsService;
import it.unisalento.brokerapp.repositories.UserPrenotaVectorTreatsRepository;


@Service
public class UserPrenotaVectorTreatsServiceImpl implements IUserPrenotaVectorTreatsService{

	@Autowired
	UserPrenotaVectorTreatsRepository userPrenotaVectorTreatsRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingUserPrenotaVectorTreatsException.class)
	public UserPrenotaVectorTreats save(UserPrenotaVectorTreats userPrenotaVectorTreats) throws SavingUserPrenotaVectorTreatsException{
		
		try {
			return userPrenotaVectorTreatsRepository.save(userPrenotaVectorTreats);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingUserPrenotaVectorTreatsException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaVectorTreatsNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public UserPrenotaVectorTreats getById(int id) throws UserPrenotaVectorTreatsNotFoundException {
		
														//function arrow
		return userPrenotaVectorTreatsRepository.findById(id).orElseThrow(()->new UserPrenotaVectorTreatsNotFoundException());
	}
	
	@Override
	@Transactional
	public List<UserPrenotaVectorTreats> getAll() {
		
		return userPrenotaVectorTreatsRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaVectorTreatsNotFoundException.class)
	public boolean delete (int id) throws UserPrenotaVectorTreatsNotFoundException, IllegalArgumentException{
		
		UserPrenotaVectorTreats userPrenotaVectorTreats = userPrenotaVectorTreatsRepository.findById(id).orElseThrow(()-> new UserPrenotaVectorTreatsNotFoundException());
		userPrenotaVectorTreatsRepository.delete(userPrenotaVectorTreats);
		return true;
	}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaVectorTreatsNotFoundException.class)
	public List<UserPrenotaVectorTreats> findByUserId(int userId) throws UserNotFoundException {
		try {
			return userPrenotaVectorTreatsRepository.findByUserId(userId);

		} catch (Exception e) {
			throw new UserNotFoundException();
		}
		
	}
	
	
	@Override
	@Transactional(rollbackOn = VectorNotFoundException.class)
	public List<UserPrenotaVectorTreats> findByVectorTreatsId(int vectorTreatsId) throws VectorTreatsNotFoundException {
		try {
			return userPrenotaVectorTreatsRepository.findByVectorTreatsId(vectorTreatsId);

		} catch (Exception e) {
			throw new VectorTreatsNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = UserPrenotaVectorTreatsNotFoundException.class)
	public UserPrenotaVectorTreats findByUserIdAndVectorTreatsId(int userId, int vectorTreatsId) throws UserPrenotaVectorTreatsNotFoundException {
		try {
			return userPrenotaVectorTreatsRepository.findByUserIdAndVectorTreatsId(userId,vectorTreatsId);

		} catch (Exception e) {
			throw new UserPrenotaVectorTreatsNotFoundException();
		}
		
	}
}
