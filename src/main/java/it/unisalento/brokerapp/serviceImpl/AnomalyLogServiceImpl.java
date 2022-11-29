package it.unisalento.brokerapp.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.DTOclasses.AnomalyDTO;
import it.unisalento.brokerapp.domainClasses.AnomalyLog;
import it.unisalento.brokerapp.exceptions.AnomalyLogNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingAnomalyLogException;
import it.unisalento.brokerapp.iservices.IAnomalyLogService;
import it.unisalento.brokerapp.repositories.AnomalyLogRepository;

@Service
public class AnomalyLogServiceImpl implements IAnomalyLogService{
	
	@Autowired 
	AnomalyLogRepository anomalyLogRepository;
	
	@Override
	@Transactional
	public List<AnomalyLog> getAll() {
		return anomalyLogRepository.findAll();
	}
	
	@Override
	@Transactional
	public List <AnomalyLog> findByVectorId(int vectorId) throws AnomalyLogNotFoundException{
		try {
			return anomalyLogRepository.findByVector(vectorId);
		} catch (Exception e) {
			throw new AnomalyLogNotFoundException();
		}
	}

	@Override
	@Transactional
	public List<AnomalyLog> findByDate(String date) throws AnomalyLogNotFoundException{
		try {
			return anomalyLogRepository.findByDate(date);
		} catch (Exception e) {
			throw new AnomalyLogNotFoundException();
		}
	}

	@Override
	@Transactional
	public int logAnomaly(Boolean val, int vectorId, String type) throws SavingAnomalyLogException {
		 LocalDateTime now = LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ITALY);
		 String date = now.format(formatter);
		 String action;
		 if (val == false) {
			 action = "TurnOFF";
		 }else {
			 action = "TurnON";
		 }
		
		return anomalyLogRepository.logAnomaly(action, date, type, vectorId);
	}

	@Override
	@Transactional
	public List<AnomalyLog> findByAction(String action) throws AnomalyLogNotFoundException {
		try {
			return anomalyLogRepository.findByAction(action);
		} catch (Exception e) {
			throw new AnomalyLogNotFoundException();
		}
	}

	@Override
	@Transactional
	public List<AnomalyLog> findByType(String type) throws AnomalyLogNotFoundException {
		try {
			return anomalyLogRepository.findByType(type);
		} catch (Exception e) {
			throw new AnomalyLogNotFoundException();
		}
	}

	@Override
	@Transactional
	public int logFix(String type, int vectorId) throws SavingAnomalyLogException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ITALY);
		LocalDateTime now = LocalDateTime.now();
		String date = now.format(formatter);
		String action = "Fixed";
		return anomalyLogRepository.logFix(action, date, type, vectorId);
	}

	@Override
	@Transactional
	public int logReport(@Valid AnomalyDTO anomalyDTO) throws SavingAnomalyLogException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ITALY);
		LocalDateTime now = LocalDateTime.now();
		String date = now.format(formatter);
		String action = "Registered";
		String type = "-";
		int vectorId=anomalyDTO.getVector_id();
		
		return anomalyLogRepository.logReport(action, date, type, vectorId);
	}

	@Override
	@Transactional
	public int logDelete(int vec) throws SavingAnomalyLogException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ITALY);
		LocalDateTime now = LocalDateTime.now();
		String date = now.format(formatter);
		String action = "Removed";
		String type = "-";
		
		return anomalyLogRepository.logDelete(action, date, type, vec);
	}

}

