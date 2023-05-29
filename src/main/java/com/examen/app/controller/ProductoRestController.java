package com.examen.app.controller;

import java.util.Arrays;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examen.app.common.Constantes;
import com.examen.app.common.Util;
import com.examen.app.exceptionhandler.model.ResponseService;
import com.examen.app.exceptionhandler.model.SEExceptionAPI;
import com.examen.app.model.ProductoRequestModel;
import com.examen.app.model.ProductoUpdRequestModel;
import com.examen.app.service.ProductosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/procesos")
@RequiredArgsConstructor
public class ProductoRestController {

	private final Util util;

	private final ProductosService productosService;

	@PostMapping("/productos")
	public Object saveProducto(@RequestBody @Valid ProductoRequestModel productoRequestModel) {
		try {
			Object o = productosService.saveProducto(productoRequestModel);
			return new ResponseService(HttpStatus.CREATED, util.getCodigo(), Constantes.OPERACION_201, util.getFolio(),
					o);
		} catch (SEExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}

	}

	@GetMapping("/productos/busquedas")
	public Object getProductos() {
		try {
			Object o = this.productosService.getProductos();
			return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.OPERACION_200, util.getFolio(), o);
		} catch (SEExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}

	}

	@GetMapping("/productos/detalles/busquedas/{idProducto}")
	public Object getProductosByID(@PathVariable(name = "idProducto", required = true) Integer idProducto) {
		try {
			Object o = this.productosService.getProductoById(idProducto);
			return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.OPERACION_200, util.getFolio(), o);
		} catch (SEExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}

	}

	@PutMapping("/productos")
	public Object updateProducto(@RequestBody @Valid ProductoUpdRequestModel productoRequestModel) {
		try {
			Object o = productosService.setProducto(productoRequestModel);
			return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.OPERACION_200, util.getFolio(), o);
		} catch (SEExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}

	}

	@DeleteMapping("/productos/bajas/{idProducto}")
	public Object deleteProducto(@PathVariable(name = "idProducto", required = true) Integer idProducto) {
		try {
			Object o = productosService.deleteProducto(idProducto);
			return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.OPERACION_200, util.getFolio(), o);
		} catch (SEExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}

	}

	@DeleteMapping("/productos/bajas")
	public Object deleteProducto(@Valid @RequestBody ProductoUpdRequestModel productoUpdRequestModel) {
		try {
			Object o = productosService.deleteProducto(productoUpdRequestModel);
			return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.OPERACION_200, util.getFolio(), o);
		} catch (SEExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}

	}
}
