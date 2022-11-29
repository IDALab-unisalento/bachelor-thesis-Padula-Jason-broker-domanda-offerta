package it.unisalento.brokerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Error: can't save anomaly")
public class SavingAnomalyException extends Exception {

}
