package com.examen.app.exceptionhandler.model;

import java.util.List;

import lombok.Data;

@Data
public class ErrorDescription {
	private String propiedad;

	private List<String> error;

	public ErrorDescription(String propiedad, List<String> error) {
		super();
		this.propiedad = propiedad;
		this.error = error;
	}

}
