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

import it.unisalento.brokerapp.DTOclasses.VectorDTO;
import it.unisalento.brokerapp.domainClasses.Vector;
import it.unisalento.brokerapp.exceptions.SavingVectorException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.iservices.IVectorService;

@RestController
@RequestMapping("/vector")
public class VectorRestController {

	@Autowired
	IVectorService vectorService;

	private ModelMapper modelMapper = new ModelMapper();

//	RICERCO UN ALLEGATO PER ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public VectorDTO get(@PathVariable int id) throws VectorNotFoundException{
		
		Vector vector = vectorService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		VectorDTO vectorDTO = modelMapper.map(vector, VectorDTO.class);

		
		return vectorDTO;
	}
	
	
//	RICERCO TUTTI GLI ALLEGATI
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VectorDTO> getAll(){
		
		List<Vector> 	vectorList = vectorService.getAll();
		List<VectorDTO> vectorDTOList = new ArrayList<VectorDTO>(); 
		
		for (Vector vector : vectorList) {
			
			VectorDTO vectorDTO = modelMapper.map(vector, VectorDTO.class);

			vectorDTOList.add(vectorDTO);
		}
		
		
		return vectorDTOList;
	}

	
//	SALVO UN ALLEGATO
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public VectorDTO save(@RequestBody @Valid VectorDTO vectorDTO) throws SavingVectorException {
		
		
		Vector  vector = new Vector();
		
		vector = modelMapper.map(vectorDTO, Vector.class);
		
		Vector vectorSaved = vectorService.save(vector);	
		vectorDTO.setId(vectorSaved.getId());
		
		return vectorDTO;
		
	}
	
    

//	RIMUOVO UN ALLEGATO
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<Vector> delete(@PathVariable int id ) throws VectorNotFoundException{
		
		vectorService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// CERCO UN'AZIENDA DALLA TARGA
	@RequestMapping(value="/getByLicensePlate/{targa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public VectorDTO getByLicensePlate(@PathVariable String targa) throws VectorNotFoundException{
		
		Vector vector = vectorService.findByLicensePlate(targa);
	
		VectorDTO vectorDTO = modelMapper.map(vector, VectorDTO.class);
		
		return vectorDTO;
	}
}
