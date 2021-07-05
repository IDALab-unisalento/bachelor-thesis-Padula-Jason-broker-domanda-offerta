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

import it.unisalento.brokerapp.DTOclasses.CompanyVectorDTO;
import it.unisalento.brokerapp.domainClasses.CompanyVector;
import it.unisalento.brokerapp.exceptions.CompanyNotFoundException;
import it.unisalento.brokerapp.exceptions.CompanyVectorNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingCompanyVectorException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.iservices.ICompanyService;
import it.unisalento.brokerapp.iservices.ICompanyVectorService;
import it.unisalento.brokerapp.iservices.IVectorService;

@RestController
@RequestMapping("/companyVector")
public class CompanyVectorRestController {

	
	@Autowired
	ICompanyVectorService companyVectorService;
	
	@Autowired
	ICompanyService companyService;
	
	@Autowired
	IVectorService vectorService;
	
	
	private ModelMapper modelMapper = new ModelMapper();

//	VIENE RAPPRESENTATA LA RELAZIONE DI APPARTENENZA CHE C'E TRA UNA STRUTTURA FISSA E I SUOI ATTRIBUTI
//	NEL PROGETTO CI SARANNO 3 STRUTTURE E AD OGNUNA SARANNO COLLEGATI CIRCA 5-10 ATTRIBUTI
//	per cui questa TABELLA SARA' NELL'ORDINE DEI 30 INGRESSO
	
//	CERCO UNA RELAZIONE -> ATTRIBUTO  APPARTIENE A UNA STRUTTURA dall'ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CompanyVectorDTO get(@PathVariable int id) throws CompanyVectorNotFoundException
	{
		
		CompanyVector companyVector = companyVectorService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		CompanyVectorDTO companyVectorDTO = modelMapper.map(companyVector, CompanyVectorDTO.class);
		companyVectorDTO.setCompanyId(companyVector.getCompany().getId());
		companyVectorDTO.setVectorId(companyVector.getVector().getId());
		
		return companyVectorDTO;
	}
	
//	CERCO TUTTE LE RELAZIONI DI APPARTENENZA
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CompanyVectorDTO> getAll(){
		
		List<CompanyVector> 	offerVectorList = companyVectorService.getAll();
		List<CompanyVectorDTO> offerVectorDTOList = new ArrayList<CompanyVectorDTO>(); 
		
		for (CompanyVector offervector : offerVectorList) {
			
			CompanyVectorDTO offerTreatsDTO = modelMapper.map(offervector, CompanyVectorDTO.class);
			offerTreatsDTO.setCompanyId(offervector.getCompany().getId());
			offerTreatsDTO.setVectorId(offervector.getVector().getId());
			
			offerVectorDTOList.add(offerTreatsDTO);
		}
		
		
		return offerVectorDTOList;
	}

	
// AGGIUNGO UNA RELAZIONE -> ATTRIBUTE APPARTIENE AD UNA STRUTTURA	
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CompanyVectorDTO save(@RequestBody @Valid CompanyVectorDTO companyVectorDTO) throws SavingCompanyVectorException, CompanyNotFoundException, VectorNotFoundException {
		
		CompanyVector companyVector = modelMapper.map(companyVectorDTO, CompanyVector.class);
		
		companyVector.setCompany(companyService.getById(companyVectorDTO.getCompanyId()));
		companyVector.setVector(vectorService.getById(companyVectorDTO.getVectorId()));

		CompanyVector offerVectorSaved = companyVectorService.save(companyVector);	
		companyVectorDTO.setId(offerVectorSaved.getId());
		
		return companyVectorDTO;
		
	}

//	RIMUOVO UNA RELAZIONE -> ATTRIBUTO NON APPARTIENE PIU' AD UNA STRUTTURA
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<CompanyVector> delete(@PathVariable int id ) throws CompanyVectorNotFoundException,IllegalArgumentException{
		
		companyVectorService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getByCompanyId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CompanyVectorDTO> getByCompanyId(@PathVariable int id) throws CompanyVectorNotFoundException, CompanyNotFoundException{
		
		List<CompanyVector> 	offerVectorList = companyVectorService.findByCompanyId(id);
		List<CompanyVectorDTO> offerVectorDTOList = new ArrayList<CompanyVectorDTO>(); 
		
		for (CompanyVector offervector : offerVectorList) {
			
			CompanyVectorDTO offerTreatsDTO = modelMapper.map(offervector, CompanyVectorDTO.class);
			offerTreatsDTO.setCompanyId(offervector.getCompany().getId());
			offerTreatsDTO.setVectorId(offervector.getVector().getId());
			
			offerVectorDTOList.add(offerTreatsDTO);
		}
		
		
		return offerVectorDTOList;
	}

	
//	CERCO TUTTE LE RELAZIONI ATTRIBUTO-STRUTTURA DALL'id DELLA STRUTTURA
//  Esempio: Voglio sapere tutti gli attributi possibili della struttura con id : 1
	@RequestMapping(value="/getByVectorId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CompanyVectorDTO> getByVectorId(@PathVariable int id) throws CompanyVectorNotFoundException, VectorNotFoundException{
		
		List<CompanyVector> 	offerVectorList = companyVectorService.findByVectorId(id);
		List<CompanyVectorDTO> offerVectorDTOList = new ArrayList<CompanyVectorDTO>(); 
		
		for (CompanyVector offervector : offerVectorList) {
			
			CompanyVectorDTO offerTreatsDTO = modelMapper.map(offervector, CompanyVectorDTO.class);
			offerTreatsDTO.setCompanyId(offervector.getCompany().getId());
			offerTreatsDTO.setVectorId(offervector.getVector().getId());
			
			offerVectorDTOList.add(offerTreatsDTO);
		}
		
		
		return offerVectorDTOList;
	}
	
	@RequestMapping(value="/getByCompanyIdAndVectorId/{companyId}/{vectorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CompanyVectorDTO getByOfferAndTreats(@PathVariable int companyId, @PathVariable int vectorId) throws CompanyVectorNotFoundException{
		
		CompanyVector companyVector = companyVectorService.findByCompanyIdAndVectorId(companyId,vectorId);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		CompanyVectorDTO companyVectorDTO = modelMapper.map(companyVector, CompanyVectorDTO.class);
		
		return companyVectorDTO;
	}

}
