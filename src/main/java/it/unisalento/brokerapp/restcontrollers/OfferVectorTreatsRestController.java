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

import it.unisalento.brokerapp.DTOclasses.VectorTreatsDTO;
import it.unisalento.brokerapp.domainClasses.OfferVectorTreats;
import it.unisalento.brokerapp.exceptions.OfferVectorNotFoundException;
import it.unisalento.brokerapp.exceptions.OfferVectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingOfferVectorTreatsException;
import it.unisalento.brokerapp.exceptions.TreatsNotFoundException;
import it.unisalento.brokerapp.iservices.IOfferVectorService;
import it.unisalento.brokerapp.iservices.IOfferVectorTreatsService;
import it.unisalento.brokerapp.iservices.ITreatsService;

@RestController
@RequestMapping("/offerVectorTreats")
public class OfferVectorTreatsRestController {

	
	@Autowired
	IOfferVectorTreatsService offerVectorTreatsService;
	
	@Autowired
	IOfferVectorService offerVectorService;
	
	@Autowired
	ITreatsService treatsService;
	
	
	private ModelMapper modelMapper = new ModelMapper();

//	VIENE RAPPRESENTATA LA RELAZIONE DI APPARTENENZA CHE C'E TRA UNA STRUTTURA FISSA E I SUOI ATTRIBUTI
//	NEL PROGETTO CI SARANNO 3 STRUTTURE E AD OGNUNA SARANNO COLLEGATI CIRCA 5-10 ATTRIBUTI
//	per cui questa TABELLA SARA' NELL'ORDINE DEI 30 INGRESSO
	
//	CERCO UNA RELAZIONE -> ATTRIBUTO  APPARTIENE A UNA STRUTTURA dall'ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public VectorTreatsDTO get(@PathVariable int id) throws OfferVectorTreatsNotFoundException{
		
		OfferVectorTreats offerVectorTreats = offerVectorTreatsService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		VectorTreatsDTO vectorTreatsDTO = modelMapper.map(offerVectorTreats, VectorTreatsDTO.class);
		vectorTreatsDTO.setOfferVectorId(offerVectorTreats.getOfferVector().getId());
		vectorTreatsDTO.setTreatsId(offerVectorTreats.getTreats().getId());
		
		return vectorTreatsDTO;
	}
	
//	CERCO TUTTE LE RELAZIONI DI APPARTENENZA
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VectorTreatsDTO> getAll(){
		
		List<OfferVectorTreats> 	offerTreatsList = offerVectorTreatsService.getAll();
		List<VectorTreatsDTO> offerTreatsDTOList = new ArrayList<VectorTreatsDTO>(); 
		
		for (OfferVectorTreats offerVectorTreats : offerTreatsList) {
			
			VectorTreatsDTO vectorTreatsDTO = modelMapper.map(offerVectorTreats, VectorTreatsDTO.class);
			vectorTreatsDTO.setOfferVectorId(offerVectorTreats.getOfferVector().getId());
			vectorTreatsDTO.setTreatsId(offerVectorTreats.getTreats().getId());
			
			offerTreatsDTOList.add(vectorTreatsDTO);
		}
		
		
		return offerTreatsDTOList;
	}

	
// AGGIUNGO UNA RELAZIONE -> ATTRIBUTE APPARTIENE AD UNA STRUTTURA	
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public VectorTreatsDTO save(@RequestBody @Valid VectorTreatsDTO vectorTreatsDTO) throws SavingOfferVectorTreatsException, TreatsNotFoundException, OfferVectorNotFoundException {
		
		OfferVectorTreats offerVectorTreats = modelMapper.map(vectorTreatsDTO, OfferVectorTreats.class);
		
		offerVectorTreats.setOfferVector(offerVectorService.getById(vectorTreatsDTO.getOfferVectorId()));
		offerVectorTreats.setTreats(treatsService.getById(vectorTreatsDTO.getTreatsId()));

		OfferVectorTreats offerTreatsSaved = offerVectorTreatsService.save(offerVectorTreats);	
		vectorTreatsDTO.setId(offerTreatsSaved.getId());
		
		return vectorTreatsDTO;
		
	}

//	RIMUOVO UNA RELAZIONE -> ATTRIBUTO NON APPARTIENE PIU' AD UNA STRUTTURA
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<OfferVectorTreats> delete(@PathVariable int id ) throws OfferVectorTreatsNotFoundException,IllegalArgumentException{
		
		offerVectorTreatsService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getByOfferVectorId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VectorTreatsDTO> getByOfferVectorId(@PathVariable int id) throws OfferVectorTreatsNotFoundException, OfferVectorNotFoundException{
		
		List<OfferVectorTreats> offerTreatsList = offerVectorTreatsService.findByOfferVectorId(id);
		List<VectorTreatsDTO> offerTreatsDTOList = new ArrayList<VectorTreatsDTO>(); 
		
		for (OfferVectorTreats offerVectorTreats : offerTreatsList) {
			VectorTreatsDTO vectorTreatsDTO = modelMapper.map(offerVectorTreats, VectorTreatsDTO.class);
			
			vectorTreatsDTO.setOfferVectorId(offerVectorTreats.getOfferVector().getId());
			vectorTreatsDTO.setTreatsId(offerVectorTreats.getTreats().getId());
			
			offerTreatsDTOList.add(vectorTreatsDTO);
		}
		
		
		return offerTreatsDTOList;
	}

	
//	CERCO TUTTE LE RELAZIONI ATTRIBUTO-STRUTTURA DALL'id DELLA STRUTTURA
//  Esempio: Voglio sapere tutti gli attributi possibili della struttura con id : 1
	@RequestMapping(value="/getByTreatsId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VectorTreatsDTO> getByTreatsId(@PathVariable int id) throws OfferVectorTreatsNotFoundException, TreatsNotFoundException{
		
		List<OfferVectorTreats> offerTreatsList = offerVectorTreatsService.findByTreatsId(id);
		List<VectorTreatsDTO> offerTreatsDTOList = new ArrayList<VectorTreatsDTO>(); 
		
		for (OfferVectorTreats offerVectorTreats : offerTreatsList) {
			VectorTreatsDTO vectorTreatsDTO = modelMapper.map(offerVectorTreats, VectorTreatsDTO.class);
			
			vectorTreatsDTO.setOfferVectorId(offerVectorTreats.getOfferVector().getId());
			vectorTreatsDTO.setTreatsId(offerVectorTreats.getTreats().getId());
			
			offerTreatsDTOList.add(vectorTreatsDTO);
		}
		
		
		return offerTreatsDTOList;
	}
	
	@RequestMapping(value="/getByOfferVectorIdAndTreatsId/{offerVectorId}/{treatsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public VectorTreatsDTO getByOfferVectorAndTreats(@PathVariable int offerVectorId, @PathVariable int treatsId) throws OfferVectorTreatsNotFoundException{
		
		OfferVectorTreats offerVectorTreats = offerVectorTreatsService.findByOfferVectorIdAndTreatsId(offerVectorId,treatsId);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		VectorTreatsDTO vectorTreatsDTO = modelMapper.map(offerVectorTreats, VectorTreatsDTO.class);
		
		
		return vectorTreatsDTO;
	}
	
}
