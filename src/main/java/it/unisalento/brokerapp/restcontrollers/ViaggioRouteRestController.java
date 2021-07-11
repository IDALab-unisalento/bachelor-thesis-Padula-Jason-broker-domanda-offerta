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

import it.unisalento.brokerapp.DTOclasses.ViaggioRouteDTO;
import it.unisalento.brokerapp.domainClasses.ViaggioRoute;
import it.unisalento.brokerapp.exceptions.ViaggioRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingViaggioRouteException;
import it.unisalento.brokerapp.exceptions.ViaggioNotFoundException;
import it.unisalento.brokerapp.exceptions.RouteNotFoundException;
import it.unisalento.brokerapp.iservices.IViaggioRouteService;
import it.unisalento.brokerapp.iservices.IViaggioService;
import it.unisalento.brokerapp.iservices.IRouteService;

@RestController
@RequestMapping("/viaggioRoute")
public class ViaggioRouteRestController {

	
	@Autowired
	IViaggioRouteService viaggioRouteService;
	
	@Autowired
	IViaggioService viaggioService;
	
	@Autowired
	IRouteService routeService;
	
	
	private ModelMapper modelMapper = new ModelMapper();

//	VIENE RAPPRESENTATA LA RELAZIONE DI APPARTENENZA CHE C'E TRA UNA STRUTTURA FISSA E I SUOI ATTRIBUTI
//	NEL PROGETTO CI SARANNO 3 STRUTTURE E AD OGNUNA SARANNO COLLEGATI CIRCA 5-10 ATTRIBUTI
//	per cui questa TABELLA SARA' NELL'ORDINE DEI 30 INGRESSO
	
//	CERCO UNA RELAZIONE -> ATTRIBUTO  APPARTIENE A UNA STRUTTURA dall'ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ViaggioRouteDTO get(@PathVariable int id) throws ViaggioRouteNotFoundException{
		
		ViaggioRoute viaggioRoute = viaggioRouteService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		ViaggioRouteDTO viaggioRouteDTO = modelMapper.map(viaggioRoute, ViaggioRouteDTO.class);
		viaggioRouteDTO.setViaggioId(viaggioRoute.getViaggio().getId());
		viaggioRouteDTO.setRouteId(viaggioRoute.getRoute().getId());
		
		return viaggioRouteDTO;
	}
	
//	CERCO TUTTE LE RELAZIONI DI APPARTENENZA
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ViaggioRouteDTO> getAll(){
		
		List<ViaggioRoute> 	offerTreatsList = viaggioRouteService.getAll();
		List<ViaggioRouteDTO> offerTreatsDTOList = new ArrayList<ViaggioRouteDTO>(); 
		
		for (ViaggioRoute viaggioRoute : offerTreatsList) {
			
			ViaggioRouteDTO viaggioRouteDTO = modelMapper.map(viaggioRoute, ViaggioRouteDTO.class);
			viaggioRouteDTO.setViaggioId(viaggioRoute.getViaggio().getId());
			viaggioRouteDTO.setRouteId(viaggioRoute.getRoute().getId());
			
			offerTreatsDTOList.add(viaggioRouteDTO);
		}
		
		
		return offerTreatsDTOList;
	}

	
// AGGIUNGO UNA RELAZIONE -> ATTRIBUTE APPARTIENE AD UNA STRUTTURA	
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ViaggioRouteDTO save(@RequestBody @Valid ViaggioRouteDTO viaggioRouteDTO) throws SavingViaggioRouteException, RouteNotFoundException, ViaggioNotFoundException {
		
		ViaggioRoute viaggioRoute = modelMapper.map(viaggioRouteDTO, ViaggioRoute.class);
		
		viaggioRoute.setViaggio(viaggioService.getById(viaggioRouteDTO.getViaggioId()));
		viaggioRoute.setRoute(routeService.getById(viaggioRouteDTO.getRouteId()));

		ViaggioRoute offerTreatsSaved = viaggioRouteService.save(viaggioRoute);	
		viaggioRouteDTO.setId(offerTreatsSaved.getId());
		
		return viaggioRouteDTO;
		
	}

//	RIMUOVO UNA RELAZIONE -> ATTRIBUTO NON APPARTIENE PIU' AD UNA STRUTTURA
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<ViaggioRoute> delete(@PathVariable int id ) throws ViaggioRouteNotFoundException,IllegalArgumentException{
		
		viaggioRouteService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getByViaggioId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ViaggioRouteDTO> getByViaggioId(@PathVariable int id) throws ViaggioRouteNotFoundException, ViaggioNotFoundException{
		
		List<ViaggioRoute> offerTreatsList = viaggioRouteService.findByViaggioId(id);
		List<ViaggioRouteDTO> offerTreatsDTOList = new ArrayList<ViaggioRouteDTO>(); 
		
		for (ViaggioRoute viaggioRoute : offerTreatsList) {
			ViaggioRouteDTO viaggioRouteDTO = modelMapper.map(viaggioRoute, ViaggioRouteDTO.class);
			
			viaggioRouteDTO.setViaggioId(viaggioRoute.getViaggio().getId());
			viaggioRouteDTO.setRouteId(viaggioRoute.getRoute().getId());
			
			offerTreatsDTOList.add(viaggioRouteDTO);
		}
		
		
		return offerTreatsDTOList;
	}

	
//	CERCO TUTTE LE RELAZIONI ATTRIBUTO-STRUTTURA DALL'id DELLA STRUTTURA
//  Esempio: Voglio sapere tutti gli attributi possibili della struttura con id : 1
	@RequestMapping(value="/getByRouteId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ViaggioRouteDTO> getByRouteId(@PathVariable int id) throws ViaggioRouteNotFoundException, RouteNotFoundException{
		
		List<ViaggioRoute> offerTreatsList = viaggioRouteService.findByRouteId(id);
		List<ViaggioRouteDTO> offerTreatsDTOList = new ArrayList<ViaggioRouteDTO>(); 
		
		for (ViaggioRoute viaggioRoute : offerTreatsList) {
			ViaggioRouteDTO viaggioRouteDTO = modelMapper.map(viaggioRoute, ViaggioRouteDTO.class);
			
			viaggioRouteDTO.setViaggioId(viaggioRoute.getViaggio().getId());
			viaggioRouteDTO.setRouteId(viaggioRoute.getRoute().getId());
			
			offerTreatsDTOList.add(viaggioRouteDTO);
		}
		
		
		return offerTreatsDTOList;
	}
	
	@RequestMapping(value="/getByViaggioIdAndRouteId/{vectorId}/{routeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ViaggioRouteDTO getByViaggioAndRoute(@PathVariable int viaggioId, @PathVariable int routeId) throws ViaggioRouteNotFoundException{
		
		ViaggioRoute viaggioRoute = viaggioRouteService.findByViaggioIdAndRouteId(viaggioId,routeId);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		ViaggioRouteDTO viaggioRouteDTO = modelMapper.map(viaggioRoute, ViaggioRouteDTO.class);
		
		
		return viaggioRouteDTO;
	}
	
}
