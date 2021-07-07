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

import it.unisalento.brokerapp.DTOclasses.AffittuarioPrenotaVectorRouteDTO;
import it.unisalento.brokerapp.domainClasses.AffittuarioPrenotaVectorRoute;
import it.unisalento.brokerapp.exceptions.VectorRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaVectorRouteException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaVectorRouteNotFoundException;
import it.unisalento.brokerapp.iservices.IVectorRouteService;
import it.unisalento.brokerapp.iservices.IAffittuarioPrenotaVectorRouteService;
import it.unisalento.brokerapp.iservices.IAffittuarioService;

@RestController
@RequestMapping("/affittuarioPrenotaVectorRoute")
public class AffittuarioPrenotaVectorRouteRestController {


	@Autowired
	IAffittuarioPrenotaVectorRouteService prenotazioneService;
	
	@Autowired
	IAffittuarioService userService;
	
	@Autowired
	IVectorRouteService vectorRouteService;
	
	
	private ModelMapper modelMapper = new ModelMapper();

//	VIENE RAPPRESENTATA LA RELAZIONE DI APPARTENENZA CHE C'E TRA UNA STRUTTURA FISSA E I SUOI ATTRIBUTI
//	NEL PROGETTO CI SARANNO 3 STRUTTURE E AD OGNUNA SARANNO COLLEGATI CIRCA 5-10 ATTRIBUTI
//	per cui questa TABELLA SARA' NELL'ORDINE DEI 30 INGRESSO
	
//	CERCO UNA RELAZIONE -> ATTRIBUTO  APPARTIENE A UNA STRUTTURA dall'ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AffittuarioPrenotaVectorRouteDTO get(@PathVariable int id) throws UserPrenotaVectorRouteNotFoundException
	{
		
		AffittuarioPrenotaVectorRoute affittuarioPrenotaVectorRoute = prenotazioneService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		AffittuarioPrenotaVectorRouteDTO affittuarioPrenotaVectorRouteDTO = modelMapper.map(affittuarioPrenotaVectorRoute, AffittuarioPrenotaVectorRouteDTO.class);
		affittuarioPrenotaVectorRouteDTO.setAffittuarioId(affittuarioPrenotaVectorRoute.getAffittuario().getId());
		affittuarioPrenotaVectorRouteDTO.setVectorRouteId(affittuarioPrenotaVectorRoute.getVectorRoute().getId());
		
		return affittuarioPrenotaVectorRouteDTO;
	}
	
//	CERCO TUTTE LE RELAZIONI DI APPARTENENZA
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AffittuarioPrenotaVectorRouteDTO> getAll(){
		
		List<AffittuarioPrenotaVectorRoute> 	prenotazioniList = prenotazioneService.getAll();
		List<AffittuarioPrenotaVectorRouteDTO> prenotazioniDTOList = new ArrayList<AffittuarioPrenotaVectorRouteDTO>(); 
		
		for (AffittuarioPrenotaVectorRoute prenotazione : prenotazioniList) {
			
			AffittuarioPrenotaVectorRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, AffittuarioPrenotaVectorRouteDTO.class);
			prenotazioneDTO.setAffittuarioId(prenotazione.getAffittuario().getId());
			prenotazioneDTO.setVectorRouteId(prenotazione.getVectorRoute().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}

	
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AffittuarioPrenotaVectorRouteDTO save(@RequestBody @Valid AffittuarioPrenotaVectorRouteDTO prenotazioneDTO) throws SavingUserPrenotaVectorRouteException, UserNotFoundException, VectorRouteNotFoundException {
		
		AffittuarioPrenotaVectorRoute prenotazione = modelMapper.map(prenotazioneDTO, AffittuarioPrenotaVectorRoute.class);
		
		prenotazione.setAffittuario(userService.getById(prenotazioneDTO.getAffittuarioId()));
		prenotazione.setVectorRoute(vectorRouteService.getById(prenotazioneDTO.getVectorRouteId()));

		AffittuarioPrenotaVectorRoute prenotazioneSaved = prenotazioneService.save(prenotazione);	
		prenotazioneDTO.setId(prenotazioneSaved.getId());
		
		return prenotazioneDTO;
		
	}

//	RIMUOVO UNA RELAZIONE -> ATTRIBUTO NON APPARTIENE PIU' AD UNA STRUTTURA
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<AffittuarioPrenotaVectorRoute> delete(@PathVariable int id ) throws UserPrenotaVectorRouteNotFoundException,IllegalArgumentException{
		
		prenotazioneService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getByAffittuarioId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AffittuarioPrenotaVectorRouteDTO> getByUserId(@PathVariable int id) throws UserPrenotaVectorRouteNotFoundException, UserNotFoundException{
		
		List<AffittuarioPrenotaVectorRoute> 	prenotazioniList = prenotazioneService.findByAffittuarioId(id);
		List<AffittuarioPrenotaVectorRouteDTO> prenotazioniDTOList = new ArrayList<AffittuarioPrenotaVectorRouteDTO>(); 
		
		for (AffittuarioPrenotaVectorRoute prenotazione : prenotazioniList) {
			
			AffittuarioPrenotaVectorRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, AffittuarioPrenotaVectorRouteDTO.class);
			prenotazioneDTO.setAffittuarioId(prenotazione.getAffittuario().getId());
			prenotazioneDTO.setVectorRouteId(prenotazione.getVectorRoute().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}

	
//	CERCO TUTTE LE RELAZIONI ATTRIBUTO-STRUTTURA DALL'id DELLA STRUTTURA
//  Esempio: Voglio sapere tutti gli attributi possibili della struttura con id : 1
	@RequestMapping(value="/getByVectorRouteId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AffittuarioPrenotaVectorRouteDTO> getByVectorRouteId(@PathVariable int id) throws UserPrenotaVectorRouteNotFoundException, VectorRouteNotFoundException{
		
		List<AffittuarioPrenotaVectorRoute> 	prenotazioniList = prenotazioneService.findByVectorRouteId(id);
		List<AffittuarioPrenotaVectorRouteDTO> prenotazioniDTOList = new ArrayList<AffittuarioPrenotaVectorRouteDTO>(); 
		
		for (AffittuarioPrenotaVectorRoute prenotazione : prenotazioniList) {
			
			AffittuarioPrenotaVectorRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, AffittuarioPrenotaVectorRouteDTO.class);
			prenotazioneDTO.setAffittuarioId(prenotazione.getAffittuario().getId());
			prenotazioneDTO.setVectorRouteId(prenotazione.getVectorRoute().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}
	
	@RequestMapping(value="/getByAffittuarioIdAndVectorRouteId/{userId}/{vectorRouteId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AffittuarioPrenotaVectorRouteDTO getByUserAndVector(@PathVariable int userId, @PathVariable int vectorRouteId) throws UserPrenotaVectorRouteNotFoundException{
		
		AffittuarioPrenotaVectorRoute prenotazione = prenotazioneService.findByAffittuarioIdAndVectorRouteId(userId,vectorRouteId);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		AffittuarioPrenotaVectorRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, AffittuarioPrenotaVectorRouteDTO.class);
		
		return prenotazioneDTO; 
	}

}
