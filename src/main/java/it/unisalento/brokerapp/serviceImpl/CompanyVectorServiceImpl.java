package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.CompanyVector;
import it.unisalento.brokerapp.exceptions.VectorRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.CompanyNotFoundException;
import it.unisalento.brokerapp.exceptions.CompanyVectorNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingCompanyVectorException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.iservices.ICompanyVectorService;
import it.unisalento.brokerapp.repositories.CompanyVectorRepository;


@Service
public class CompanyVectorServiceImpl implements ICompanyVectorService{

	
	
	@Autowired
	CompanyVectorRepository companyVectorRepository;
	
	
	@Override
	@Transactional(rollbackOn =SavingCompanyVectorException.class)
	public CompanyVector save(CompanyVector companyVector) throws SavingCompanyVectorException{
		
		try {
			return companyVectorRepository.save(companyVector);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingCompanyVectorException();
			}
		
		}
	
	@Override
	@Transactional(rollbackOn = CompanyVectorNotFoundException.class) //rollback mi permette di ritornare al punto prima della transazione quando va male
	public CompanyVector getById(int id) throws CompanyVectorNotFoundException {
		
														//function arrow
		return companyVectorRepository.findById(id).orElseThrow(()->new CompanyVectorNotFoundException());
	}
	
	@Override
	@Transactional
	public List<CompanyVector> getAll() {
		
		return companyVectorRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = CompanyVectorNotFoundException.class)
	public boolean delete (int id) throws CompanyVectorNotFoundException, IllegalArgumentException{
		
		CompanyVector companyVector = companyVectorRepository.findById(id).orElseThrow(()-> new CompanyVectorNotFoundException());
		companyVectorRepository.delete(companyVector);
		return true;
	}
	
	
	@Override
	@Transactional(rollbackOn = VectorRouteNotFoundException.class)
	public List<CompanyVector> findByCompanyId(int companyId) throws CompanyNotFoundException {
		try {
			return companyVectorRepository.findByCompanyId(companyId);

		} catch (Exception e) {
			throw new CompanyNotFoundException();
		}
		
	}
	
	
	@Override
	@Transactional(rollbackOn = VectorRouteNotFoundException.class)
	public List<CompanyVector> findByVectorId(int vectorId) throws VectorNotFoundException {
		try {
			return companyVectorRepository.findByVectorId(vectorId);

		} catch (Exception e) {
			throw new VectorNotFoundException();
		}
		
	}

	@Override
	@Transactional(rollbackOn = CompanyVectorNotFoundException.class)
	public CompanyVector findByCompanyIdAndVectorId(int companyId, int vectorId) throws CompanyVectorNotFoundException {
		try {
			return companyVectorRepository.findByCompanyIdAndVectorId(companyId,vectorId);

		} catch (Exception e) {
			throw new CompanyVectorNotFoundException();
		}
		
	}
}
