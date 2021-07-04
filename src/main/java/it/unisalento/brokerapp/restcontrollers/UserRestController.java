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

import it.unisalento.brokerapp.DTOclasses.UserDTO;
import it.unisalento.brokerapp.domainClasses.User;
import it.unisalento.brokerapp.exceptions.SavingUserException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.iservices.IUserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	IUserService userService;
	
	private ModelMapper modelMapper = new ModelMapper();

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO get(@PathVariable int id) throws UserNotFoundException {

		User user = userService.getById(id);

		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO

		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		userDTO.setPassword("*******");

		return userDTO;
	}

//  CERCO TUTTI GLI USER
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> getAll() {

		List<User> userList = userService.getAll();
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();

		for (User user : userList) {

			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			userDTOList.add(userDTO);
		}

		return userDTOList;
	}

	// come passare parametri nel body della request
	// quando possiamo farlo ? solo in 1 caso : quando il method è un POST

//	SALVARE UNO USER
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean save(@RequestBody @Valid UserDTO userDTO) throws SavingUserException {
		
		try {
			User user  = new User();
			user = modelMapper.map(userDTO, User.class);
			userService.save(user);
			
		} catch (Exception e) {
			throw new SavingUserException();
		}
		 	
			return true;
		

	}

//	RIMUOVERE UN UTENTE
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> delete(@PathVariable int id) throws UserNotFoundException, IllegalArgumentException {

		userService.delete(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}


//	CERCO UNO USER PER USERNAME
	@RequestMapping(value = "/getByUsername/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO getByUsername(@PathVariable String username) throws UserNotFoundException {

		try {
			User user = userService.getByUsernameLike(username);
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			return userDTO;

		} catch (Exception e) {
			throw new UserNotFoundException();
		}

	}
	
//	CERCO UNO USER PER USERNAME E PASSWORD

	// metodo di LOGIN

	@PostMapping(value = "/getByUsernameAndPassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO getByUsernameAndPassword(@RequestBody UserDTO userToLogin) throws UserNotFoundException {

		try {

			
			User user = userService.getByUsernameAndPassword(userToLogin.getUsername(), userToLogin.getPassword());
						
			UserDTO userDTO = new UserDTO();
			userDTO = modelMapper.map(user, UserDTO.class);
			
			return userDTO;

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserNotFoundException();
		}

	}
}
