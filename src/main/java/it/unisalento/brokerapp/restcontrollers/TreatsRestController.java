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

import it.unisalento.brokerapp.DTOclasses.TreatsDTO;
import it.unisalento.brokerapp.domainClasses.Treats;
import it.unisalento.brokerapp.exceptions.SavingTreatsException;
import it.unisalento.brokerapp.exceptions.TreatsNotFoundException;
import it.unisalento.brokerapp.iservices.ITreatsService;



@RestController
@RequestMapping("/treats")
public class TreatsRestController {

	@Autowired
	ITreatsService treatsService;

	private ModelMapper modelMapper = new ModelMapper();

//	RICERCO UN ALLEGATO PER ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TreatsDTO get(@PathVariable int id) throws TreatsNotFoundException{
		
		Treats treats = treatsService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		TreatsDTO treatsDTO = modelMapper.map(treats, TreatsDTO.class);

		
		return treatsDTO;
	}
	
	
//	RICERCO TUTTI GLI ALLEGATI
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TreatsDTO> getAll(){
		
		List<Treats> 	treatsList = treatsService.getAll();
		List<TreatsDTO> treatsDTOList = new ArrayList<TreatsDTO>(); 
		
		for (Treats treats : treatsList) {
			
			TreatsDTO treatsDTO = modelMapper.map(treats, TreatsDTO.class);

			treatsDTOList.add(treatsDTO);
		}
		
		
		return treatsDTOList;
	}

	
//	SALVO UN ALLEGATO
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TreatsDTO save(@RequestBody @Valid TreatsDTO treatsDTO) throws SavingTreatsException {
		
		
		Treats  treats = new Treats();
		
		treats = modelMapper.map(treatsDTO, Treats.class);
		
		Treats treatsSaved = treatsService.save(treats);	
		treatsDTO.setId(treatsSaved.getId());
		
		return treatsDTO;
		
	}
	
    

//	RIMUOVO UN ALLEGATO
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<Treats> delete(@PathVariable int id ) throws TreatsNotFoundException{
		
		treatsService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
