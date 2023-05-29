package com.examen.app.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.app.security.entidad.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Integer> {
 
}