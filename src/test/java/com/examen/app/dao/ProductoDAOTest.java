package com.examen.app.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.examen.app.entidad.ProductosEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ProductoDAOTest {
	
	@Autowired
	private ProductosDAO productosDAO;

	@Test
	public void testGetProductos() {
		List<ProductosEntity> productos=this.productosDAO.findAll();
		if (productos.isEmpty()) {
			log.info("si hay productos");
		}else {
			log.info("no hay productos");
		}
		Assert.assertNotNull(productos);
	}
	

	@Test
	public void testSetProductos() {
		
		ProductosEntity producto=new ProductosEntity();
		producto.setNombre("TEST 1234");
		producto.setPrecio(new BigDecimal(120));
		producto.setStock(2);
		producto=this.productosDAO.saveAndFlush(producto);
		if (producto != null) {
			log.info("se registro el producto");
		}
		Assert.assertNotNull(producto);
	}
	
	@Test
	public void testUpdProductos() {
		
		ProductosEntity producto=new ProductosEntity();
		producto.setId(1);
		producto.setNombre("TEST 1234");
		producto.setPrecio(new BigDecimal(120));
		producto.setStock(2);
		producto=this.productosDAO.saveAndFlush(producto);
		if (producto != null) {
			log.info("se actualizo el producto");
		}
		Assert.assertNotNull(producto);
	}
	
	
	@Test
	public void testDeleteProductos() {
		ProductosEntity producto=new ProductosEntity();
		producto.setId(1);
		producto.setNombre("TEST 1234");
		producto.setPrecio(new BigDecimal(120));
		producto.setStock(2);
		this.productosDAO.delete(producto);
	}
	
}
