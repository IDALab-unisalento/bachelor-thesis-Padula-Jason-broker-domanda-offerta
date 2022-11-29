package it.unisalento.brokerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Vector not found") // come impostare il code di response HTTP
public class VectorNotFoundException extends Exception{

}
