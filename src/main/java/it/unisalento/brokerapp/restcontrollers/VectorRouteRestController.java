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

import it.unisalento.brokerapp.DTOclasses.VectorRouteDTO;
import it.unisalento.brokerapp.domainClasses.VectorRoute;
import it.unisalento.brokerapp.exceptions.VectorRouteNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingVectorRouteException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.exceptions.RouteNotFoundException;
import it.unisalento.brokerapp.iservices.IVectorRouteService;
import it.unisalento.brokerapp.iservices.IVectorService;
import it.unisalento.brokerapp.iservices.IRouteService;

@RestController
@RequestMapping("/vectorRoute")
public class VectorRouteRestController {

	
	@Autowired
	IVectorRouteService vectorRouteService;
	
	@Autowired
	IVectorService vectorService;
	
	@Autowired
	IRouteService routeService;
	
	
	private ModelMapper modelMapper = new ModelMapper();

//	VIENE RAPPRESENTATA LA RELAZIONE DI APPARTENENZA CHE C'E TRA UNA STRUTTURA FISSA E I SUOI ATTRIBUTI
//	NEL PROGETTO CI SARANNO 3 STRUTTURE E AD OGNUNA SARANNO COLLEGATI CIRCA 5-10 ATTRIBUTI
//	per cui questa TABELLA SARA' NELL'ORDINE DEI 30 INGRESSO
	
//	CERCO UNA RELAZIONE -> ATTRIBUTO  APPARTIENE A UNA STRUTTURA dall'ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public VectorRouteDTO get(@PathVariable int id) throws VectorRouteNotFoundException{
		
		VectorRoute vectorRoute = vectorRouteService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		VectorRouteDTO vectorRouteDTO = modelMapper.map(vectorRoute, VectorRouteDTO.class);
		vectorRouteDTO.setVectorId(vectorRoute.getVector().getId());
		vectorRouteDTO.setRouteId(vectorRoute.getRoute().getId());
		
		return vectorRouteDTO;
	}
	
//	CERCO TUTTE LE RELAZIONI DI APPARTENENZA
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VectorRouteDTO> getAll(){
		
		List<VectorRoute> 	offerTreatsList = vectorRouteService.getAll();
		List<VectorRouteDTO> offerTreatsDTOList = new ArrayList<VectorRouteDTO>(); 
		
		for (VectorRoute vectorRoute : offerTreatsList) {
			
			VectorRouteDTO vectorRouteDTO = modelMapper.map(vectorRoute, VectorRouteDTO.class);
			vectorRouteDTO.setVectorId(vectorRoute.getVector().getId());
			vectorRouteDTO.setRouteId(vectorRoute.getRoute().getId());
			
			offerTreatsDTOList.add(vectorRouteDTO);
		}
		
		
		return offerTreatsDTOList;
	}

	
// AGGIUNGO UNA RELAZIONE -> ATTRIBUTE APPARTIENE AD UNA STRUTTURA	
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public VectorRouteDTO save(@RequestBody @Valid VectorRouteDTO vectorRouteDTO) throws SavingVectorRouteException, RouteNotFoundException, VectorNotFoundException {
		
		VectorRoute vectorRoute = modelMapper.map(vectorRouteDTO, VectorRoute.class);
		
		vectorRoute.setVector(vectorService.getById(vectorRouteDTO.getVectorId()));
		vectorRoute.setRoute(routeService.getById(vectorRouteDTO.getRouteId()));

		VectorRoute offerTreatsSaved = vectorRouteService.save(vectorRoute);	
		vectorRouteDTO.setId(offerTreatsSaved.getId());
		
		return vectorRouteDTO;
		
	}

//	RIMUOVO UNA RELAZIONE -> ATTRIBUTO NON APPARTIENE PIU' AD UNA STRUTTURA
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<VectorRoute> delete(@PathVariable int id ) throws VectorRouteNotFoundException,IllegalArgumentException{
		
		vectorRouteService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getByVectorId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VectorRouteDTO> getByVectorId(@PathVariable int id) throws VectorRouteNotFoundException, VectorNotFoundException{
		
		List<VectorRoute> offerTreatsList = vectorRouteService.findByVectorId(id);
		List<VectorRouteDTO> offerTreatsDTOList = new ArrayList<VectorRouteDTO>(); 
		
		for (VectorRoute vectorRoute : offerTreatsList) {
			VectorRouteDTO vectorRouteDTO = modelMapper.map(vectorRoute, VectorRouteDTO.class);
			
			vectorRouteDTO.setVectorId(vectorRoute.getVector().getId());
			vectorRouteDTO.setRouteId(vectorRoute.getRoute().getId());
			
			offerTreatsDTOList.add(vectorRouteDTO);
		}
		
		
		return offerTreatsDTOList;
	}

	
//	CERCO TUTTE LE RELAZIONI ATTRIBUTO-STRUTTURA DALL'id DELLA STRUTTURA
//  Esempio: Voglio sapere tutti gli attributi possibili della struttura con id : 1
	@RequestMapping(value="/getByRouteId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VectorRouteDTO> getByRouteId(@PathVariable int id) throws VectorRouteNotFoundException, RouteNotFoundException{
		
		List<VectorRoute> offerTreatsList = vectorRouteService.findByRouteId(id);
		List<VectorRouteDTO> offerTreatsDTOList = new ArrayList<VectorRouteDTO>(); 
		
		for (VectorRoute vectorRoute : offerTreatsList) {
			VectorRouteDTO vectorRouteDTO = modelMapper.map(vectorRoute, VectorRouteDTO.class);
			
			vectorRouteDTO.setVectorId(vectorRoute.getVector().getId());
			vectorRouteDTO.setRouteId(vectorRoute.getRoute().getId());
			
			offerTreatsDTOList.add(vectorRouteDTO);
		}
		
		
		return offerTreatsDTOList;
	}
	
	@RequestMapping(value="/getByVectorIdAndRouteId/{vectorId}/{routeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public VectorRouteDTO getByVectorAndRoute(@PathVariable int vectorId, @PathVariable int routeId) throws VectorRouteNotFoundException{
		
		VectorRoute vectorRoute = vectorRouteService.findByVectorIdAndRouteId(vectorId,routeId);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		VectorRouteDTO vectorRouteDTO = modelMapper.map(vectorRoute, VectorRouteDTO.class);
		
		
		return vectorRouteDTO;
	}
	
}
