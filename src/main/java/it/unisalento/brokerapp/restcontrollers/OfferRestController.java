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

import it.unisalento.brokerapp.DTOclasses.OfferDTO;
import it.unisalento.brokerapp.domainClasses.Offer;
import it.unisalento.brokerapp.exceptions.CompanyNotFoundException;
import it.unisalento.brokerapp.exceptions.OfferNotFoundException;
import it.unisalento.brokerapp.exceptions.SavingOfferException;
import it.unisalento.brokerapp.iservices.ICompanyService;
import it.unisalento.brokerapp.iservices.IOfferService;


@RestController
@RequestMapping("/offer")
public class OfferRestController {

	@Autowired
	IOfferService offerService;
	
	@Autowired
	ICompanyService companyService;

	private ModelMapper modelMapper = new ModelMapper();

//	RICERCO UN ALLEGATO PER ID
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public OfferDTO get(@PathVariable int id) throws OfferNotFoundException{
		
		Offer offer = offerService.getById(id);
		
		// Abbiamo un oggetto IUserDTO da mappare in un oggetto userDTO
		OfferDTO offerDTO = modelMapper.map(offer, OfferDTO.class);
		offerDTO.setCompanyId(offer.getCompany().getId());

		
		return offerDTO;
	}
	
	
//	RICERCO TUTTI GLI ALLEGATI
	@RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OfferDTO> getAll(){
		
		List<Offer> 	offerList  = offerService.getAll();
		List<OfferDTO>  offerDTOList = new ArrayList<OfferDTO>(); 
		
		for (Offer offer : offerList) {
			
			OfferDTO offerDTO = modelMapper.map(offer, OfferDTO.class);
			offerDTO.setCompanyId(offer.getCompany().getId());

			offerDTOList.add(offerDTO);
		}
		
		
		return offerDTOList;
	}

	
//	SALVO UN ALLEGATO
	@PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public OfferDTO save(@RequestBody @Valid OfferDTO offerDTO) throws SavingOfferException, CompanyNotFoundException {
		
		
		Offer  offer = new Offer();
		
		offer = modelMapper.map(offerDTO, Offer.class);
		offer.setCompany(companyService.getById(offerDTO.getCompanyId()) );
		
		Offer offerSaved = offerService.save(offer);	
		offerDTO.setId(offerSaved.getId());
		
		return offerDTO;
		
	}
	
    

//	RIMUOVO UN ALLEGATO
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<Offer> delete(@PathVariable int id ) throws OfferNotFoundException{
		
		offerService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
