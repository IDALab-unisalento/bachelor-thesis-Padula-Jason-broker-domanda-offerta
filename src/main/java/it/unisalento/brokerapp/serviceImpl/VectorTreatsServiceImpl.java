package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.VectorTreats;
import it.unisalento.brokerapp.exceptions.VectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingVectorTreatsException;
import it.unisalento.brokerapp.exceptions.TreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.iservices.IVectorTreatsService;
import it.unisalento.brokerapp.repositories.VectorTreatsRepository;



@Service
public class VectorTreatsServiceImpl implements IVectorTreatsService{

	
	@Autowired
	VectorTreatsRepository vectorTreatsRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingVectorTreatsException.class)
	public VectorTreats save(VectorTreats vectorTreats) throws SavingVectorTreatsException{
		
		try {
			return vectorTreatsRepository.save(vectorTreats);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingVectorTreatsException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = VectorTreatsNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public VectorTreats getById(int id) throws VectorTreatsNotFoundException {
		
														//function arrow
		return vectorTreatsRepository.findById(id).orElseThrow(()->new VectorTreatsNotFoundException());
	}
	
	@Override
	@Transactional
	public List<VectorTreats> getAll() {
		
		return vectorTreatsRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = VectorTreatsNotFoundException.class)
	public boolean delete (int id) throws VectorTreatsNotFoundException, IllegalArgumentException{
		
		VectorTreats offertreats = vectorTreatsRepository.findById(id).orElseThrow(()-> new VectorTreatsNotFoundException());
		vectorTreatsRepository.delete(offertreats);
		return true;
	}
	
	@Override
	@Transactional(rollbackOn = VectorTreatsNotFoundException.class)
	public List<VectorTreats> findByVectorId(int vectorId) throws VectorNotFoundException {
		try {
			return vectorTreatsRepository.findByVectorId(vectorId);

		} catch (Exception e) {
			throw new VectorNotFoundException();
		}
		
	}
	
	
	@Override
	@Transactional(rollbackOn = VectorTreatsNotFoundException.class)
	public List<VectorTreats> findByTreatsId(int treatsId) throws TreatsNotFoundException {
		try {
			return vectorTreatsRepository.findByTreatsId(treatsId);

		} catch (Exception e) {
			throw new TreatsNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = VectorTreatsNotFoundException.class)
	public VectorTreats findByVectorIdAndTreatsId(int vectorId, int structureId) throws VectorTreatsNotFoundException {
		try {
			return vectorTreatsRepository.findByVectorIdAndTreatsId(vectorId,structureId);

		} catch (Exception e) {
			throw new VectorTreatsNotFoundException();
		}
		
	}

}
