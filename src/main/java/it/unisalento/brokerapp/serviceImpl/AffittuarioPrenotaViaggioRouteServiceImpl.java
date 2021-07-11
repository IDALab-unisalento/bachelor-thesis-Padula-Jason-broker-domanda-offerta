package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.AffittuarioPrenotaViaggioRoute;
import it.unisalento.brokerapp.exceptions.ViaggioRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaViaggioRouteException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaViaggioRouteNotFoundException;
import it.unisalento.brokerapp.iservices.IAffittuarioPrenotaViaggioRouteService;
import it.unisalento.brokerapp.repositories.AffittuarioPrenotaViaggioRouteRepository;


@Service
public class AffittuarioPrenotaViaggioRouteServiceImpl implements IAffittuarioPrenotaViaggioRouteService{

	@Autowired
	AffittuarioPrenotaViaggioRouteRepository affittuarioPrenotaViaggioRouteRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingUserPrenotaViaggioRouteException.class)
	public AffittuarioPrenotaViaggioRoute save(AffittuarioPrenotaViaggioRoute affittuarioPrenotaViaggioRoute) throws SavingUserPrenotaViaggioRouteException{
		
		try {
			return affittuarioPrenotaViaggioRouteRepository.save(affittuarioPrenotaViaggioRoute);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingUserPrenotaViaggioRouteException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaViaggioRouteNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public AffittuarioPrenotaViaggioRoute getById(int id) throws UserPrenotaViaggioRouteNotFoundException {
		
														//function arrow
		return affittuarioPrenotaViaggioRouteRepository.findById(id).orElseThrow(()->new UserPrenotaViaggioRouteNotFoundException());
	}
	
	@Override
	@Transactional
	public List<AffittuarioPrenotaViaggioRoute> getAll() {
		
		return affittuarioPrenotaViaggioRouteRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaViaggioRouteNotFoundException.class)
	public boolean delete (int id) throws UserPrenotaViaggioRouteNotFoundException, IllegalArgumentException{
		
		AffittuarioPrenotaViaggioRoute affittuarioPrenotaViaggioRoute = affittuarioPrenotaViaggioRouteRepository.findById(id).orElseThrow(()-> new UserPrenotaViaggioRouteNotFoundException());
		affittuarioPrenotaViaggioRouteRepository.delete(affittuarioPrenotaViaggioRoute);
		return true;
	}
	
	@Override
	@Transactional(rollbackOn = UserPrenotaViaggioRouteNotFoundException.class)
	public List<AffittuarioPrenotaViaggioRoute> findByAffittuarioId(int userId) throws UserNotFoundException {
		try {
			return affittuarioPrenotaViaggioRouteRepository.findByAffittuarioId(userId);

		} catch (Exception e) {
			throw new UserNotFoundException();
		}
		
	}
	
	
	@Override
	@Transactional(rollbackOn = ViaggioRouteNotFoundException.class)
	public List<AffittuarioPrenotaViaggioRoute> findByViaggioRouteId(int viaggioTreatsId) throws ViaggioRouteNotFoundException {
		try {
			return affittuarioPrenotaViaggioRouteRepository.findByViaggioRouteId(viaggioTreatsId);

		} catch (Exception e) {
			throw new ViaggioRouteNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = UserPrenotaViaggioRouteNotFoundException.class)
	public AffittuarioPrenotaViaggioRoute findByAffittuarioIdAndViaggioRouteId(int userId, int viaggioRouteId) throws UserPrenotaViaggioRouteNotFoundException {
		try {
			return affittuarioPrenotaViaggioRouteRepository.findByAffittuarioIdAndViaggioRouteId(userId,viaggioRouteId);

		} catch (Exception e) {
			throw new UserPrenotaViaggioRouteNotFoundException();
		}
		
	}
}
