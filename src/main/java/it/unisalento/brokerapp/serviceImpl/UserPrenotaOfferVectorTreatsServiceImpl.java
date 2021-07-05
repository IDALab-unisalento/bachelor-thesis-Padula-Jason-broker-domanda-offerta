package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.UserPrenotaVectorRoute;
import it.unisalento.brokerapp.exceptions.VectorRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaVectorRouteException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaVectorRouteNotFoundException;
import it.unisalento.brokerapp.iservices.IUserPrenotaVectorRouteService;
import it.unisalento.brokerapp.repositories.UserPrenotaVectorRouteRepository;


@Service
public class UserPrenotaOfferVectorTreatsServiceImpl implements IUserPrenotaVectorRouteService{

	@Autowired
	UserPrenotaVectorRouteRepository userPrenotaVectorRouteRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingUserPrenotaVectorRouteException.class)
	public UserPrenotaVectorRoute save(UserPrenotaVectorRoute userPrenotaVectorRoute) throws SavingUserPrenotaVectorRouteException{
		
		try {
			return userPrenotaVectorRouteRepository.save(userPrenotaVectorRoute);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingUserPrenotaVectorRouteException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaVectorRouteNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public UserPrenotaVectorRoute getById(int id) throws UserPrenotaVectorRouteNotFoundException {
		
														//function arrow
		return userPrenotaVectorRouteRepository.findById(id).orElseThrow(()->new UserPrenotaVectorRouteNotFoundException());
	}
	
	@Override
	@Transactional
	public List<UserPrenotaVectorRoute> getAll() {
		
		return userPrenotaVectorRouteRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaVectorRouteNotFoundException.class)
	public boolean delete (int id) throws UserPrenotaVectorRouteNotFoundException, IllegalArgumentException{
		
		UserPrenotaVectorRoute userPrenotaVectorRoute = userPrenotaVectorRouteRepository.findById(id).orElseThrow(()-> new UserPrenotaVectorRouteNotFoundException());
		userPrenotaVectorRouteRepository.delete(userPrenotaVectorRoute);
		return true;
	}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaVectorRouteNotFoundException.class)
	public List<UserPrenotaVectorRoute> findByUserId(int userId) throws UserNotFoundException {
		try {
			return userPrenotaVectorRouteRepository.findByUserId(userId);

		} catch (Exception e) {
			throw new UserNotFoundException();
		}
		
	}
	
	
	@Override
	@Transactional(rollbackOn = VectorRouteNotFoundException.class)
	public List<UserPrenotaVectorRoute> findByVectorRouteId(int vectorTreatsId) throws VectorRouteNotFoundException {
		try {
			return userPrenotaVectorRouteRepository.findByVectorRouteId(vectorTreatsId);

		} catch (Exception e) {
			throw new VectorRouteNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = UserPrenotaVectorRouteNotFoundException.class)
	public UserPrenotaVectorRoute findByUserIdAndVectorRouteId(int userId, int vectorRouteId) throws UserPrenotaVectorRouteNotFoundException {
		try {
			return userPrenotaVectorRouteRepository.findByUserIdAndVectorRouteId(userId,vectorRouteId);

		} catch (Exception e) {
			throw new UserPrenotaVectorRouteNotFoundException();
		}
		
	}
}
