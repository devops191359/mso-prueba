package com.examen.app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3616495503037160151L;
	private Integer idEstatus;
	private String estatus;
}
