package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.Treats;
import it.unisalento.brokerapp.exceptions.SavingTreatsException;
import it.unisalento.brokerapp.exceptions.TreatsNotFoundException;
import it.unisalento.brokerapp.iservices.ITreatsService;
import it.unisalento.brokerapp.repositories.TreatsRepository;

@Service
public class TreatsServiceImpl implements ITreatsService{

	
	@Autowired
	TreatsRepository treatsRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingTreatsException.class)
	public Treats save(Treats treats) throws SavingTreatsException{
		
		try {
			return treatsRepository.save(treats);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingTreatsException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = TreatsNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public Treats getById(int id) throws TreatsNotFoundException {
		
														//function arrow
		return treatsRepository.findById(id).orElseThrow(()->new TreatsNotFoundException());
	}
	
	@Override
	@Transactional
	public List<Treats> getAll() {
		
		return treatsRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = TreatsNotFoundException.class)
	public boolean delete (int id) throws TreatsNotFoundException, IllegalArgumentException{
		
		Treats treats = treatsRepository.findById(id).orElseThrow(()-> new TreatsNotFoundException());
		treatsRepository.delete(treats);
		return true;
	}
}
