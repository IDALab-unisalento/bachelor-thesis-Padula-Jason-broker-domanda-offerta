package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.Anomaly;
import it.unisalento.brokerapp.exceptions.AnomalyNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingAnomalyException;
import it.unisalento.brokerapp.iservices.IAnomalyService;
import it.unisalento.brokerapp.repositories.AnomalyRepository;
import it.unisalento.brokerapp.repositories.VectorRepository;

@Service
public class AnomalyServiceImpl implements IAnomalyService {
	
	@Autowired
	AnomalyRepository anomalyRepository;
	
	@Autowired 
	VectorRepository vectorRepository;
	
	@Override
	@Transactional (rollbackOn = SavingAnomalyException.class)
	public Anomaly report (Anomaly anomaly) throws SavingAnomalyException{
		
		if (anomalyRepository.findByVectorId(anomaly.getVector().getId()) == null) {
			try {
				return anomalyRepository.save(anomaly);
			} catch (Exception e) {
				throw new SavingAnomalyException();
			}
		} else {
			anomalyRepository.delete(anomalyRepository.findByVectorId(anomaly.getVector().getId()));
			try {
				return anomalyRepository.save(anomaly);
			} catch (Exception e) {
				throw new SavingAnomalyException();
			}
		}
	}
	
	@Override
	@Transactional
	public List<Anomaly> getAll() {
		return anomalyRepository.findAll();
	}

	@Override
	@Transactional (rollbackOn = AnomalyNotFoundException.class)
	public boolean delete(int id) throws AnomalyNotFoundException, IllegalArgumentException {
	
		try {
			Anomaly anomaly = anomalyRepository.findById(id);
			anomalyRepository.delete(anomaly);
		} catch (Exception e) {
			throw new AnomalyNotFoundException();
		}
		return true;
	}

	@Override
	@Transactional
	public Anomaly findByVectorId(int vectorId) throws AnomalyNotFoundException{
		try {
			return anomalyRepository.findByVectorId(vectorId);
		} catch (Exception e) {
			throw new AnomalyNotFoundException();
		}
	}
	
	@Override
	public List<Anomaly> getByGpsErr() {
		return anomalyRepository.findByGpsErr();
	}
	
	@Override
	public List<Anomaly> getByFridgeErr() {
		return anomalyRepository.findByFridgeErr();
	}
	
	@Override
	public List<Anomaly> getByTempErr() {
		return anomalyRepository.findByTempErr();
	}
	
	@Override
	public List<Anomaly> getByTailErr() {
		return anomalyRepository.findByTailErr();
	}
	
	@Override
	public List<Anomaly> getByGen1Err() {
		return anomalyRepository.findByGen1Err();
	}
	
	@Override
	public List<Anomaly> getByGen2Err() {
		return anomalyRepository.findByGen2Err();
	}
	
	@Override
	public List<Anomaly> getByGen3Err() {
		return anomalyRepository.findByGen3Err();
	}
	
	@Override
	@Transactional (rollbackOn = SavingAnomalyException.class)
	public int manGps(Boolean val, int vectorId) throws SavingAnomalyException{
		try {
			return anomalyRepository.manGps(val, vectorId);
		} catch (Exception e) {
			throw new SavingAnomalyException();
		}		
	}
	
	@Override
	@Transactional (rollbackOn = SavingAnomalyException.class)
	public int manFridge(Boolean val, int vectorId) throws SavingAnomalyException{
		try {
			return anomalyRepository.manFridge(val, vectorId);
		} catch (Exception e) {
			throw new SavingAnomalyException();
		}		
	}
	
	@Override
	@Transactional (rollbackOn = SavingAnomalyException.class)
	public int manTemp(Boolean val, int vectorId) throws SavingAnomalyException{
		try {
			return anomalyRepository.manTemp(val, vectorId);
		} catch (Exception e) {
			throw new SavingAnomalyException();
		}		
	}
	
	@Override
	@Transactional (rollbackOn = SavingAnomalyException.class)
	public int manTail(Boolean val, int vectorId) throws SavingAnomalyException{
		try {
			return anomalyRepository.manTail(val, vectorId);
		} catch (Exception e) {
			throw new SavingAnomalyException();
		}		
	}
	
	@Override
	@Transactional (rollbackOn = SavingAnomalyException.class)
	public int manGen1(Boolean val, int vectorId) throws SavingAnomalyException{
		try {
			return anomalyRepository.manGen1(val, vectorId);
		} catch (Exception e) {
			throw new SavingAnomalyException();
		}		
	}
	
	@Override
	@Transactional (rollbackOn = SavingAnomalyException.class)
	public int manGen2(Boolean val, int vectorId) throws SavingAnomalyException{
		try {
			return anomalyRepository.manGen2(val, vectorId);
		} catch (Exception e) {
			throw new SavingAnomalyException();
		}		
	}
	
	@Override
	@Transactional (rollbackOn = SavingAnomalyException.class)
	public int manGen3(Boolean val, int vectorId) throws SavingAnomalyException{
		try {
			return anomalyRepository.manGen3(val, vectorId);
		} catch (Exception e) {
			throw new SavingAnomalyException();
		}		
	}
	
	@Override
	@Transactional
	public int fixVector(int vectorId) throws SavingAnomalyException{
		try {
			return anomalyRepository.fixVector(vectorId);
		} catch (Exception e) {
			throw new SavingAnomalyException();
		}
	}

	@Override
	@Transactional
	public Anomaly findById(int id) throws AnomalyNotFoundException {
		try {
			return anomalyRepository.findById(id);
		} catch (Exception e) {
			throw new AnomalyNotFoundException();
		}
	}


	

	

	
}
