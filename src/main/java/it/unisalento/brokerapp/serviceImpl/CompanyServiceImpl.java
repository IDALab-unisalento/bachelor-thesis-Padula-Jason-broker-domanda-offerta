package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.Company;
import it.unisalento.brokerapp.exceptions.CompanyNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingCompanyException;
import it.unisalento.brokerapp.iservices.ICompanyService;
import it.unisalento.brokerapp.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements ICompanyService{

	
	@Autowired
	CompanyRepository companyRepository;
	
	
	@Override
	@Transactional(rollbackOn = SavingCompanyException.class)
	public Company save(Company company) throws SavingCompanyException{
		
		try {
			return companyRepository.save(company);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingCompanyException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = CompanyNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public Company getById(int id) throws CompanyNotFoundException {
		
														//function arrow
		return companyRepository.findById(id).orElseThrow(()->new CompanyNotFoundException());
	}
	
	@Override
	@Transactional
	public List<Company> getAll() {
		
		return companyRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = CompanyNotFoundException.class)
	public boolean delete (int id) throws CompanyNotFoundException, IllegalArgumentException{
		
		Company company = companyRepository.findById(id).orElseThrow(()-> new CompanyNotFoundException());
		companyRepository.delete(company);
		return true;
	}



	@Override
	@Transactional(rollbackOn = CompanyNotFoundException.class)

	public Company findByIva(String iva) throws CompanyNotFoundException {
		try {
			return companyRepository.findByIva(iva);
		} catch (Exception e) {
			throw new CompanyNotFoundException();
		}
	}

	
}
