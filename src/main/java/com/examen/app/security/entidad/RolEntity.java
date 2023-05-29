package com.examen.app.security.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TA_ROL")
public class RolEntity {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME", nullable = false, length = 50, unique = true)
	private String name;

	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private List<UsuarioEntity> users;

	public RolEntity(String name) {
		super();
		this.name = name;
	}

	public RolEntity(Integer id) {
		super();
		this.id = id;
	}

}