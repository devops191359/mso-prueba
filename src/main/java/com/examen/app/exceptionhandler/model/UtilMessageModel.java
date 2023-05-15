package com.examen.app.exceptionhandler.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UtilMessageModel implements Serializable {
	
	private String propiedad;
	
	private String mensaje;
}
