package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.DTOclasses.AnomalyDTO;
import it.unisalento.brokerapp.domainClasses.AnomalyLog;
import it.unisalento.brokerapp.exceptions.AnomalyLogNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingAnomalyLogException;

public interface IAnomalyLogService {


	public List<AnomalyLog> getAll();

	public List<AnomalyLog> findByVectorId(int vectorId) throws AnomalyLogNotFoundException;

	public List<AnomalyLog> findByDate(String date) throws AnomalyLogNotFoundException;

	public int logAnomaly(Boolean val, int vectorId, String type) throws SavingAnomalyLogException;

	public List<AnomalyLog> findByAction(String action) throws AnomalyLogNotFoundException;

	public List<AnomalyLog> findByType(String type) throws AnomalyLogNotFoundException;

	public int logFix(String type, int vectorId) throws SavingAnomalyLogException;

	public int logReport(AnomalyDTO anomalyDTO) throws SavingAnomalyLogException;

	public int logDelete(int vec) throws SavingAnomalyLogException;

}
