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

import it.unisalento.brokerapp.DTOclasses.UserPrenotaVectorTreatsDTO;
import it.unisalento.brokerapp.domainClasses.UserPrenotaVectorTreats;
import it.unisalento.brokerapp.exceptions.VectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaVectorTreatsException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaVectorTreatsNotFoundException;
import it.unisalento.brokerapp.iservices.IVectorTreatsService;
import it.unisalento.brokerapp.iservices.IUserPrenotaVectorTreatsService;
import it.unisalento.brokerapp.iservices.IUserService;

@RestController
@RequestMapping("/userPrenotaOfferTreats")
public class UserPrenotaOfferTreatsRestController {


	@Autowired
	IUserPrenotaVectorTreatsService prenotazioneService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IVectorTreatsService vectorTreatsService;
	
	
	private ModelMapper modelMapper = new ModelMapper();

//	VIENE RAPPRESENTATA LA RELAZIONE DI APPARTENENZA CHE C'E TRA UNA STRUTTURA FISSA E I SUOI ATTRIBUTI
//	NEL PROGETTO CI SARANNO 3 STRUTTURE E AD OGNUNA SARANNO COLLEGATI CIRCA 5-10 ATTRIBUTI
//	per cui questa TABELLA SARA' NELL'ORDINE DEI 30 INGRESSO
	
//	CERCO UNA RELAZIONE -> ATTRIBUTO  APPARTIENE A UNA STRUTTURA dall'ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserPrenotaVectorTreatsDTO get(@PathVariable int id) throws UserPrenotaVectorTreatsNotFoundException
	{
		
		UserPrenotaVectorTreats userPrenotaVectorTreats = prenotazioneService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		UserPrenotaVectorTreatsDTO userPrenotaVectorTreatsDTO = modelMapper.map(userPrenotaVectorTreats, UserPrenotaVectorTreatsDTO.class);
		userPrenotaVectorTreatsDTO.setUserId(userPrenotaVectorTreats.getUser().getId());
		userPrenotaVectorTreatsDTO.setVectorTreatsId(userPrenotaVectorTreats.getVectorTreats().getId());
		
		return userPrenotaVectorTreatsDTO;
	}
	
//	CERCO TUTTE LE RELAZIONI DI APPARTENENZA
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserPrenotaVectorTreatsDTO> getAll(){
		
		List<UserPrenotaVectorTreats> 	prenotazioniList = prenotazioneService.getAll();
		List<UserPrenotaVectorTreatsDTO> prenotazioniDTOList = new ArrayList<UserPrenotaVectorTreatsDTO>(); 
		
		for (UserPrenotaVectorTreats prenotazione : prenotazioniList) {
			
			UserPrenotaVectorTreatsDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaVectorTreatsDTO.class);
			prenotazioneDTO.setUserId(prenotazione.getUser().getId());
			prenotazioneDTO.setVectorTreatsId(prenotazione.getVectorTreats().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}

	
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserPrenotaVectorTreatsDTO save(@RequestBody @Valid UserPrenotaVectorTreatsDTO prenotazioneDTO) throws SavingUserPrenotaVectorTreatsException, UserNotFoundException, VectorTreatsNotFoundException {
		
		UserPrenotaVectorTreats prenotazione = modelMapper.map(prenotazioneDTO, UserPrenotaVectorTreats.class);
		
		prenotazione.setUser(userService.getById(prenotazioneDTO.getUserId()));
		prenotazione.setVectorTreats(vectorTreatsService.getById(prenotazioneDTO.getVectorTreatsId()));

		UserPrenotaVectorTreats prenotazioneSaved = prenotazioneService.save(prenotazione);	
		prenotazioneDTO.setId(prenotazioneSaved.getId());
		
		return prenotazioneDTO;
		
	}

//	RIMUOVO UNA RELAZIONE -> ATTRIBUTO NON APPARTIENE PIU' AD UNA STRUTTURA
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<UserPrenotaVectorTreats> delete(@PathVariable int id ) throws UserPrenotaVectorTreatsNotFoundException,IllegalArgumentException{
		
		prenotazioneService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getByUserId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserPrenotaVectorTreatsDTO> getByUserId(@PathVariable int id) throws UserPrenotaVectorTreatsNotFoundException, UserNotFoundException{
		
		List<UserPrenotaVectorTreats> 	prenotazioniList = prenotazioneService.findByUserId(id);
		List<UserPrenotaVectorTreatsDTO> prenotazioniDTOList = new ArrayList<UserPrenotaVectorTreatsDTO>(); 
		
		for (UserPrenotaVectorTreats prenotazione : prenotazioniList) {
			
			UserPrenotaVectorTreatsDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaVectorTreatsDTO.class);
			prenotazioneDTO.setUserId(prenotazione.getUser().getId());
			prenotazioneDTO.setVectorTreatsId(prenotazione.getVectorTreats().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}

	
//	CERCO TUTTE LE RELAZIONI ATTRIBUTO-STRUTTURA DALL'id DELLA STRUTTURA
//  Esempio: Voglio sapere tutti gli attributi possibili della struttura con id : 1
	@RequestMapping(value="/getByOfferTreatsId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserPrenotaVectorTreatsDTO> getByOfferTreatsId(@PathVariable int id) throws UserPrenotaVectorTreatsNotFoundException, VectorTreatsNotFoundException{
		
		List<UserPrenotaVectorTreats> 	prenotazioniList = prenotazioneService.findByVectorTreatsId(id);
		List<UserPrenotaVectorTreatsDTO> prenotazioniDTOList = new ArrayList<UserPrenotaVectorTreatsDTO>(); 
		
		for (UserPrenotaVectorTreats prenotazione : prenotazioniList) {
			
			UserPrenotaVectorTreatsDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaVectorTreatsDTO.class);
			prenotazioneDTO.setUserId(prenotazione.getUser().getId());
			prenotazioneDTO.setVectorTreatsId(prenotazione.getVectorTreats().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}
	
	@RequestMapping(value="/getByUserIdAndOfferTreatsId/{userId}/{offerTreatsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserPrenotaVectorTreatsDTO getByUserAndVector(@PathVariable int userId, @PathVariable int vectorTreatsId) throws UserPrenotaVectorTreatsNotFoundException{
		
		UserPrenotaVectorTreats prenotazione = prenotazioneService.findByUserIdAndVectorTreatsId(userId,vectorTreatsId);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		UserPrenotaVectorTreatsDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaVectorTreatsDTO.class);
		
		return prenotazioneDTO; 
	}

}
