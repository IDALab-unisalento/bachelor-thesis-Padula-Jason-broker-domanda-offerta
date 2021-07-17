package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.ViaggioRoute;
import it.unisalento.brokerapp.exceptions.ViaggioRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingViaggioRouteException;
import it.unisalento.brokerapp.exceptions.ViaggioNotFoundException;
import it.unisalento.brokerapp.exceptions.RouteNotFoundException;
import it.unisalento.brokerapp.iservices.IViaggioRouteService;
import it.unisalento.brokerapp.repositories.ViaggioRouteRepository;

@Service
public class ViaggioRouteServiceImpl implements IViaggioRouteService{

	
	@Autowired
	ViaggioRouteRepository viaggioRouteRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingViaggioRouteException.class)
	public ViaggioRoute save(ViaggioRoute viaggioRoute) throws SavingViaggioRouteException{
		
		try {
			return viaggioRouteRepository.save(viaggioRoute);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingViaggioRouteException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = ViaggioRouteNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public ViaggioRoute getById(int id) throws ViaggioRouteNotFoundException {
		
														//function arrow
		return viaggioRouteRepository.findById(id).orElseThrow(()->new ViaggioRouteNotFoundException());
	}
	
	@Override
	@Transactional
	public List<ViaggioRoute> getAll() {
		
		return viaggioRouteRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = ViaggioRouteNotFoundException.class)
	public boolean delete (int id) throws ViaggioRouteNotFoundException, IllegalArgumentException{
		
		ViaggioRoute offertreats = viaggioRouteRepository.findById(id).orElseThrow(()-> new ViaggioRouteNotFoundException());
		viaggioRouteRepository.delete(offertreats);
		return true;
	}
	
	@Override
	@Transactional(rollbackOn = ViaggioNotFoundException.class)
	public List<ViaggioRoute> findByViaggioId(int viaggioId) throws ViaggioNotFoundException {
		try {
			return viaggioRouteRepository.findByViaggioId(viaggioId);

		} catch (Exception e) {
			throw new ViaggioNotFoundException();
		}
		
	}
	
	
	@Override
	@Transactional(rollbackOn = RouteNotFoundException.class)
	public List<ViaggioRoute> findByRouteId(int routeId) throws RouteNotFoundException {
		try {
			return viaggioRouteRepository.findByRouteId(routeId);

		} catch (Exception e) {
			throw new RouteNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = ViaggioRouteNotFoundException.class)
	public ViaggioRoute findByViaggioIdAndRouteId(int viaggioId, int routeId) throws ViaggioRouteNotFoundException {
		try {
			return viaggioRouteRepository.findByViaggioIdAndRouteId(viaggioId,routeId);

		} catch (Exception e) {
			throw new ViaggioRouteNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = ViaggioRouteNotFoundException.class)

	public int updateCapacityByViaggioRouteId(double capacity, int viaggioRouteId) {
		return viaggioRouteRepository.updateCapacityByViaggioRouteId(capacity, viaggioRouteId);
	}

}
