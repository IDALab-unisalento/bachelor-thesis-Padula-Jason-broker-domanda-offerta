package it.unisalento.brokerapp.domainClasses;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Administrator extends User {
	
	public Administrator() {}

}
