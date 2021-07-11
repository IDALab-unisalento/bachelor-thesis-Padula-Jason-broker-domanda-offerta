package it.unisalento.brokerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "C'è stato qualche errore: legame offerta-tratte non aggiunto")
public class SavingViaggioRouteException  extends Exception{

}
