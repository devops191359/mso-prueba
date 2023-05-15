package com.examen.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.app.entidad.ProductosEntity;

public interface ProductosDAO extends JpaRepository<ProductosEntity, Integer> {

}
