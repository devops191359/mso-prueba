package com.examen.app.health.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.examen.app.common.Constantes;
import com.examen.app.common.Util;
import com.examen.app.exceptionhandler.model.ResponseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusRestController {

	private final Util util;

	@Value("${info.app.name}")
	private String nombreAplicacion;

	@Value("${info.app.version}")
	private String appVersion;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseService getStatus() {
		Map<String, String> status = Map.of("aplicacion", nombreAplicacion, "appVersion", appVersion);
		return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.OPERACION_200, util.getFolio(), status);
	}

}