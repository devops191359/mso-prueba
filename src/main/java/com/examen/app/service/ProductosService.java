package com.examen.app.service;

import java.util.List;

import com.examen.app.entidad.ProductosEntity;
import com.examen.app.model.ProductoRequestModel;
import com.examen.app.model.ProductoResponseModel;
import com.examen.app.model.ProductoUpdRequestModel;

public interface ProductosService {

	public List<ProductosEntity> getProductos();

	public Object saveProducto(ProductoRequestModel request);

	public Object getProductoById(Integer idProducto);

	public Object setProducto(ProductoUpdRequestModel request);

	public Object deleteProducto(Integer idProducto);

	public Object deleteProducto(ProductoUpdRequestModel request);
}
