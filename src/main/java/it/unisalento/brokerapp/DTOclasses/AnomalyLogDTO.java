package it.unisalento.brokerapp.DTOclasses;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AnomalyLogDTO {

	Integer id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Berlin")
	LocalDateTime date;
	
	Integer vector;
	
	String action;
	
	String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getVector() {
		return vector;
	}

	public void setVector(Integer vector) {
		this.vector = vector;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
