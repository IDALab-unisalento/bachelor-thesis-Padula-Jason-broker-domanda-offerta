package it.unisalento.brokerapp.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.brokerapp.DTOclasses.RouteDTO;
import it.unisalento.brokerapp.domainClasses.Route;
import it.unisalento.brokerapp.exceptions.SavingRouteException;
import it.unisalento.brokerapp.exceptions.RouteNotFoundException;
import it.unisalento.brokerapp.iservices.IRouteService;



@RestController
@RequestMapping("/route")
public class RouteRestController {

	@Autowired
	IRouteService routeService;

	private ModelMapper modelMapper = new ModelMapper();

//	RICERCO UN ALLEGATO PER ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RouteDTO get(@PathVariable int id) throws RouteNotFoundException{
		
		Route route = routeService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		RouteDTO routeDTO = modelMapper.map(route, RouteDTO.class);

		
		return routeDTO;
	}
	

//	RICERCO UNA route avendo le due città
	@RequestMapping(value="/getByStartCityAndEndCity/{startCity}/{endCity}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RouteDTO getByStartCityAndEndCity(@PathVariable String startCity,@PathVariable String endCity) throws RouteNotFoundException{
		
		Route route = routeService.getByStartCityAndEndCity(startCity,endCity);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		RouteDTO routeDTO = modelMapper.map(route, RouteDTO.class);

		
		return routeDTO;
	}
	
	
	
//	RICERCO TUTTI GLI ALLEGATI
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RouteDTO> getAll(){
		
		List<Route> 	treatsList = routeService.getAll();
		List<RouteDTO> treatsDTOList = new ArrayList<RouteDTO>(); 
		
		for (Route route : treatsList) {
			
			RouteDTO routeDTO = modelMapper.map(route, RouteDTO.class);

			treatsDTOList.add(routeDTO);
		}
		
		
		return treatsDTOList;
	}
	
	
	@GetMapping(value="/getRoutes/{viaggioId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<RouteDTO> getAllRoutesOfViaggioId(@PathVariable int viaggioId){
		List<Route> 	allRoutes = routeService.findAllRouteOfViaggioId(viaggioId);
		List<RouteDTO> allRoutesDTO = new ArrayList<RouteDTO>(); 
		for (Route route :allRoutes) {
			
			RouteDTO routeDTO = modelMapper.map(route, RouteDTO.class);

			allRoutesDTO.add(routeDTO);
		}
		return allRoutesDTO;
		
	}
	
//	SALVO UN ALLEGATO
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RouteDTO save(@RequestBody @Valid RouteDTO routeDTO) throws SavingRouteException {
		
		
		Route  route = new Route();
		
		route = modelMapper.map(routeDTO, Route.class);
		
		Route treatsSaved = routeService.save(route);	
		routeDTO.setId(treatsSaved.getId());
		
		return routeDTO;
		
	}
	
    

//	RIMUOVO UN ALLEGATO
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<Route> delete(@PathVariable int id ) throws RouteNotFoundException{
		
		routeService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
