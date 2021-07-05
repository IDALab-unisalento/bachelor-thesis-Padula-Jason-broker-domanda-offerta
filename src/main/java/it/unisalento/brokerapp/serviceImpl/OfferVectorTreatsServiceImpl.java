package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.OfferVectorTreats;
import it.unisalento.brokerapp.exceptions.OfferVectorNotFoundException;
import it.unisalento.brokerapp.exceptions.OfferVectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingOfferVectorTreatsException;
import it.unisalento.brokerapp.exceptions.TreatsNotFoundException;
import it.unisalento.brokerapp.iservices.IOfferVectorTreatsService;
import it.unisalento.brokerapp.repositories.OfferVectorTreatsRepository;



@Service
public class OfferVectorTreatsServiceImpl implements IOfferVectorTreatsService{

	
	@Autowired
	OfferVectorTreatsRepository offerVectorTreatsRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingOfferVectorTreatsException.class)
	public OfferVectorTreats save(OfferVectorTreats offerVectorTreats) throws SavingOfferVectorTreatsException{
		
		try {
			return offerVectorTreatsRepository.save(offerVectorTreats);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingOfferVectorTreatsException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = OfferVectorTreatsNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public OfferVectorTreats getById(int id) throws OfferVectorTreatsNotFoundException {
		
														//function arrow
		return offerVectorTreatsRepository.findById(id).orElseThrow(()->new OfferVectorTreatsNotFoundException());
	}
	
	@Override
	@Transactional
	public List<OfferVectorTreats> getAll() {
		
		return offerVectorTreatsRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = OfferVectorTreatsNotFoundException.class)
	public boolean delete (int id) throws OfferVectorTreatsNotFoundException, IllegalArgumentException{
		
		OfferVectorTreats offertreats = offerVectorTreatsRepository.findById(id).orElseThrow(()-> new OfferVectorTreatsNotFoundException());
		offerVectorTreatsRepository.delete(offertreats);
		return true;
	}
	
	@Override
	@Transactional(rollbackOn = OfferVectorTreatsNotFoundException.class)
	public List<OfferVectorTreats> findByOfferVectorId(int offerVectorId) throws OfferVectorNotFoundException {
		try {
			return offerVectorTreatsRepository.findByOfferVectorId(offerVectorId);

		} catch (Exception e) {
			throw new OfferVectorNotFoundException();
		}
		
	}
	
	
	@Override
	@Transactional(rollbackOn = OfferVectorTreatsNotFoundException.class)
	public List<OfferVectorTreats> findByTreatsId(int treatsId) throws TreatsNotFoundException {
		try {
			return offerVectorTreatsRepository.findByTreatsId(treatsId);

		} catch (Exception e) {
			throw new TreatsNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = OfferVectorTreatsNotFoundException.class)
	public OfferVectorTreats findByOfferVectorIdAndTreatsId(int offerVectorId, int structureId) throws OfferVectorTreatsNotFoundException {
		try {
			return offerVectorTreatsRepository.findByOfferVectorIdAndTreatsId(offerVectorId,structureId);

		} catch (Exception e) {
			throw new OfferVectorTreatsNotFoundException();
		}
		
	}

}
