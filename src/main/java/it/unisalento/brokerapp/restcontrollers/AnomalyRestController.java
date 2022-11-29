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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.brokerapp.DTOclasses.AnomalyDTO;
import it.unisalento.brokerapp.domainClasses.Anomaly;
import it.unisalento.brokerapp.exceptions.AnomalyNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingAnomalyException;
import it.unisalento.brokerapp.iservices.IAnomalyLogService;
import it.unisalento.brokerapp.iservices.IAnomalyService;
import it.unisalento.brokerapp.iservices.IVectorService;


@RestController
@RequestMapping("/anomaly")
public class AnomalyRestController {

	@Autowired
	IAnomalyService anomalyService;
	
	@Autowired
	IAnomalyLogService anomalyLogService;
	
	@Autowired
	IVectorService vectorService;
	
	private ModelMapper modelMapper = new ModelMapper();

	@PostMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AnomalyDTO insert (@RequestBody @Valid AnomalyDTO anomalyDTO) throws SavingAnomalyException, NullPointerException{
		try {
		Anomaly anomaly = modelMapper.map(anomalyDTO, Anomaly.class);
		anomaly.setVector(vectorService.getById(anomalyDTO.getVector_id()));
		Anomaly anomalyInserted = anomalyService.report(anomaly);
		anomalyDTO.setId(anomalyInserted.getId());
		anomalyLogService.logReport(anomalyDTO);
		return anomalyDTO;
		} catch (Exception e) {
			throw new SavingAnomalyException();
		}
		
	}
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyDTO> getAll(){
		
		List<Anomaly> anomalyList = anomalyService.getAll();
		List<AnomalyDTO> anomalyDTOList = new ArrayList<AnomalyDTO>(); 
		
		for (Anomaly anomaly : anomalyList) {
			AnomalyDTO anomalyDTO = modelMapper.map(anomaly, AnomalyDTO.class);
			anomalyDTO.setVector_id(anomaly.getVector().getId());
			anomalyDTOList.add(anomalyDTO);
			}
		
		return anomalyDTOList;
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Anomaly> delete(@PathVariable int id) throws AnomalyNotFoundException, IllegalArgumentException, NullPointerException{
		
		try {
		int vec = anomalyService.findById(id).getVector().getId();
		anomalyLogService.logDelete(vec);
		}catch(Exception e) {
			throw new AnomalyNotFoundException();
		}
		anomalyService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getById/{Id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AnomalyDTO getById(@PathVariable int Id) throws AnomalyNotFoundException{
		
		try {
		Anomaly anomaly = anomalyService.findById(Id);
		AnomalyDTO anomalyDTO = modelMapper.map(anomaly, AnomalyDTO.class);
		anomalyDTO.setVector_id(anomaly.getVector().getId());
		return anomalyDTO;
		} catch (Exception e) {
			throw new AnomalyNotFoundException();
		}
	}
	
	@RequestMapping(value = "/getByVectorId/{vectorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AnomalyDTO getByVectorId(@PathVariable int vectorId) throws AnomalyNotFoundException{
		
		try {
		Anomaly anomaly = anomalyService.findByVectorId(vectorId);
		AnomalyDTO anomalyDTO = modelMapper.map(anomaly, AnomalyDTO.class);
		anomalyDTO.setVector_id(anomaly.getVector().getId());
		return anomalyDTO;
		} catch (Exception e) {
			throw new AnomalyNotFoundException();
		}
	}
	
	@RequestMapping(value = "/getByGpsErr", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyDTO> getByGpsErr(){
		
		List<Anomaly> anomalyList = anomalyService.getByGpsErr();
		List<AnomalyDTO> anomalyDTOList = new ArrayList<AnomalyDTO>(); 
		
		for (Anomaly anomaly : anomalyList) {
			AnomalyDTO anomalyDTO = modelMapper.map(anomaly, AnomalyDTO.class);
			anomalyDTO.setVector_id(anomaly.getVector().getId());
			anomalyDTOList.add(anomalyDTO);
			}
		
		return anomalyDTOList;
	}
	
	@RequestMapping(value = "/getByFridgeErr", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyDTO> getByFridgeErr(){
		
		List<Anomaly> anomalyList = anomalyService.getByFridgeErr();
		List<AnomalyDTO> anomalyDTOList = new ArrayList<AnomalyDTO>(); 
		
		for (Anomaly anomaly : anomalyList) {
			AnomalyDTO anomalyDTO = modelMapper.map(anomaly, AnomalyDTO.class);
			anomalyDTO.setVector_id(anomaly.getVector().getId());
			anomalyDTOList.add(anomalyDTO);
			}
		
		return anomalyDTOList;
	}
	
	@RequestMapping(value = "/getByTempErr", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyDTO> getByTempErr(){
		
		List<Anomaly> anomalyList = anomalyService.getByTempErr();
		List<AnomalyDTO> anomalyDTOList = new ArrayList<AnomalyDTO>(); 
		
		for (Anomaly anomaly : anomalyList) {
			AnomalyDTO anomalyDTO = modelMapper.map(anomaly, AnomalyDTO.class);
			anomalyDTO.setVector_id(anomaly.getVector().getId());
			anomalyDTOList.add(anomalyDTO);
			}
		
		return anomalyDTOList;
	}
	
	@RequestMapping(value = "/getByTailErr", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyDTO> getByTailErr(){
		
		List<Anomaly> anomalyList = anomalyService.getByTailErr();
		List<AnomalyDTO> anomalyDTOList = new ArrayList<AnomalyDTO>(); 
		
		for (Anomaly anomaly : anomalyList) {
			AnomalyDTO anomalyDTO = modelMapper.map(anomaly, AnomalyDTO.class);
			anomalyDTO.setVector_id(anomaly.getVector().getId());
			anomalyDTOList.add(anomalyDTO);
			}
		
		return anomalyDTOList;
	}
	
	@RequestMapping(value = "/getByGen1Err", method  = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyDTO> getByGen1Err(){
		
		List<Anomaly> anomalyList = anomalyService.getByGen1Err();
		List<AnomalyDTO> anomalyDTOList = new ArrayList<AnomalyDTO>(); 
		
		for (Anomaly anomaly : anomalyList) {
			AnomalyDTO anomalyDTO = modelMapper.map(anomaly, AnomalyDTO.class);
			anomalyDTO.setVector_id(anomaly.getVector().getId());
			anomalyDTOList.add(anomalyDTO);
			}
		
		return anomalyDTOList;
	}
	
	@RequestMapping(value = "/getByGen2Err", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyDTO> getByGen2Err(){
		
		List<Anomaly> anomalyList = anomalyService.getByGen2Err();
		List<AnomalyDTO> anomalyDTOList = new ArrayList<AnomalyDTO>(); 
		
		for (Anomaly anomaly : anomalyList) {
			AnomalyDTO anomalyDTO = modelMapper.map(anomaly, AnomalyDTO.class);
			anomalyDTO.setVector_id(anomaly.getVector().getId());
			anomalyDTOList.add(anomalyDTO);
			}
		
		return anomalyDTOList;
	}
	
	@RequestMapping(value = "/getByGen3Err", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyDTO> getByGen3Err(){
		
		List<Anomaly> anomalyList = anomalyService.getByGen3Err();
		List<AnomalyDTO> anomalyDTOList = new ArrayList<AnomalyDTO>(); 
		
		for (Anomaly anomaly : anomalyList) {
			AnomalyDTO anomalyDTO = modelMapper.map(anomaly, AnomalyDTO.class);
			anomalyDTO.setVector_id(anomaly.getVector().getId());
			anomalyDTOList.add(anomalyDTO);
			}
		
		return anomalyDTOList;
	}
	
	@GetMapping(value = "/manGps")
	public int manGps(@RequestParam(required = true, value = "val") Boolean val, @RequestParam(required = true, value = "vectorId") int vectorId) throws SavingAnomalyException, AnomalyNotFoundException{
		try {
		if (anomalyService.findByVectorId(vectorId) == null) {
			throw new AnomalyNotFoundException();
		}
		
		String type = "Gps";
		anomalyLogService.logAnomaly(val, vectorId, type);
		
		return anomalyService.manGps(val, vectorId);
		} catch (Exception e ) {
			throw new SavingAnomalyException();
		}
	}
	
	@GetMapping(value = "/manFridge")
	public int manFridge(@RequestParam(required = true, value = "val") Boolean val, @RequestParam(required = true, value = "vectorId") int vectorId) throws SavingAnomalyException, AnomalyNotFoundException{
		try {
		if (anomalyService.findByVectorId(vectorId) == null) {
			throw new AnomalyNotFoundException();
		}
		
		String type = "Fridge";
		anomalyLogService.logAnomaly(val, vectorId, type);
		
		return anomalyService.manFridge(val, vectorId);
		} catch (Exception e ) {
			throw new SavingAnomalyException();
		}
	}
	
	@GetMapping(value = "/manTemp")
	public int manTemp(@RequestParam(required = true, value = "val") Boolean val, @RequestParam(required = true, value = "vectorId") int vectorId) throws SavingAnomalyException, AnomalyNotFoundException{
		try {
		if (anomalyService.findByVectorId(vectorId) == null) {
			throw new AnomalyNotFoundException();
		}
		
		String type = "Temp";
		anomalyLogService.logAnomaly(val, vectorId, type);
		
		return anomalyService.manTemp(val, vectorId);
		} catch (Exception e ) {
			throw new SavingAnomalyException();
		}
	}
	
	@GetMapping(value = "/manTail")
	public int manTail(@RequestParam(required = true, value = "val") Boolean val, @RequestParam(required = true, value = "vectorId") int vectorId) throws SavingAnomalyException, AnomalyNotFoundException{
		try {
		if (anomalyService.findByVectorId(vectorId) == null) {
			throw new AnomalyNotFoundException();
		}
		
		String type = "Tail";
		anomalyLogService.logAnomaly(val, vectorId, type);
		
		return anomalyService.manTail(val, vectorId);
		} catch (Exception e ) {
			throw new SavingAnomalyException();
		}
	}
	
	@GetMapping(value = "/manGen1")
	public int manGen1(@RequestParam(required = true, value = "val") Boolean val, @RequestParam(required = true, value = "vectorId") int vectorId) throws SavingAnomalyException, AnomalyNotFoundException{
		try {
		if (anomalyService.findByVectorId(vectorId) == null) {
			throw new AnomalyNotFoundException();
		}
		
		String type = "Gen1";
		anomalyLogService.logAnomaly(val, vectorId, type);
		
		return anomalyService.manGen1(val, vectorId);
		} catch (Exception e ) {
			throw new SavingAnomalyException();
		}
	}
	
	@GetMapping(value = "/manGen2")
	public int manGen2(@RequestParam(required = true, value = "val") Boolean val, @RequestParam(required = true, value = "vectorId") int vectorId) throws SavingAnomalyException, AnomalyNotFoundException{
		try {
		if (anomalyService.findByVectorId(vectorId) == null) {
			throw new AnomalyNotFoundException();
		}
		String type = "Gen2";
		anomalyLogService.logAnomaly(val, vectorId, type);
		
		return anomalyService.manGen2(val, vectorId);
		} catch (Exception e ) {
			throw new SavingAnomalyException();
		}
	}
	
	@GetMapping(value = "/manGen3")
	public int manGen3(@RequestParam(required = true, value = "val") Boolean val, @RequestParam(required = true, value = "vectorId") int vectorId) throws SavingAnomalyException, AnomalyNotFoundException{
		try {
		if (anomalyService.findByVectorId(vectorId) == null) {
			throw new AnomalyNotFoundException();
		}
		
		String type = "Gen3";
		anomalyLogService.logAnomaly(val, vectorId, type);
		
		return anomalyService.manGen3(val, vectorId);
		} catch (Exception e ) {
			throw new SavingAnomalyException();
		}
	}
	
	@RequestMapping(value = "/fixVector/{vectorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int fixVector(@PathVariable int vectorId) throws AnomalyNotFoundException, SavingAnomalyException{
		try {
			if (anomalyService.findByVectorId(vectorId) == null) {
				throw new AnomalyNotFoundException();
			}
			
			String type = "All";
			anomalyLogService.logFix(type, vectorId);
			
			return anomalyService.fixVector(vectorId);
			} catch (Exception e ) {
				throw new SavingAnomalyException();
			}
	}
	
	
	
	
}
