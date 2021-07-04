package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.Treats;
import it.unisalento.brokerapp.exceptions.SavingTreatsException;
import it.unisalento.brokerapp.exceptions.TreatsNotFoundException;

public interface ITreatsService {

	
	public Treats save(Treats treats) throws SavingTreatsException;
	
	public Treats getById(int id) throws TreatsNotFoundException;
		
	public List<Treats> getAll();

    boolean delete (int id) throws TreatsNotFoundException,IllegalArgumentException;

}
