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

import it.unisalento.brokerapp.DTOclasses.AffittuarioDTO;
import it.unisalento.brokerapp.domainClasses.Affittuario;
import it.unisalento.brokerapp.exceptions.SavingUserException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.iservices.IAffittuarioService;


@RestController
@RequestMapping("/affittuario")
public class AffittuarioRestController {

	
	@Autowired
	IAffittuarioService affittuarioService;

	private ModelMapper modelMapper = new ModelMapper();

//	RICERCO UN ALLEGATO PER ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AffittuarioDTO get(@PathVariable int id) throws UserNotFoundException{
		
		Affittuario affittuario = affittuarioService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		AffittuarioDTO affittuarioDTO = modelMapper.map(affittuario, AffittuarioDTO.class);

		
		return affittuarioDTO;
	}
	
	
//	RICERCO TUTTI GLI ALLEGATI
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AffittuarioDTO> getAll(){
		
		List<Affittuario> 	affituariList = affittuarioService.getAll();
		List<AffittuarioDTO> affittuariDTOList = new ArrayList<AffittuarioDTO>(); 
		
		for (Affittuario affittuario : affituariList) {
			
			AffittuarioDTO affittuarioDTO = modelMapper.map(affittuario, AffittuarioDTO.class);

			affittuariDTOList.add(affittuarioDTO);
		}
		
		
		return affittuariDTOList;
	}

	
//	SALVO UN ALLEGATO
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AffittuarioDTO save(@RequestBody @Valid AffittuarioDTO affittuarioDTO) throws SavingUserException {
		
		
		Affittuario  affittuario = new Affittuario();
		
		affittuario = modelMapper.map(affittuarioDTO, Affittuario.class);
		affittuario.setDisabilitated(true);

		Affittuario affittuarioSaved = affittuarioService.save(affittuario);	
		affittuarioDTO.setId(affittuarioSaved.getId());
		
		return affittuarioDTO;
		
	}
	
    

//	RIMUOVO UN ALLEGATO
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<Affittuario> delete(@PathVariable int id ) throws UserNotFoundException{
		
		affittuarioService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
//  CERCO TUTTI GLI AFFITTUARI DISABILITATI {/true} O ABILITATI {/false}
	@RequestMapping(value="/getDisabled/{disabilitated}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AffittuarioDTO> getDisabled(@PathVariable boolean disabilitated ) throws UserNotFoundException{
		
		List<Affittuario> 	affittuarioList = affittuarioService.findByDisabilitated(disabilitated);
		List<AffittuarioDTO> affittuarioDTOList = new ArrayList<AffittuarioDTO>(); 
		
		for (Affittuario affittuario : affittuarioList) {
			
			AffittuarioDTO affittuarioDTO = modelMapper.map(affittuario, AffittuarioDTO.class);

			affittuarioDTOList.add(affittuarioDTO);
		}
			
		
		return affittuarioDTOList;
	}
	
}
