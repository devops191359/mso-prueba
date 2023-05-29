package com.examen.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.examen.app.entidad.ProductosEntity;

public interface ProductosDAO extends JpaRepository<ProductosEntity, Integer>, CrudRepository<ProductosEntity, Integer>,
		PagingAndSortingRepository<ProductosEntity, Integer> {

}
