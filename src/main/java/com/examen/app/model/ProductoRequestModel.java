package com.examen.app.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestModel implements Serializable {

	
	private String nombre;

	private BigDecimal precio;

	private Integer cantidad;

}
