package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.Offer;
import it.unisalento.brokerapp.exceptions.OfferNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingOfferException;
import it.unisalento.brokerapp.iservices.IOfferService;
import it.unisalento.brokerapp.repositories.OfferRepository;

@Service
public class OfferServiceImpl implements IOfferService{

	@Autowired
	OfferRepository offerRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingOfferException.class)
	public Offer save(Offer offer) throws SavingOfferException{
		
		try {
			return offerRepository.save(offer);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingOfferException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = OfferNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public Offer getById(int id) throws OfferNotFoundException {
		
														//function arrow
		return offerRepository.findById(id).orElseThrow(()->new OfferNotFoundException());
	}
	
	@Override
	@Transactional
	public List<Offer> getAll() {
		
		return offerRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = OfferNotFoundException.class)
	public boolean delete (int id) throws OfferNotFoundException, IllegalArgumentException{
		
		Offer offer = offerRepository.findById(id).orElseThrow(()-> new OfferNotFoundException());
		offerRepository.delete(offer);
		return true;
	}

	
}
