package com.examen.app.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.app.security.entidad.UsuarioEntity;

public interface UserRepository extends JpaRepository<UsuarioEntity, Long> {

	Optional<UsuarioEntity> findByUsername(String username);
}
