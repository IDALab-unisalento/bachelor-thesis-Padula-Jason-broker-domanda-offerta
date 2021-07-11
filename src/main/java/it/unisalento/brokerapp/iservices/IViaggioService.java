package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.Viaggio;
import it.unisalento.brokerapp.exceptions.SavingViaggioException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.exceptions.ViaggioNotFoundException;

public interface IViaggioService {

	
	public Viaggio save(Viaggio viaggio) throws SavingViaggioException;
	
	public Viaggio getById(int id) throws ViaggioNotFoundException;
		
	public List<Viaggio> getAll();

    boolean delete (int id) throws ViaggioNotFoundException,IllegalArgumentException;

    public List<Viaggio> findByVectorId(int vectorId) throws VectorNotFoundException;
    
}
