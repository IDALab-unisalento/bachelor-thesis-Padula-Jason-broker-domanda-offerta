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

import it.unisalento.brokerapp.DTOclasses.UserPrenotaOfferVectorTreatsDTO;
import it.unisalento.brokerapp.domainClasses.UserPrenotaOfferVectorTreats;
import it.unisalento.brokerapp.exceptions.OfferVectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaOfferVectorTreatsException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaOfferVectorTreatsNotFoundException;
import it.unisalento.brokerapp.iservices.IOfferVectorTreatsService;
import it.unisalento.brokerapp.iservices.IUserPrenotaOfferVectorTreatsService;
import it.unisalento.brokerapp.iservices.IUserService;

@RestController
@RequestMapping("/userPrenotaOfferTreats")
public class UserPrenotaOfferVectorTreatsRestController {


	@Autowired
	IUserPrenotaOfferVectorTreatsService prenotazioneService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IOfferVectorTreatsService offerVectorTreatsService;
	
	
	private ModelMapper modelMapper = new ModelMapper();

//	VIENE RAPPRESENTATA LA RELAZIONE DI APPARTENENZA CHE C'E TRA UNA STRUTTURA FISSA E I SUOI ATTRIBUTI
//	NEL PROGETTO CI SARANNO 3 STRUTTURE E AD OGNUNA SARANNO COLLEGATI CIRCA 5-10 ATTRIBUTI
//	per cui questa TABELLA SARA' NELL'ORDINE DEI 30 INGRESSO
	
//	CERCO UNA RELAZIONE -> ATTRIBUTO  APPARTIENE A UNA STRUTTURA dall'ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserPrenotaOfferVectorTreatsDTO get(@PathVariable int id) throws UserPrenotaOfferVectorTreatsNotFoundException
	{
		
		UserPrenotaOfferVectorTreats userPrenotaOfferVectorTreats = prenotazioneService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		UserPrenotaOfferVectorTreatsDTO userPrenotaOfferVectorTreatsDTO = modelMapper.map(userPrenotaOfferVectorTreats, UserPrenotaOfferVectorTreatsDTO.class);
		userPrenotaOfferVectorTreatsDTO.setUserId(userPrenotaOfferVectorTreats.getUser().getId());
		userPrenotaOfferVectorTreatsDTO.setOfferVectorTreatsId(userPrenotaOfferVectorTreats.getOfferVectorTreats().getId());
		
		return userPrenotaOfferVectorTreatsDTO;
	}
	
//	CERCO TUTTE LE RELAZIONI DI APPARTENENZA
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserPrenotaOfferVectorTreatsDTO> getAll(){
		
		List<UserPrenotaOfferVectorTreats> 	prenotazioniList = prenotazioneService.getAll();
		List<UserPrenotaOfferVectorTreatsDTO> prenotazioniDTOList = new ArrayList<UserPrenotaOfferVectorTreatsDTO>(); 
		
		for (UserPrenotaOfferVectorTreats prenotazione : prenotazioniList) {
			
			UserPrenotaOfferVectorTreatsDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaOfferVectorTreatsDTO.class);
			prenotazioneDTO.setUserId(prenotazione.getUser().getId());
			prenotazioneDTO.setOfferVectorTreatsId(prenotazione.getOfferVectorTreats().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}

	
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserPrenotaOfferVectorTreatsDTO save(@RequestBody @Valid UserPrenotaOfferVectorTreatsDTO prenotazioneDTO) throws SavingUserPrenotaOfferVectorTreatsException, UserNotFoundException, OfferVectorTreatsNotFoundException {
		
		UserPrenotaOfferVectorTreats prenotazione = modelMapper.map(prenotazioneDTO, UserPrenotaOfferVectorTreats.class);
		
		prenotazione.setUser(userService.getById(prenotazioneDTO.getUserId()));
		prenotazione.setOfferVectorTreats(offerVectorTreatsService.getById(prenotazioneDTO.getOfferVectorTreatsId()));

		UserPrenotaOfferVectorTreats prenotazioneSaved = prenotazioneService.save(prenotazione);	
		prenotazioneDTO.setId(prenotazioneSaved.getId());
		
		return prenotazioneDTO;
		
	}

//	RIMUOVO UNA RELAZIONE -> ATTRIBUTO NON APPARTIENE PIU' AD UNA STRUTTURA
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<UserPrenotaOfferVectorTreats> delete(@PathVariable int id ) throws UserPrenotaOfferVectorTreatsNotFoundException,IllegalArgumentException{
		
		prenotazioneService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getByUserId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserPrenotaOfferVectorTreatsDTO> getByUserId(@PathVariable int id) throws UserPrenotaOfferVectorTreatsNotFoundException, UserNotFoundException{
		
		List<UserPrenotaOfferVectorTreats> 	prenotazioniList = prenotazioneService.findByUserId(id);
		List<UserPrenotaOfferVectorTreatsDTO> prenotazioniDTOList = new ArrayList<UserPrenotaOfferVectorTreatsDTO>(); 
		
		for (UserPrenotaOfferVectorTreats prenotazione : prenotazioniList) {
			
			UserPrenotaOfferVectorTreatsDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaOfferVectorTreatsDTO.class);
			prenotazioneDTO.setUserId(prenotazione.getUser().getId());
			prenotazioneDTO.setOfferVectorTreatsId(prenotazione.getOfferVectorTreats().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}

	
//	CERCO TUTTE LE RELAZIONI ATTRIBUTO-STRUTTURA DALL'id DELLA STRUTTURA
//  Esempio: Voglio sapere tutti gli attributi possibili della struttura con id : 1
	@RequestMapping(value="/getByOfferVectorTreatsId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserPrenotaOfferVectorTreatsDTO> getByOfferVectorTreatsId(@PathVariable int id) throws UserPrenotaOfferVectorTreatsNotFoundException, OfferVectorTreatsNotFoundException{
		
		List<UserPrenotaOfferVectorTreats> 	prenotazioniList = prenotazioneService.findByOfferVectorTreatsId(id);
		List<UserPrenotaOfferVectorTreatsDTO> prenotazioniDTOList = new ArrayList<UserPrenotaOfferVectorTreatsDTO>(); 
		
		for (UserPrenotaOfferVectorTreats prenotazione : prenotazioniList) {
			
			UserPrenotaOfferVectorTreatsDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaOfferVectorTreatsDTO.class);
			prenotazioneDTO.setUserId(prenotazione.getUser().getId());
			prenotazioneDTO.setOfferVectorTreatsId(prenotazione.getOfferVectorTreats().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}
	
	@RequestMapping(value="/getByUserIdAndOfferVectorTreatsId/{userId}/{offerVectorTreatsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserPrenotaOfferVectorTreatsDTO getByUserAndOfferVector(@PathVariable int userId, @PathVariable int offerVectorTreatsId) throws UserPrenotaOfferVectorTreatsNotFoundException{
		
		UserPrenotaOfferVectorTreats prenotazione = prenotazioneService.findByUserIdAndOfferVectorTreatsId(userId,offerVectorTreatsId);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		UserPrenotaOfferVectorTreatsDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaOfferVectorTreatsDTO.class);
		
		return prenotazioneDTO; 
	}

}
