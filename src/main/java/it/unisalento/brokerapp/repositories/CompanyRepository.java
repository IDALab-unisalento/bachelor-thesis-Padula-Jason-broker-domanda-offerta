package it.unisalento.brokerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

	public Company findByName(String companyName);
	
	public Company findByIva(String iva);
}
