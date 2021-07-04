package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.VectorTreats;
import it.unisalento.brokerapp.exceptions.VectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingVectorTreatsException;
import it.unisalento.brokerapp.exceptions.TreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;


public interface IVectorTreatsService {

	
	public VectorTreats save(VectorTreats vectorTreats) throws SavingVectorTreatsException;
	
	public VectorTreats getById(int id) throws VectorTreatsNotFoundException;
		
	public List<VectorTreats> getAll();

	public boolean delete (int id) throws VectorTreatsNotFoundException,IllegalArgumentException;

	public List<VectorTreats> findByVectorId(int vectorId) throws VectorNotFoundException;

	public List<VectorTreats> findByTreatsId(int treatsId) throws TreatsNotFoundException;

	public VectorTreats findByVectorIdAndTreatsId(int vectorId, int treatsId) throws VectorTreatsNotFoundException; 

}
