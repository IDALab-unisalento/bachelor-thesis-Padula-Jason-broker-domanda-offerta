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

import it.unisalento.brokerapp.DTOclasses.VectorTreatsDTO;
import it.unisalento.brokerapp.domainClasses.VectorTreats;
import it.unisalento.brokerapp.exceptions.VectorTreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingVectorTreatsException;
import it.unisalento.brokerapp.exceptions.TreatsNotFoundException;
import it.unisalento.brokerapp.exceptions.VectorNotFoundException;
import it.unisalento.brokerapp.iservices.IVectorTreatsService;
import it.unisalento.brokerapp.iservices.ITreatsService;
import it.unisalento.brokerapp.iservices.IVectorService;

@RestController
@RequestMapping("/vectorTreats")
public class VectorTreatsRestController {

	
	@Autowired
	IVectorTreatsService vectorTreatsService;
	
	@Autowired
	IVectorService vectorService;
	
	@Autowired
	ITreatsService treatsService;
	
	
	private ModelMapper modelMapper = new ModelMapper();

//	VIENE RAPPRESENTATA LA RELAZIONE DI APPARTENENZA CHE C'E TRA UNA STRUTTURA FISSA E I SUOI ATTRIBUTI
//	NEL PROGETTO CI SARANNO 3 STRUTTURE E AD OGNUNA SARANNO COLLEGATI CIRCA 5-10 ATTRIBUTI
//	per cui questa TABELLA SARA' NELL'ORDINE DEI 30 INGRESSO
	
//	CERCO UNA RELAZIONE -> ATTRIBUTO  APPARTIENE A UNA STRUTTURA dall'ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public VectorTreatsDTO get(@PathVariable int id) throws VectorTreatsNotFoundException{
		
		VectorTreats vectorTreats = vectorTreatsService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		VectorTreatsDTO vectorTreatsDTO = modelMapper.map(vectorTreats, VectorTreatsDTO.class);
		vectorTreatsDTO.setVectorId(vectorTreats.getVector().getId());
		vectorTreatsDTO.setTreatsId(vectorTreats.getTreats().getId());
		
		return vectorTreatsDTO;
	}
	
//	CERCO TUTTE LE RELAZIONI DI APPARTENENZA
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VectorTreatsDTO> getAll(){
		
		List<VectorTreats> 	offerTreatsList = vectorTreatsService.getAll();
		List<VectorTreatsDTO> offerTreatsDTOList = new ArrayList<VectorTreatsDTO>(); 
		
		for (VectorTreats vectorTreats : offerTreatsList) {
			
			VectorTreatsDTO vectorTreatsDTO = modelMapper.map(vectorTreats, VectorTreatsDTO.class);
			vectorTreatsDTO.setVectorId(vectorTreats.getVector().getId());
			vectorTreatsDTO.setTreatsId(vectorTreats.getTreats().getId());
			
			offerTreatsDTOList.add(vectorTreatsDTO);
		}
		
		
		return offerTreatsDTOList;
	}

	
// AGGIUNGO UNA RELAZIONE -> ATTRIBUTE APPARTIENE AD UNA STRUTTURA	
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public VectorTreatsDTO save(@RequestBody @Valid VectorTreatsDTO vectorTreatsDTO) throws SavingVectorTreatsException, TreatsNotFoundException, VectorNotFoundException {
		
		VectorTreats vectorTreats = modelMapper.map(vectorTreatsDTO, VectorTreats.class);
		
		vectorTreats.setVector(vectorService.getById(vectorTreatsDTO.getVectorId()));
		vectorTreats.setTreats(treatsService.getById(vectorTreatsDTO.getTreatsId()));

		VectorTreats offerTreatsSaved = vectorTreatsService.save(vectorTreats);	
		vectorTreatsDTO.setId(offerTreatsSaved.getId());
		
		return vectorTreatsDTO;
		
	}

//	RIMUOVO UNA RELAZIONE -> ATTRIBUTO NON APPARTIENE PIU' AD UNA STRUTTURA
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<VectorTreats> delete(@PathVariable int id ) throws VectorTreatsNotFoundException,IllegalArgumentException{
		
		vectorTreatsService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getByVectorId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VectorTreatsDTO> getByVectorId(@PathVariable int id) throws VectorTreatsNotFoundException, VectorNotFoundException{
		
		List<VectorTreats> offerTreatsList = vectorTreatsService.findByVectorId(id);
		List<VectorTreatsDTO> offerTreatsDTOList = new ArrayList<VectorTreatsDTO>(); 
		
		for (VectorTreats vectorTreats : offerTreatsList) {
			VectorTreatsDTO vectorTreatsDTO = modelMapper.map(vectorTreats, VectorTreatsDTO.class);
			
			vectorTreatsDTO.setVectorId(vectorTreats.getVector().getId());
			vectorTreatsDTO.setTreatsId(vectorTreats.getTreats().getId());
			
			offerTreatsDTOList.add(vectorTreatsDTO);
		}
		
		
		return offerTreatsDTOList;
	}

	
//	CERCO TUTTE LE RELAZIONI ATTRIBUTO-STRUTTURA DALL'id DELLA STRUTTURA
//  Esempio: Voglio sapere tutti gli attributi possibili della struttura con id : 1
	@RequestMapping(value="/getByTreatsId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VectorTreatsDTO> getByTreatsId(@PathVariable int id) throws VectorTreatsNotFoundException, TreatsNotFoundException{
		
		List<VectorTreats> offerTreatsList = vectorTreatsService.findByTreatsId(id);
		List<VectorTreatsDTO> offerTreatsDTOList = new ArrayList<VectorTreatsDTO>(); 
		
		for (VectorTreats vectorTreats : offerTreatsList) {
			VectorTreatsDTO vectorTreatsDTO = modelMapper.map(vectorTreats, VectorTreatsDTO.class);
			
			vectorTreatsDTO.setVectorId(vectorTreats.getVector().getId());
			vectorTreatsDTO.setTreatsId(vectorTreats.getTreats().getId());
			
			offerTreatsDTOList.add(vectorTreatsDTO);
		}
		
		
		return offerTreatsDTOList;
	}
	
	@RequestMapping(value="/getByVectorIdAndTreatsId/{vectorId}/{treatsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public VectorTreatsDTO getByVectorAndTreats(@PathVariable int vectorId, @PathVariable int treatsId) throws VectorTreatsNotFoundException{
		
		VectorTreats vectorTreats = vectorTreatsService.findByVectorIdAndTreatsId(vectorId,treatsId);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		
		VectorTreatsDTO vectorTreatsDTO = modelMapper.map(vectorTreats, VectorTreatsDTO.class);
		
		
		return vectorTreatsDTO;
	}
	
}
