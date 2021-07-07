package it.unisalento.brokerapp.serviceImpl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.brokerapp.domainClasses.Affittuario;
import it.unisalento.brokerapp.exceptions.SavingUserException;
import it.unisalento.brokerapp.exceptions.UserNotFoundException;
import it.unisalento.brokerapp.iservices.IAffittuarioService;
import it.unisalento.brokerapp.repositories.AffittuarioRepository;


@Service
public class AffittuarioServiceImpl implements IAffittuarioService{

	@Autowired
	AffittuarioRepository userRepository;
	
	@Override
	@Transactional(rollbackOn =SavingUserException.class)
	public Affittuario save(Affittuario user) throws SavingUserException{
		
		try {
			return userRepository.save(user);  //l' id se lo crea da solo il db perchè abbiamo taggato con @Id
			}catch (Exception e) {
				// TODO: handle exception
				throw new SavingUserException();
			}
		
		}
	
	
	@Transactional(rollbackOn = UserNotFoundException.class)
	@Override
	public Affittuario getById(int userId) throws UserNotFoundException{
		return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
		
	}
	
	@Transactional
	@Override
	public List<Affittuario> getAll(){
		return userRepository.findAll();
	}
	
	@Override
	@Transactional(rollbackOn = {UserNotFoundException.class, EntityNotFoundException.class})
	public boolean delete(int id) throws UserNotFoundException, IllegalArgumentException {

		Affittuario user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
		userRepository.delete(user);
		return true;
	}

}
