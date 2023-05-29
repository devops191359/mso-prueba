package com.examen.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.examen.app.common.Constantes;
import com.examen.app.common.Util;
import com.examen.app.dao.ProductosDAO;
import com.examen.app.entidad.ProductosEntity;
import com.examen.app.exceptionhandler.model.SEExceptionAPI;
import com.examen.app.model.ProductoRequestModel;
import com.examen.app.model.ProductoResponseModel;
import com.examen.app.model.ProductoUpdRequestModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductosServiceImpl implements ProductosService {

	private final ProductosDAO productosDAO;

	private final Util util;

	public ProductosServiceImpl(final ProductosDAO productosDAO, final Util util) {
		super();
		this.productosDAO = productosDAO;
		this.util = util;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	@Override
	public List<ProductosEntity> getProductos() {
		log.info("Obteniendo productos");

		List<ProductosEntity> productos = productosDAO.findAll();

		if (productos.isEmpty()) {
			throw new SEExceptionAPI(HttpStatus.NOT_FOUND, util.getCodigo(), Constantes.OPERACION_404, util.getFolio(),
					Arrays.asList("No se encontro información de los productos"));
		}

		return productos;
	}

	@Transactional
	@Override
	public Object saveProducto(ProductoRequestModel request) {
		try {
			ProductosEntity productosEntity = new ProductosEntity();
			productosEntity.setNombre(request.getNombre());
			productosEntity.setPrecio(request.getPrecio());
			productosEntity.setStock(request.getCantidad());
			productosEntity = this.productosDAO.saveAndFlush(productosEntity);

			return new ProductoResponseModel(1,
					"El producto " + productosEntity.getId() + " se registro correctamente");
		} catch (Exception ex) {
			throw new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(), Constantes.OPERACION_500,
					util.getFolio(), Arrays.asList(Constantes.OPERACION_500));
		}
	}

	@Transactional
	@Override
	public Object setProducto(ProductoUpdRequestModel request) {
		// TODO Auto-generated method stub

		if (productosDAO.existsById(request.getId())) {
			ProductosEntity productosEntity = new ProductosEntity();
			productosEntity.setId(request.getId());
			productosEntity.setNombre(request.getNombre());
			productosEntity.setPrecio(request.getPrecio());
			productosEntity.setStock(request.getStock());
			productosEntity=this.productosDAO.saveAndFlush(productosEntity);
			ProductoResponseModel productoResponseModel = new ProductoResponseModel();
			productoResponseModel.setEstatus("El producto " + productosEntity.getId() + " se actualizo correctamente");
			productoResponseModel.setIdEstatus(2);
			return productoResponseModel;
		} else {
			throw new SEExceptionAPI(HttpStatus.NOT_FOUND, util.getCodigo(), Constantes.OPERACION_404, util.getFolio(),
					Arrays.asList("El producto que intenta modificar no existe"));
		}

	}

	@Override
	public Object deleteProducto(Integer idProducto) {
		// TODO Auto-generated method stub

		if (productosDAO.existsById(idProducto)) {
			this.productosDAO.deleteById(idProducto);
			ProductoResponseModel productoResponseModel = new ProductoResponseModel();
			productoResponseModel.setEstatus("El producto " + idProducto + " ha sido dado de baja");
			productoResponseModel.setIdEstatus(3);
			return productoResponseModel;
		} else {
			throw new SEExceptionAPI(HttpStatus.NOT_FOUND, util.getCodigo(), Constantes.OPERACION_404, util.getFolio(),
					Arrays.asList("El producto que intenta eliminar no existe"));
		}

	}

	@Override
	public Object getProductoById(Integer idProducto) {
		// TODO Auto-generated method stub
		return productosDAO.findById(idProducto).get();
	}

	@Override
	public Object deleteProducto(ProductoUpdRequestModel request) {
		// TODO Auto-generated method stub

		if (productosDAO.existsById(request.getId())) {
			ProductosEntity productosEntity = new ProductosEntity();
			productosEntity.setId(request.getId());
			productosEntity.setNombre(request.getNombre());
			productosEntity.setPrecio(request.getPrecio());
			productosEntity.setStock(request.getStock());
			this.productosDAO.delete(productosEntity);
			ProductoResponseModel productoResponseModel = new ProductoResponseModel();
			productoResponseModel.setEstatus("El producto " + request.getId() + " ha sido dado de baja");
			productoResponseModel.setIdEstatus(3);
			return productoResponseModel;
		} else {
			throw new SEExceptionAPI(HttpStatus.NOT_FOUND, util.getCodigo(), Constantes.OPERACION_404, util.getFolio(),
					Arrays.asList("El producto que intenta eliminar no existe"));
		}

	}

}
