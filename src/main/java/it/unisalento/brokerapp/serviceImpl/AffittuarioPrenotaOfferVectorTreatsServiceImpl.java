package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.AffittuarioPrenotaVectorRoute;
import it.unisalento.brokerapp.exceptions.VectorRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaVectorRouteException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaVectorRouteNotFoundException;
import it.unisalento.brokerapp.iservices.IAffittuarioPrenotaVectorRouteService;
import it.unisalento.brokerapp.repositories.AffittuarioPrenotaVectorRouteRepository;


@Service
public class AffittuarioPrenotaOfferVectorTreatsServiceImpl implements IAffittuarioPrenotaVectorRouteService{

	@Autowired
	AffittuarioPrenotaVectorRouteRepository affittuarioPrenotaVectorRouteRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingUserPrenotaVectorRouteException.class)
	public AffittuarioPrenotaVectorRoute save(AffittuarioPrenotaVectorRoute affittuarioPrenotaVectorRoute) throws SavingUserPrenotaVectorRouteException{
		
		try {
			return affittuarioPrenotaVectorRouteRepository.save(affittuarioPrenotaVectorRoute);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingUserPrenotaVectorRouteException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaVectorRouteNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public AffittuarioPrenotaVectorRoute getById(int id) throws UserPrenotaVectorRouteNotFoundException {
		
														//function arrow
		return affittuarioPrenotaVectorRouteRepository.findById(id).orElseThrow(()->new UserPrenotaVectorRouteNotFoundException());
	}
	
	@Override
	@Transactional
	public List<AffittuarioPrenotaVectorRoute> getAll() {
		
		return affittuarioPrenotaVectorRouteRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaVectorRouteNotFoundException.class)
	public boolean delete (int id) throws UserPrenotaVectorRouteNotFoundException, IllegalArgumentException{
		
		AffittuarioPrenotaVectorRoute affittuarioPrenotaVectorRoute = affittuarioPrenotaVectorRouteRepository.findById(id).orElseThrow(()-> new UserPrenotaVectorRouteNotFoundException());
		affittuarioPrenotaVectorRouteRepository.delete(affittuarioPrenotaVectorRoute);
		return true;
	}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaVectorRouteNotFoundException.class)
	public List<AffittuarioPrenotaVectorRoute> findByAffittuarioId(int userId) throws UserNotFoundException {
		try {
			return affittuarioPrenotaVectorRouteRepository.findByAffittuarioId(userId);

		} catch (Exception e) {
			throw new UserNotFoundException();
		}
		
	}
	
	
	@Override
	@Transactional(rollbackOn = VectorRouteNotFoundException.class)
	public List<AffittuarioPrenotaVectorRoute> findByVectorRouteId(int vectorTreatsId) throws VectorRouteNotFoundException {
		try {
			return affittuarioPrenotaVectorRouteRepository.findByVectorRouteId(vectorTreatsId);

		} catch (Exception e) {
			throw new VectorRouteNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = UserPrenotaVectorRouteNotFoundException.class)
	public AffittuarioPrenotaVectorRoute findByAffittuarioIdAndVectorRouteId(int userId, int vectorRouteId) throws UserPrenotaVectorRouteNotFoundException {
		try {
			return affittuarioPrenotaVectorRouteRepository.findByAffittuarioIdAndVectorRouteId(userId,vectorRouteId);

		} catch (Exception e) {
			throw new UserPrenotaVectorRouteNotFoundException();
		}
		
	}
}
