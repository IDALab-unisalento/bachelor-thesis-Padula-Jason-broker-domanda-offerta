package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.OfferVector;
import it.unisalento.brokerapp.exceptions.OfferNotFoundException;
import it.unisalento.brokerapp.exceptions.VectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.OfferVectorNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingOfferVectorException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.iservices.IOfferVectorService;
import it.unisalento.brokerapp.repositories.OfferVectorRepository;


@Service
public class OfferVectorServiceImpl implements IOfferVectorService{

	
	
	@Autowired
	OfferVectorRepository offerVectorRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingOfferVectorException.class)
	public OfferVector save(OfferVector offerVector) throws SavingOfferVectorException{
		
		try {
			return offerVectorRepository.save(offerVector);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingOfferVectorException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = OfferVectorNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public OfferVector getById(int id) throws OfferVectorNotFoundException {
		
														//function arrow
		return offerVectorRepository.findById(id).orElseThrow(()->new OfferVectorNotFoundException());
	}
	
	@Override
	@Transactional
	public List<OfferVector> getAll() {
		
		return offerVectorRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = OfferVectorNotFoundException.class)
	public boolean delete (int id) throws OfferVectorNotFoundException, IllegalArgumentException{
		
		OfferVector offerVector = offerVectorRepository.findById(id).orElseThrow(()-> new OfferVectorNotFoundException());
		offerVectorRepository.delete(offerVector);
		return true;
	}
	
	
	@Override
	@Transactional(rollbackOn = VectorTreatsNotFoundException.class)
	public List<OfferVector> findByOfferId(int offerId) throws OfferNotFoundException {
		try {
			return offerVectorRepository.findByOfferId(offerId);

		} catch (Exception e) {
			throw new OfferNotFoundException();
		}
		
	}
	
	
	@Override
	@Transactional(rollbackOn = VectorTreatsNotFoundException.class)
	public List<OfferVector> findByVectorId(int vectorId) throws VectorNotFoundException {
		try {
			return offerVectorRepository.findByVectorId(vectorId);

		} catch (Exception e) {
			throw new VectorNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = OfferVectorNotFoundException.class)
	public OfferVector findByOfferIdAndVectorId(int offerId, int vectorId) throws OfferVectorNotFoundException {
		try {
			return offerVectorRepository.findByOfferIdAndVectorId(offerId,vectorId);

		} catch (Exception e) {
			throw new OfferVectorNotFoundException();
		}
		
	}
}
