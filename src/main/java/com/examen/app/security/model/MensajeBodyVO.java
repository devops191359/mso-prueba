package com.examen.app.security.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeBodyVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String propiedad;

	private List<String> error;
}
