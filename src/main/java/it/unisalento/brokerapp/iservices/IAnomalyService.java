package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.Anomaly;
import it.unisalento.brokerapp.exceptions.AnomalyNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingAnomalyException;

public interface IAnomalyService {
	
	//-----Inserisce un record di anomalie per un mezzo-----
	
	public Anomaly report (Anomaly anomaly) throws SavingAnomalyException;

	//-----Da la lista di tutte le anomalie presenti nel DB-----
	public List<Anomaly> getAll();
	
	//-----Cancella un record di anomalie da id-----
	
	public boolean delete (int id) throws AnomalyNotFoundException;
	
	//-----Ricerca un record di anomalie da vectorId-----
	
	public Anomaly findByVectorId (int vectorId) throws AnomalyNotFoundException;
	
	//-----Ricerca tutti i record con una tipologia di anomalia attiva-----
	
	public List<Anomaly> getByGpsErr();
	
	public List<Anomaly> getByFridgeErr();
	
	public List<Anomaly> getByTempErr();
	
	public List<Anomaly> getByTailErr();
	
	public List<Anomaly> getByGen1Err();
	
	public List<Anomaly> getByGen2Err();
	
	public List<Anomaly> getByGen3Err();
	
	//-----Imposta a 0 o 1 una tipologia di anomalia (spegnimento/accensione anomalia)-----

	public int manGps(Boolean val, int vectorId) throws SavingAnomalyException;
	
	public int manFridge(Boolean val, int vectorId) throws SavingAnomalyException;
	
	public int manTemp(Boolean val, int vectorId) throws SavingAnomalyException;
	
	public int manTail(Boolean val, int vectorId) throws SavingAnomalyException;
	
	public int manGen1(Boolean val, int vectorId) throws SavingAnomalyException;
	
	public int manGen2(Boolean val, int vectorId) throws SavingAnomalyException;
	
	public int manGen3(Boolean val, int vectorId) throws SavingAnomalyException;
	
	//-----Imposta a 0 tutte le tipologie di anomalie su un mezzo-----
	
	public int fixVector (int vectorId) throws SavingAnomalyException;

	public Anomaly findById(int id) throws AnomalyNotFoundException;

	

	
}
