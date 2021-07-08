package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
	public Company findByIva(String iva);
	
	public List<Company> findByDisabilitated(boolean disabilitated);

}
