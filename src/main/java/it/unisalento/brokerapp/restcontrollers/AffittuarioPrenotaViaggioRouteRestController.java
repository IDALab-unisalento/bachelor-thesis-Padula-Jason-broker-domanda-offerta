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

import it.unisalento.brokerapp.DTOclasses.AffittuarioPrenotaViaggioRouteDTO;
import it.unisalento.brokerapp.domainClasses.AffittuarioPrenotaViaggioRoute;
import it.unisalento.brokerapp.exceptions.ViaggioRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingUserPrenotaViaggioRouteException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.exceptions.UserPrenotaViaggioRouteNotFoundException;
import it.unisalento.brokerapp.iservices.IViaggioRouteService;
import it.unisalento.brokerapp.iservices.IAffittuarioPrenotaViaggioRouteService;
import it.unisalento.brokerapp.iservices.IAffittuarioService;

@RestController
@RequestMapping("/affittuarioPrenotaViaggioRoute")
public class AffittuarioPrenotaViaggioRouteRestController {


	@Autowired
	IAffittuarioPrenotaViaggioRouteService prenotazioneService;
	
	@Autowired
	IAffittuarioService userService;
	
	@Autowired
	IViaggioRouteService viaggioRouteService;
	
	
	private ModelMapper modelMapper = new ModelMapper();

//	VIENE RAPPRESENTATA LA RELAZIONE DI APPARTENENZA CHE C'E TRA UNA STRUTTURA FISSA E I SUOI ATTRIBUTI
//	NEL PROGETTO CI SARANNO 3 STRUTTURE E AD OGNUNA SARANNO COLLEGATI CIRCA 5-10 ATTRIBUTI
//	per cui questa TABELLA SARA' NELL'ORDINE DEI 30 INGRESSO
	
//	CERCO UNA RELAZIONE -> ATTRIBUTO  APPARTIENE A UNA STRUTTURA dall'ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AffittuarioPrenotaViaggioRouteDTO get(@PathVariable int id) throws UserPrenotaViaggioRouteNotFoundException
	{
		
		AffittuarioPrenotaViaggioRoute affittuarioPrenotaViaggioRoute = prenotazioneService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		AffittuarioPrenotaViaggioRouteDTO affittuarioPrenotaViaggioRouteDTO = modelMapper.map(affittuarioPrenotaViaggioRoute, AffittuarioPrenotaViaggioRouteDTO.class);
		affittuarioPrenotaViaggioRouteDTO.setAffittuarioId(affittuarioPrenotaViaggioRoute.getAffittuario().getId());
		affittuarioPrenotaViaggioRouteDTO.setViaggioRouteId(affittuarioPrenotaViaggioRoute.getViaggioRoute().getId());
		
		return affittuarioPrenotaViaggioRouteDTO;
	}
	
//	CERCO TUTTE LE RELAZIONI DI APPARTENENZA
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AffittuarioPrenotaViaggioRouteDTO> getAll(){
		
		List<AffittuarioPrenotaViaggioRoute> 	prenotazioniList = prenotazioneService.getAll();
		List<AffittuarioPrenotaViaggioRouteDTO> prenotazioniDTOList = new ArrayList<AffittuarioPrenotaViaggioRouteDTO>(); 
		
		for (AffittuarioPrenotaViaggioRoute prenotazione : prenotazioniList) {
			
			AffittuarioPrenotaViaggioRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, AffittuarioPrenotaViaggioRouteDTO.class);
			prenotazioneDTO.setAffittuarioId(prenotazione.getAffittuario().getId());
			prenotazioneDTO.setViaggioRouteId(prenotazione.getViaggioRoute().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}

	
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AffittuarioPrenotaViaggioRouteDTO save(@RequestBody @Valid AffittuarioPrenotaViaggioRouteDTO prenotazioneDTO) throws SavingUserPrenotaViaggioRouteException, UserNotFoundException, ViaggioRouteNotFoundException {
		
		AffittuarioPrenotaViaggioRoute prenotazione = modelMapper.map(prenotazioneDTO, AffittuarioPrenotaViaggioRoute.class);
		
		prenotazione.setAffittuario(userService.getById(prenotazioneDTO.getAffittuarioId()));
		prenotazione.setViaggioRoute(viaggioRouteService.getById(prenotazioneDTO.getViaggioRouteId()));

		AffittuarioPrenotaViaggioRoute prenotazioneSaved = prenotazioneService.save(prenotazione);	
		prenotazioneDTO.setId(prenotazioneSaved.getId());
		
		return prenotazioneDTO;
		
	}

//	RIMUOVO UNA RELAZIONE -> ATTRIBUTO NON APPARTIENE PIU' AD UNA STRUTTURA
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<AffittuarioPrenotaViaggioRoute> delete(@PathVariable int id ) throws UserPrenotaViaggioRouteNotFoundException,IllegalArgumentException{
		
		prenotazioneService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getByAffittuarioId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AffittuarioPrenotaViaggioRouteDTO> getByUserId(@PathVariable int id) throws UserPrenotaViaggioRouteNotFoundException, UserNotFoundException{
		
		List<AffittuarioPrenotaViaggioRoute> 	prenotazioniList = prenotazioneService.findByAffittuarioId(id);
		List<AffittuarioPrenotaViaggioRouteDTO> prenotazioniDTOList = new ArrayList<AffittuarioPrenotaViaggioRouteDTO>(); 
		
		for (AffittuarioPrenotaViaggioRoute prenotazione : prenotazioniList) {
			
			AffittuarioPrenotaViaggioRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, AffittuarioPrenotaViaggioRouteDTO.class);
			prenotazioneDTO.setAffittuarioId(prenotazione.getAffittuario().getId());
			prenotazioneDTO.setViaggioRouteId(prenotazione.getViaggioRoute().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}

	
//	CERCO TUTTE LE RELAZIONI ATTRIBUTO-STRUTTURA DALL'id DELLA STRUTTURA
//  Esempio: Voglio sapere tutti gli attributi possibili della struttura con id : 1
	@RequestMapping(value="/getByViaggioRouteId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AffittuarioPrenotaViaggioRouteDTO> getByVectorRouteId(@PathVariable int id) throws UserPrenotaViaggioRouteNotFoundException, ViaggioRouteNotFoundException{
		
		List<AffittuarioPrenotaViaggioRoute> 	prenotazioniList = prenotazioneService.findByViaggioRouteId(id);
		List<AffittuarioPrenotaViaggioRouteDTO> prenotazioniDTOList = new ArrayList<AffittuarioPrenotaViaggioRouteDTO>(); 
		
		for (AffittuarioPrenotaViaggioRoute prenotazione : prenotazioniList) {
			
			AffittuarioPrenotaViaggioRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, AffittuarioPrenotaViaggioRouteDTO.class);
			prenotazioneDTO.setAffittuarioId(prenotazione.getAffittuario().getId());
			prenotazioneDTO.setViaggioRouteId(prenotazione.getViaggioRoute().getId());
			
			prenotazioniDTOList.add(prenotazioneDTO);
		}
		
		
		return prenotazioniDTOList;
	}
	
	@RequestMapping(value="/getByAffittuarioIdAndViaggioRouteId/{userId}/{viaggioRouteId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AffittuarioPrenotaViaggioRouteDTO getByUserAndVector(@PathVariable int userId, @PathVariable int viaggioRouteId) throws UserPrenotaViaggioRouteNotFoundException{
		
		AffittuarioPrenotaViaggioRoute prenotazione = prenotazioneService.findByAffittuarioIdAndViaggioRouteId(userId,viaggioRouteId);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		AffittuarioPrenotaViaggioRouteDTO prenotazioneDTO = modelMapper.map(prenotazione, AffittuarioPrenotaViaggioRouteDTO.class);
		
		return prenotazioneDTO; 
	}

}
