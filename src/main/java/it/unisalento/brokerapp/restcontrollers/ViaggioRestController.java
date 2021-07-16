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

import it.unisalento.brokerapp.DTOclasses.ViaggioDTO;
import it.unisalento.brokerapp.domainClasses.Viaggio;
import it.unisalento.brokerapp.exceptions.SavingViaggioException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.exceptions.ViaggioNotFoundException;
import it.unisalento.brokerapp.iservices.IViaggioService;



@RestController
@RequestMapping("/viaggio")
public class ViaggioRestController {

	@Autowired
	IViaggioService viaggioService;

	private ModelMapper modelMapper = new ModelMapper();

//	RICERCO UN ALLEGATO PER ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ViaggioDTO get(@PathVariable int id) throws ViaggioNotFoundException{
		
		Viaggio viaggio = viaggioService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		ViaggioDTO viaggioDTO = modelMapper.map(viaggio, ViaggioDTO.class);

		
		return viaggioDTO;
	}
	
	
//	RICERCO TUTTI GLI ALLEGATI
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ViaggioDTO> getAll(){
		
		List<Viaggio> 	viaggiList = viaggioService.getAll();
	
		List<ViaggioDTO> viaggiDTOList = new ArrayList<ViaggioDTO>(); 
		
		for (Viaggio viaggio : viaggiList) {
			ViaggioDTO viaggioDTO = modelMapper.map(viaggio, ViaggioDTO.class);
			viaggiDTOList.add(viaggioDTO);
		}
		
		
		return viaggiDTOList;
	}

	
//	SALVO UN ALLEGATO
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ViaggioDTO save(@RequestBody @Valid ViaggioDTO viaggioDTO) throws SavingViaggioException {
		
		
		Viaggio  viaggio = new Viaggio();
		
		viaggio = modelMapper.map(viaggioDTO, Viaggio.class);
		
		Viaggio viaggioSaved = viaggioService.save(viaggio);	
		viaggioDTO.setId(viaggioSaved.getId());
		
		return viaggioDTO;
		
	}
	
    

//	RIMUOVO UN ALLEGATO
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<Viaggio> delete(@PathVariable int id ) throws ViaggioNotFoundException{
		
		viaggioService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
//	RICERCO TUTTI I VIAGGI per vectorID
	@RequestMapping(value="/getByVector/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ViaggioDTO> getByVector(@PathVariable int id) throws VectorNotFoundException{
		
		List<Viaggio> 	viaggiList = viaggioService.findByVectorId(id);
		List<ViaggioDTO> viaggiDTOList = new ArrayList<ViaggioDTO>(); 
		
		for (Viaggio viaggio : viaggiList) {
			
			ViaggioDTO viaggioDTO = modelMapper.map(viaggio, ViaggioDTO.class);

			viaggiDTOList.add(viaggioDTO);
		}
		
		
		return viaggiDTOList;
	}
}
