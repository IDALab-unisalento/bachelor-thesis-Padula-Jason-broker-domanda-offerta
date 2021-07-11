package it.unisalento.brokerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "C'è stato qualche errore: viaggio non aggiunto")
public class SavingViaggioException  extends Exception{

}
