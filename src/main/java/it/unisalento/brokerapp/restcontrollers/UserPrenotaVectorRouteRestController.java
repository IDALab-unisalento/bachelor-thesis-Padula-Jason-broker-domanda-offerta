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

import it.unisalento.brokerapp.DTOclasses.UserPrenotaVectorRouteDTO;
import it.unisalento.brokerapp.domainClasses.UserPrenotaVectorRoute;
import it.unisalento.brokerapp.exceptions.VectorRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaVectorRouteException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaVectorRouteNotFoundException;
import it.unisalento.brokerapp.iservices.IVectorRouteService;
import it.unisalento.brokerapp.iservices.IUserPrenotaVectorRouteService;
import it.unisalento.brokerapp.iservices.IUserService;

@RestController
@RequestMapping("/userPrenotaVectorRoute")
public class UserPrenotaVectorRouteRestController {


	@Autowired
	IUserPrenotaVectorRouteService prenotazioneService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IVectorRouteService vectorRouteService;
	
	
	private ModelMapper modelMapper = new ModelMapper();

//	VIENE RAPPRESENTATA LA RELAZIONE DI APPARTENENZA CHE C'E TRA UNA STRUTTURA FISSA E I SUOI ATTRIBUTI
//	NEL PROGETTO CI SARANNO 3 STRUTTURE E AD OGNUNA SARANNO COLLEGATI CIRCA 5-10 ATTRIBUTI
//	per cui questa TABELLA SARA' NELL'ORDINE DEI 30 INGRESSO
	
//	CERCO UNA RELAZIONE -> ATTRIBUTO  APPARTIENE A UNA STRUTTURA dall'ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserPrenotaVectorRouteDTO get(@PathVariable int id) throws UserPrenotaVectorRouteNotFoundException
	{
		
		UserPrenotaVectorRoute userPrenotaVectorRoute = prenotazioneService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		UserPrenotaVectorRouteDTO userPrenotaVectorRouteDTO = modelMapper.map(userPrenotaVectorRoute, UserPrenotaVectorRouteDTO.class);
		userPrenotaVectorRouteDTO.setUserId(userPrenotaVectorRoute.getUser().getId());
		userPrenotaVectorRouteDTO.setVectorRouteId(userPrenotaVectorRoute.getVectorRoute().getId());
		
		return userPrenotaVectorRouteDTO;
	}
	
//	CERCO TUTTE LE RELAZIONI DI APPARTENENZA
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserPrenotaVectorRouteDTO> getAll(){
		
		List<UserPrenotaVectorRoute> 	prenotazioniList = prenotazioneService.getAll();
		List<UserPrenotaVectorRouteDTO> prenotazioniDTOList = new ArrayList<UserPrenotaVectorRouteDTO>(); 
		
		for (UserPrenotaVectorRoute prenotazione : prenotazioniList) {
			
			UserPrenotaVectorRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaVectorRouteDTO.class);
			prenotazioneDTO.setUserId(prenotazione.getUser().getId());
			prenotazioneDTO.setVectorRouteId(prenotazione.getVectorRoute().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}

	
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserPrenotaVectorRouteDTO save(@RequestBody @Valid UserPrenotaVectorRouteDTO prenotazioneDTO) throws SavingUserPrenotaVectorRouteException, UserNotFoundException, VectorRouteNotFoundException {
		
		UserPrenotaVectorRoute prenotazione = modelMapper.map(prenotazioneDTO, UserPrenotaVectorRoute.class);
		
		prenotazione.setUser(userService.getById(prenotazioneDTO.getUserId()));
		prenotazione.setVectorRoute(vectorRouteService.getById(prenotazioneDTO.getVectorRouteId()));

		UserPrenotaVectorRoute prenotazioneSaved = prenotazioneService.save(prenotazione);	
		prenotazioneDTO.setId(prenotazioneSaved.getId());
		
		return prenotazioneDTO;
		
	}

//	RIMUOVO UNA RELAZIONE -> ATTRIBUTO NON APPARTIENE PIU' AD UNA STRUTTURA
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<UserPrenotaVectorRoute> delete(@PathVariable int id ) throws UserPrenotaVectorRouteNotFoundException,IllegalArgumentException{
		
		prenotazioneService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getByUserId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserPrenotaVectorRouteDTO> getByUserId(@PathVariable int id) throws UserPrenotaVectorRouteNotFoundException, UserNotFoundException{
		
		List<UserPrenotaVectorRoute> 	prenotazioniList = prenotazioneService.findByUserId(id);
		List<UserPrenotaVectorRouteDTO> prenotazioniDTOList = new ArrayList<UserPrenotaVectorRouteDTO>(); 
		
		for (UserPrenotaVectorRoute prenotazione : prenotazioniList) {
			
			UserPrenotaVectorRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaVectorRouteDTO.class);
			prenotazioneDTO.setUserId(prenotazione.getUser().getId());
			prenotazioneDTO.setVectorRouteId(prenotazione.getVectorRoute().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}

	
//	CERCO TUTTE LE RELAZIONI ATTRIBUTO-STRUTTURA DALL'id DELLA STRUTTURA
//  Esempio: Voglio sapere tutti gli attributi possibili della struttura con id : 1
	@RequestMapping(value="/getByVectorRouteId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserPrenotaVectorRouteDTO> getByVectorRouteId(@PathVariable int id) throws UserPrenotaVectorRouteNotFoundException, VectorRouteNotFoundException{
		
		List<UserPrenotaVectorRoute> 	prenotazioniList = prenotazioneService.findByVectorRouteId(id);
		List<UserPrenotaVectorRouteDTO> prenotazioniDTOList = new ArrayList<UserPrenotaVectorRouteDTO>(); 
		
		for (UserPrenotaVectorRoute prenotazione : prenotazioniList) {
			
			UserPrenotaVectorRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaVectorRouteDTO.class);
			prenotazioneDTO.setUserId(prenotazione.getUser().getId());
			prenotazioneDTO.setVectorRouteId(prenotazione.getVectorRoute().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}
	
	@RequestMapping(value="/getByUserIdAndVectorRouteId/{userId}/{vectorRouteId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserPrenotaVectorRouteDTO getByUserAndVector(@PathVariable int userId, @PathVariable int vectorRouteId) throws UserPrenotaVectorRouteNotFoundException{
		
		UserPrenotaVectorRoute prenotazione = prenotazioneService.findByUserIdAndVectorRouteId(userId,vectorRouteId);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		UserPrenotaVectorRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, UserPrenotaVectorRouteDTO.class);
		
		return prenotazioneDTO; 
	}

}
