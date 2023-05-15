package com.examen.app.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoUpdRequestModel implements Serializable{

	private Integer id;
	
	private String nombre;

	private BigDecimal precio;

	private Integer cantidad;
}
