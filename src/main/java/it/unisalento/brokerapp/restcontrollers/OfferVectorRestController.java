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

import it.unisalento.brokerapp.DTOclasses.OfferVectorDTO;
import it.unisalento.brokerapp.domainClasses.OfferVector;
import it.unisalento.brokerapp.exceptions.OfferNotFoundException;
import it.unisalento.brokerapp.exceptions.OfferVectorNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingOfferVectorException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.iservices.IOfferService;
import it.unisalento.brokerapp.iservices.IOfferVectorService;
import it.unisalento.brokerapp.iservices.IVectorService;

@RestController
@RequestMapping("/offerVector")
public class OfferVectorRestController {

	
	@Autowired
	IOfferVectorService offerVectorService;
	
	@Autowired
	IOfferService offerService;
	
	@Autowired
	IVectorService vectorService;
	
	
	private ModelMapper modelMapper = new ModelMapper();

//	VIENE RAPPRESENTATA LA RELAZIONE DI APPARTENENZA CHE C'E TRA UNA STRUTTURA FISSA E I SUOI ATTRIBUTI
//	NEL PROGETTO CI SARANNO 3 STRUTTURE E AD OGNUNA SARANNO COLLEGATI CIRCA 5-10 ATTRIBUTI
//	per cui questa TABELLA SARA' NELL'ORDINE DEI 30 INGRESSO
	
//	CERCO UNA RELAZIONE -> ATTRIBUTO  APPARTIENE A UNA STRUTTURA dall'ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public OfferVectorDTO get(@PathVariable int id) throws OfferVectorNotFoundException
	{
		
		OfferVector offerVector = offerVectorService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		OfferVectorDTO offerVectorDTO = modelMapper.map(offerVector, OfferVectorDTO.class);
		offerVectorDTO.setOfferId(offerVector.getOffer().getId());
		offerVectorDTO.setVectorId(offerVector.getVector().getId());
		
		return offerVectorDTO;
	}
	
//	CERCO TUTTE LE RELAZIONI DI APPARTENENZA
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OfferVectorDTO> getAll(){
		
		List<OfferVector> 	offerVectorList = offerVectorService.getAll();
		List<OfferVectorDTO> offerVectorDTOList = new ArrayList<OfferVectorDTO>(); 
		
		for (OfferVector offervector : offerVectorList) {
			
			OfferVectorDTO offerTreatsDTO = modelMapper.map(offervector, OfferVectorDTO.class);
			offerTreatsDTO.setOfferId(offervector.getOffer().getId());
			offerTreatsDTO.setVectorId(offervector.getVector().getId());
			
			offerVectorDTOList.add(offerTreatsDTO);
		}
		
		
		return offerVectorDTOList;
	}

	
// AGGIUNGO UNA RELAZIONE -> ATTRIBUTE APPARTIENE AD UNA STRUTTURA	
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public OfferVectorDTO save(@RequestBody @Valid OfferVectorDTO offerVectorDTO) throws SavingOfferVectorException, OfferNotFoundException, VectorNotFoundException {
		
		OfferVector offerVector = modelMapper.map(offerVectorDTO, OfferVector.class);
		
		offerVector.setOffer(offerService.getById(offerVectorDTO.getOfferId()));
		offerVector.setVector(vectorService.getById(offerVectorDTO.getVectorId()));

		OfferVector offerVectorSaved = offerVectorService.save(offerVector);	
		offerVectorDTO.setId(offerVectorSaved.getId());
		
		return offerVectorDTO;
		
	}

//	RIMUOVO UNA RELAZIONE -> ATTRIBUTO NON APPARTIENE PIU' AD UNA STRUTTURA
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<OfferVector> delete(@PathVariable int id ) throws OfferVectorNotFoundException,IllegalArgumentException{
		
		offerVectorService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getByOfferId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OfferVectorDTO> getByOfferId(@PathVariable int id) throws OfferVectorNotFoundException, OfferNotFoundException{
		
		List<OfferVector> 	offerVectorList = offerVectorService.findByOfferId(id);
		List<OfferVectorDTO> offerVectorDTOList = new ArrayList<OfferVectorDTO>(); 
		
		for (OfferVector offervector : offerVectorList) {
			
			OfferVectorDTO offerTreatsDTO = modelMapper.map(offervector, OfferVectorDTO.class);
			offerTreatsDTO.setOfferId(offervector.getOffer().getId());
			offerTreatsDTO.setVectorId(offervector.getVector().getId());
			
			offerVectorDTOList.add(offerTreatsDTO);
		}
		
		
		return offerVectorDTOList;
	}

	
//	CERCO TUTTE LE RELAZIONI ATTRIBUTO-STRUTTURA DALL'id DELLA STRUTTURA
//  Esempio: Voglio sapere tutti gli attributi possibili della struttura con id : 1
	@RequestMapping(value="/getByVectorId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OfferVectorDTO> getByVectorId(@PathVariable int id) throws OfferVectorNotFoundException, VectorNotFoundException{
		
		List<OfferVector> 	offerVectorList = offerVectorService.findByVectorId(id);
		List<OfferVectorDTO> offerVectorDTOList = new ArrayList<OfferVectorDTO>(); 
		
		for (OfferVector offervector : offerVectorList) {
			
			OfferVectorDTO offerTreatsDTO = modelMapper.map(offervector, OfferVectorDTO.class);
			offerTreatsDTO.setOfferId(offervector.getOffer().getId());
			offerTreatsDTO.setVectorId(offervector.getVector().getId());
			
			offerVectorDTOList.add(offerTreatsDTO);
		}
		
		
		return offerVectorDTOList;
	}
	
	@RequestMapping(value="/getByOfferIdAndVectorId/{offerId}/{vectorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public OfferVectorDTO getByOfferAndTreats(@PathVariable int offerId, @PathVariable int vectorId) throws OfferVectorNotFoundException{
		
		OfferVector offerVector = offerVectorService.findByOfferIdAndVectorId(offerId,vectorId);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		OfferVectorDTO offerVectorDTO = modelMapper.map(offerVector, OfferVectorDTO.class);
		
		return offerVectorDTO;
	}

}
