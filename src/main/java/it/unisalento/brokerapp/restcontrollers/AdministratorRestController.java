
package it.unisalento.brokerapp.restcontrollers;

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

import it.unisalento.brokerapp.DTOclasses.AdministratorDTO;
import it.unisalento.brokerapp.DTOclasses.UserDTO;
import it.unisalento.brokerapp.domainClasses.Administrator;
import it.unisalento.brokerapp.exceptions.SavingUserException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.iservices.IAdministratorService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/administrator")
public class AdministratorRestController {

	@Autowired
	IAdministratorService administratorService;

	private ModelMapper modelMapper = new ModelMapper();




	@PostMapping(value = "/enableUserById", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean EnableUserById(@RequestBody UserDTO toManage) throws UserNotFoundException {

		if (administratorService.EnableUserById(toManage.getId())) {
			return true; 
		}
		return false;
	}

	
	/**************************************************************************************/

//	RICERCO UN AMMINISTRATORE PER ID
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AdministratorDTO get(@PathVariable int id) throws UserNotFoundException {

		Administrator administrator = administratorService.getById(id);

		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO

		AdministratorDTO administratorDTO = modelMapper.map(administrator, AdministratorDTO.class);
		administratorDTO.setPassword("*******");

		return administratorDTO;
	}


//	RICERCO TUTTI GLI AMMINISTRATORI
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AdministratorDTO> getAll() {

		List<Administrator> userList = administratorService.getAll();
		List<AdministratorDTO> userDTOList = new ArrayList<AdministratorDTO>();

		for (Administrator administrator : userList) {

			AdministratorDTO administratorDTO = modelMapper.map(administrator, AdministratorDTO.class);

			userDTOList.add(administratorDTO);
		}

		return userDTOList;
	}

	// come passare parametri nel body della request
	// quando possiamo farlo ? solo in 1 caso : quando il method è un POST

//	AGGIUNGO UN AMMINISTRATORE
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean save(@RequestBody @Valid AdministratorDTO administratorDTO) throws SavingUserException {

		Administrator administrator = modelMapper.map(administratorDTO, Administrator.class);

		administratorService.save(administrator);
		return true;

	}

//	RIMUOVERE AMMINISTRATORE tramite il suo ID
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Administrator> delete(@PathVariable int id)
			throws UserNotFoundException, IllegalArgumentException {

		administratorService.delete(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}