
package it.unisalento.brokerapp.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.Administrator;
import it.unisalento.brokerapp.exceptions.SavingUserException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.iservices.IAdministratorService;
import it.unisalento.brokerapp.repositories.AdministratorRepository;
import it.unisalento.brokerapp.repositories.UserRepository;


@Service
public class AdministratorServiceImpl implements IAdministratorService {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional(rollbackOn = SavingUserException.class)
	public Administrator save(Administrator administrator) throws SavingUserException {
		try {
			return administratorRepository.save(administrator); // l' id se lo crea da solo il db perchè abbiamo
																// taggato con @Id
		} catch (Exception e) {
			// TODO: handle exception
			throw new SavingUserException();
		}
	}

	@Override
	@Transactional(rollbackOn = UserNotFoundException.class)
	public Administrator getById(int id) throws UserNotFoundException {
		return administratorRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

	}

	@Override
	public List<Administrator> getAll() {
		return administratorRepository.findAll();

	}

	@Override
	@Transactional(rollbackOn = UserNotFoundException.class)
	public boolean delete(int id) throws UserNotFoundException,IllegalArgumentException {
		Administrator administrator = administratorRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException());
		administratorRepository.delete(administrator);
		return true;
	}



	@Override
	@Transactional(rollbackOn = UserNotFoundException.class)
	public boolean EnableUserById(int id_User) throws UserNotFoundException {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Date currentDate = calendar.getTime();
		if (administratorRepository.EnableUserById(id_User, currentDate) == 0)
			throw new UserNotFoundException();
		return true;

	}

}