package com.examen.app.exceptionhandler.model;

import java.util.List;

import lombok.Data;

@Data
public class ErrorRespModel {
	private String codigo;
	private String mensaje;
	private String folio;
	private String info;
	private List<String> detalles;
}
