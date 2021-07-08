package it.unisalento.brokerapp.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.brokerapp.DTOclasses.CompanyDTO;
import it.unisalento.brokerapp.domainClasses.Company;
import it.unisalento.brokerapp.exceptions.CompanyNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingCompanyException;
import it.unisalento.brokerapp.iservices.ICompanyService;


@RestController
@RequestMapping("/company")
public class CompanyRestController {

	
	@Autowired
	ICompanyService companyService;

	private ModelMapper modelMapper = new ModelMapper();

//	RICERCO UN ALLEGATO PER ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CompanyDTO get(@PathVariable int id) throws CompanyNotFoundException{
		
		Company company = companyService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		CompanyDTO companyDTO = modelMapper.map(company, CompanyDTO.class);

		
		return companyDTO;
	}
	
	
//	RICERCO TUTTI GLI ALLEGATI
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CompanyDTO> getAll(){
		
		List<Company> 	companyList = companyService.getAll();
		List<CompanyDTO> companyDTOList = new ArrayList<CompanyDTO>(); 
		
		for (Company company : companyList) {
			
			CompanyDTO companyDTO = modelMapper.map(company, CompanyDTO.class);

			companyDTOList.add(companyDTO);
		}
		
		
		return companyDTOList;
	}

	
//	SALVO UN ALLEGATO
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CompanyDTO save(@RequestBody @Valid CompanyDTO companyDTO) throws SavingCompanyException {
		
		
		Company  company = new Company();
		
		company = modelMapper.map(companyDTO, Company.class);
		
		Company companySaved = companyService.save(company);	
		companyDTO.setId(companySaved.getId());
		
		return companyDTO;
		
	}
	
    

//	RIMUOVO UN ALLEGATO
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<Company> delete(@PathVariable int id ) throws CompanyNotFoundException{
		
		companyService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
//  CERCO TUTTE le companies DISABILITATI {/true} O ABILITATI {/false}
	@RequestMapping(value="/getDisabled/{disabilitated}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CompanyDTO> getDisabled(@PathVariable boolean disabilitated ) throws  CompanyNotFoundException{
		
		List<Company> 	companyList = companyService.findByDisabilitated(disabilitated);
		List<CompanyDTO> companyDTOList = new ArrayList<CompanyDTO>(); 
		
		for (Company company : companyList) {
			
			CompanyDTO companyDTO = modelMapper.map(company, CompanyDTO.class);

			companyDTOList.add(companyDTO);
		}
			
		
		return companyDTOList;
	}
	
}
