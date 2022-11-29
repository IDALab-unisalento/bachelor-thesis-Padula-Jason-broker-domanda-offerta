package it.unisalento.brokerapp.restcontrollers;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.brokerapp.DTOclasses.AnomalyLogDTO;
import it.unisalento.brokerapp.domainClasses.AnomalyLog;
import it.unisalento.brokerapp.exceptions.AnomalyLogNotFoundException;
import it.unisalento.brokerapp.iservices.IAnomalyLogService;

@RestController
@RequestMapping("/anomalyLog")
public class AnomalyLogRestController {
	
	@Autowired
	IAnomalyLogService anomalyLogService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyLogDTO> getAll(){
		List<AnomalyLog> anomalyLogList = anomalyLogService.getAll();
		List<AnomalyLogDTO> anomalyLogDTOList = new ArrayList<AnomalyLogDTO>(); 
		
		
		for (AnomalyLog anomalyLog : anomalyLogList) {
			AnomalyLogDTO anomalyLogDTO = modelMapper.map(anomalyLog, AnomalyLogDTO.class);
			anomalyLogDTO.setId(anomalyLog.getId());
			
			LocalDateTime date = anomalyLog.getDate();
			ZonedDateTime ldtZoned = date.atZone(ZoneId.systemDefault());
			ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
			LocalDateTime italyLocal = utcZoned.toLocalDateTime();
			anomalyLogDTO.setDate(italyLocal);

			anomalyLogDTOList.add(anomalyLogDTO);
			}
		
		return anomalyLogDTOList;
	}
	
	@RequestMapping(value="/getByVectorId/{vectorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyLogDTO> getByVectorId(@PathVariable int vectorId) throws AnomalyLogNotFoundException {
		
		List<AnomalyLog> anomalyLogList = anomalyLogService.findByVectorId(vectorId);
		List<AnomalyLogDTO> anomalyLogDTOList = new ArrayList<AnomalyLogDTO>(); 
		
		for (AnomalyLog anomalyLog : anomalyLogList) {
			AnomalyLogDTO anomalyLogDTO = modelMapper.map(anomalyLog, AnomalyLogDTO.class);
			anomalyLogDTO.setId(anomalyLog.getId());
			
			LocalDateTime date = anomalyLog.getDate();
			ZonedDateTime ldtZoned = date.atZone(ZoneId.systemDefault());
			ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
			LocalDateTime italyLocal = utcZoned.toLocalDateTime();
			anomalyLogDTO.setDate(italyLocal);
			
			anomalyLogDTOList.add(anomalyLogDTO);
			}
		return anomalyLogDTOList;
	}
	
	@RequestMapping(value="/getByDate/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyLogDTO> getByDateId(@PathVariable Date date) throws AnomalyLogNotFoundException {
		
		List<AnomalyLog> anomalyLogList = anomalyLogService.findByDate(date.toString());
		List<AnomalyLogDTO> anomalyLogDTOList = new ArrayList<AnomalyLogDTO>(); 
			
		for (AnomalyLog anomalyLog : anomalyLogList) {
			AnomalyLogDTO anomalyLogDTO = modelMapper.map(anomalyLog, AnomalyLogDTO.class);
			anomalyLogDTO.setId(anomalyLog.getId());
			
			LocalDateTime date2 = anomalyLog.getDate();
			ZonedDateTime ldtZoned = date2.atZone(ZoneId.systemDefault());
			ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
			LocalDateTime italyLocal = utcZoned.toLocalDateTime();
			anomalyLogDTO.setDate(italyLocal);
			
			anomalyLogDTOList.add(anomalyLogDTO);
			}
		return anomalyLogDTOList;
	}
	
	@RequestMapping(value="/getByAction/{action}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyLogDTO> getByAction(@PathVariable String action) throws AnomalyLogNotFoundException {
		
		List<AnomalyLog> anomalyLogList = anomalyLogService.findByAction(action);
		List<AnomalyLogDTO> anomalyLogDTOList = new ArrayList<AnomalyLogDTO>(); 
		
		for (AnomalyLog anomalyLog : anomalyLogList) {
			AnomalyLogDTO anomalyLogDTO = modelMapper.map(anomalyLog, AnomalyLogDTO.class);
			anomalyLogDTO.setId(anomalyLog.getId());
			
			LocalDateTime date = anomalyLog.getDate();
			ZonedDateTime ldtZoned = date.atZone(ZoneId.systemDefault());
			ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
			LocalDateTime italyLocal = utcZoned.toLocalDateTime();
			anomalyLogDTO.setDate(italyLocal);
			
			anomalyLogDTOList.add(anomalyLogDTO);
			}
		return anomalyLogDTOList;
	}
	
	@RequestMapping(value="/getByType/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AnomalyLogDTO> getByType(@PathVariable String type) throws AnomalyLogNotFoundException {
		
		List<AnomalyLog> anomalyLogList = anomalyLogService.findByType(type);
		List<AnomalyLogDTO> anomalyLogDTOList = new ArrayList<AnomalyLogDTO>(); 
		
		for (AnomalyLog anomalyLog : anomalyLogList) {
			AnomalyLogDTO anomalyLogDTO = modelMapper.map(anomalyLog, AnomalyLogDTO.class);
			anomalyLogDTO.setId(anomalyLog.getId());
			
			LocalDateTime date = anomalyLog.getDate();
			ZonedDateTime ldtZoned = date.atZone(ZoneId.systemDefault());
			ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
			LocalDateTime italyLocal = utcZoned.toLocalDateTime();
			anomalyLogDTO.setDate(italyLocal);
			
			anomalyLogDTOList.add(anomalyLogDTO);
			}
		return anomalyLogDTOList;
	}
	
	

}
