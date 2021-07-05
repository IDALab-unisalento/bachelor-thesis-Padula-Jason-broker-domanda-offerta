package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.VectorRoute;
import it.unisalento.brokerapp.exceptions.VectorRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingVectorRouteException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.exceptions.RouteNotFoundException;
import it.unisalento.brokerapp.iservices.IVectorRouteService;
import it.unisalento.brokerapp.repositories.VectorRouteRepository;



@Service
public class VectorRouteServiceImpl implements IVectorRouteService{

	
	@Autowired
	VectorRouteRepository vectorRouteRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingVectorRouteException.class)
	public VectorRoute save(VectorRoute vectorRoute) throws SavingVectorRouteException{
		
		try {
			return vectorRouteRepository.save(vectorRoute);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingVectorRouteException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = VectorRouteNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public VectorRoute getById(int id) throws VectorRouteNotFoundException {
		
														//function arrow
		return vectorRouteRepository.findById(id).orElseThrow(()->new VectorRouteNotFoundException());
	}
	
	@Override
	@Transactional
	public List<VectorRoute> getAll() {
		
		return vectorRouteRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = VectorRouteNotFoundException.class)
	public boolean delete (int id) throws VectorRouteNotFoundException, IllegalArgumentException{
		
		VectorRoute offertreats = vectorRouteRepository.findById(id).orElseThrow(()-> new VectorRouteNotFoundException());
		vectorRouteRepository.delete(offertreats);
		return true;
	}
	
	@Override
	@Transactional(rollbackOn = VectorNotFoundException.class)
	public List<VectorRoute> findByVectorId(int vectorId) throws VectorNotFoundException {
		try {
			return vectorRouteRepository.findByVectorId(vectorId);

		} catch (Exception e) {
			throw new VectorNotFoundException();
		}
		
	}
	
	
	@Override
	@Transactional(rollbackOn = RouteNotFoundException.class)
	public List<VectorRoute> findByRouteId(int routeId) throws RouteNotFoundException {
		try {
			return vectorRouteRepository.findByRouteId(routeId);

		} catch (Exception e) {
			throw new RouteNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = VectorRouteNotFoundException.class)
	public VectorRoute findByVectorIdAndRouteId(int vectorId, int routeId) throws VectorRouteNotFoundException {
		try {
			return vectorRouteRepository.findByVectorIdAndRouteId(vectorId,routeId);

		} catch (Exception e) {
			throw new VectorRouteNotFoundException();
		}
		
	}

}
