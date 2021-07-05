package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.CompanyVector;
import it.unisalento.brokerapp.exceptions.CompanyNotFoundException;
import it.unisalento.brokerapp.exceptions.CompanyVectorNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingCompanyVectorException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;

public interface ICompanyVectorService {

	
	public CompanyVector save(CompanyVector companyVector) throws SavingCompanyVectorException;
	
	public CompanyVector getById(int id) throws CompanyVectorNotFoundException;
		
	public List<CompanyVector> getAll();

	public boolean delete (int id) throws CompanyVectorNotFoundException,IllegalArgumentException;

	
	public List<CompanyVector> findByCompanyId(int companyId) throws CompanyNotFoundException;

	public List<CompanyVector> findByVectorId(int vectorId) throws VectorNotFoundException;

	public CompanyVector findByCompanyIdAndVectorId(int companyId, int vectorId) throws CompanyVectorNotFoundException; 

}
