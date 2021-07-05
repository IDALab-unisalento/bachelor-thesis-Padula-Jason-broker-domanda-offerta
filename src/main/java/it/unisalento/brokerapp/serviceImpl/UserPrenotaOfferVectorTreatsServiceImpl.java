package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.UserPrenotaOfferVectorTreats;
import it.unisalento.brokerapp.exceptions.OfferVectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaOfferVectorTreatsException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaOfferVectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.iservices.IUserPrenotaOfferVectorTreatsService;
import it.unisalento.brokerapp.repositories.UserPrenotaOfferVectorTreatsRepository;


@Service
public class UserPrenotaOfferVectorTreatsServiceImpl implements IUserPrenotaOfferVectorTreatsService{

	@Autowired
	UserPrenotaOfferVectorTreatsRepository userPrenotaOfferVectorTreatsRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingUserPrenotaOfferVectorTreatsException.class)
	public UserPrenotaOfferVectorTreats save(UserPrenotaOfferVectorTreats userPrenotaOfferVectorTreats) throws SavingUserPrenotaOfferVectorTreatsException{
		
		try {
			return userPrenotaOfferVectorTreatsRepository.save(userPrenotaOfferVectorTreats);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingUserPrenotaOfferVectorTreatsException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaOfferVectorTreatsNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public UserPrenotaOfferVectorTreats getById(int id) throws UserPrenotaOfferVectorTreatsNotFoundException {
		
														//function arrow
		return userPrenotaOfferVectorTreatsRepository.findById(id).orElseThrow(()->new UserPrenotaOfferVectorTreatsNotFoundException());
	}
	
	@Override
	@Transactional
	public List<UserPrenotaOfferVectorTreats> getAll() {
		
		return userPrenotaOfferVectorTreatsRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaOfferVectorTreatsNotFoundException.class)
	public boolean delete (int id) throws UserPrenotaOfferVectorTreatsNotFoundException, IllegalArgumentException{
		
		UserPrenotaOfferVectorTreats userPrenotaOfferVectorTreats = userPrenotaOfferVectorTreatsRepository.findById(id).orElseThrow(()-> new UserPrenotaOfferVectorTreatsNotFoundException());
		userPrenotaOfferVectorTreatsRepository.delete(userPrenotaOfferVectorTreats);
		return true;
	}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaOfferVectorTreatsNotFoundException.class)
	public List<UserPrenotaOfferVectorTreats> findByUserId(int userId) throws UserNotFoundException {
		try {
			return userPrenotaOfferVectorTreatsRepository.findByUserId(userId);

		} catch (Exception e) {
			throw new UserNotFoundException();
		}
		
	}
	
	
	@Override
	@Transactional(rollbackOn = VectorNotFoundException.class)
	public List<UserPrenotaOfferVectorTreats> findByOfferVectorTreatsId(int offerVectorTreatsId) throws OfferVectorTreatsNotFoundException {
		try {
			return userPrenotaOfferVectorTreatsRepository.findByOfferVectorTreatsId(offerVectorTreatsId);

		} catch (Exception e) {
			throw new OfferVectorTreatsNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = UserPrenotaOfferVectorTreatsNotFoundException.class)
	public UserPrenotaOfferVectorTreats findByUserIdAndOfferVectorTreatsId(int userId, int offerVectorTreatsId) throws UserPrenotaOfferVectorTreatsNotFoundException {
		try {
			return userPrenotaOfferVectorTreatsRepository.findByUserIdAndOfferVectorTreatsId(userId,offerVectorTreatsId);

		} catch (Exception e) {
			throw new UserPrenotaOfferVectorTreatsNotFoundException();
		}
		
	}
}
