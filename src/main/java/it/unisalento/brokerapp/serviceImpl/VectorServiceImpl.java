package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.Vector;
import it.unisalento.brokerapp.exceptions.SavingVectorException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.iservices.IVectorService;
import it.unisalento.brokerapp.repositories.VectorRepository;

@Service
public class VectorServiceImpl implements IVectorService{

	
	@Autowired
	VectorRepository vectorRepository;
	
	@Override
	@Transactional(rollbackOn =SavingVectorException.class)
	public Vector save(Vector vector) throws SavingVectorException{
		
		try {
			return vectorRepository.save(vector);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingVectorException();
			}
		
		}
	
	
	@Transactional(rollbackOn = VectorNotFoundException.class)
	@Override
	public Vector getById(int userId) throws VectorNotFoundException{
		return vectorRepository.findById(userId).orElseThrow(() -> new VectorNotFoundException());
		
	}
	
	@Transactional
	@Override
	public List<Vector> getAll(){
		return vectorRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = {VectorNotFoundException.class, EntityNotFoundException.class})
	public boolean delete(int id) throws VectorNotFoundException, IllegalArgumentException {

		Vector vector = vectorRepository.findById(id).orElseThrow(() -> new VectorNotFoundException());
		vectorRepository.delete(vector);
		return true;
	}
	
	@Override
	@Transactional(rollbackOn = VectorNotFoundException.class)

	public Vector findByLicensePlate(String licensePlate) throws VectorNotFoundException {
		try {
			return vectorRepository.findByLicensePlate(licensePlate);
		} catch (Exception e) {
			throw new VectorNotFoundException();
		}
	}
	

}
