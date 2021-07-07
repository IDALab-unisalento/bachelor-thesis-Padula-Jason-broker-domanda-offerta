package it.unisalento.brokerapp.iservices;

import java.util.List;

import it.unisalento.brokerapp.domainClasses.Company;
import it.unisalento.brokerapp.exceptions.CompanyNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingCompanyException;


public interface ICompanyService {

	
	public Company save(Company company) throws SavingCompanyException;
	
	public Company getById(int id) throws CompanyNotFoundException;
		
	public List<Company> getAll();

	public boolean delete (int id) throws CompanyNotFoundException,IllegalArgumentException;
		
	public Company findByIva(String iva) throws CompanyNotFoundException;

}
