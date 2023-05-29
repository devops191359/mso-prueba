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
public class UsuarioResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8070951350310027962L;
	private Integer idUsuario;
	private String username;
	private String nombreCompleto;
	private List<String> roles;

}
