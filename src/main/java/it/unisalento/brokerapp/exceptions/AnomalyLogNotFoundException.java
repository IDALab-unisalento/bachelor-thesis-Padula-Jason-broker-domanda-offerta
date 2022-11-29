package it.unisalento.brokerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "AnomalyLog records not found or not valid input")
public class AnomalyLogNotFoundException extends Exception {

}
