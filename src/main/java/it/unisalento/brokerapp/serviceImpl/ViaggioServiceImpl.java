package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.Viaggio;
import it.unisalento.brokerapp.exceptions.SavingViaggioException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.exceptions.ViaggioNotFoundException;
import it.unisalento.brokerapp.iservices.IViaggioService;
import it.unisalento.brokerapp.repositories.ViaggioRepository;

@Service
public class ViaggioServiceImpl implements IViaggioService{

	
	@Autowired
	ViaggioRepository viaggioRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingViaggioException.class)
	public Viaggio save(Viaggio viaggio) throws SavingViaggioException{
		
		try {
			return viaggioRepository.save(viaggio);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingViaggioException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = ViaggioNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public Viaggio getById(int id) throws ViaggioNotFoundException {
		
														//function arrow
		return viaggioRepository.findById(id).orElseThrow(()->new ViaggioNotFoundException());
	}
	
	@Override
	@Transactional
	public List<Viaggio> getAll() {
		
		return viaggioRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = ViaggioNotFoundException.class)
	public boolean delete (int id) throws ViaggioNotFoundException, IllegalArgumentException{
		
		Viaggio viaggio = viaggioRepository.findById(id).orElseThrow(()-> new ViaggioNotFoundException());
		viaggioRepository.delete(viaggio);
		
		return true;
	}
	
	@Override
	@Transactional
	public List<Viaggio> findByVectorId(int vectorId) throws VectorNotFoundException {
		try {
			return viaggioRepository.findByVectorId(vectorId);

		} catch (Exception e) {
			throw new VectorNotFoundException();
		}
	}
	
	@Override
	@Transactional
	public List<Viaggio> findByCompanyId(int companyId) throws UserNotFoundException {
		try {
			return viaggioRepository.findByCompanyId(companyId);

		} catch (Exception e) {
			throw new UserNotFoundException();
		}
	}
}
