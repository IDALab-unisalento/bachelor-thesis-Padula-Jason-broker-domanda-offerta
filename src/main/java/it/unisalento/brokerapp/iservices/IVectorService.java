package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.Vector;
import it.unisalento.brokerapp.exceptions.SavingVectorException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;


public interface IVectorService {

	public Vector save(Vector attached) throws SavingVectorException;
	
	public Vector getById(int id) throws VectorNotFoundException;
		
	public List<Vector> getAll();

	public boolean delete (int id) throws VectorNotFoundException,IllegalArgumentException;

	public Vector findByLicensePlate(String licensePlate) throws VectorNotFoundException;

}
