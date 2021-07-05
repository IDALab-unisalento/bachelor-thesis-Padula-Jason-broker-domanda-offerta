package it.unisalento.brokerapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisalento.brokerapp.domainClasses.CompanyVector;

@Repository
public interface CompanyVectorRepository extends JpaRepository<CompanyVector, Integer>{

	
	public List<CompanyVector> findByCompanyId(int companyId);

	public List<CompanyVector> findByVectorId(int vectorId);

	public CompanyVector findByCompanyIdAndVectorId(int companyId, int vectorId);
}
