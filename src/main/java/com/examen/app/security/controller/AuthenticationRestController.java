package com.examen.app.security.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examen.app.common.Constantes;
import com.examen.app.common.Util;
import com.examen.app.exceptionhandler.model.ResponseService;
import com.examen.app.exceptionhandler.model.SEExceptionAPI;
import com.examen.app.security.config.JwtTokenProvider;
import com.examen.app.security.model.AuthenticationRequest;
import com.examen.app.security.model.AuthenticationResponse;
import com.examen.app.security.model.MensajeBodyVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

	private final AuthenticationManager authenticationManager;

	private final JwtTokenProvider jwtTokenProvider;

	private final Util util;

	@PostMapping("/login")
	public Object authenticateUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

		try {

			List<MensajeBodyVO> lisBodyVOs = validaBody(authenticationRequest);

			if (!lisBodyVOs.isEmpty()) {
				throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
						util.getFolio(), lisBodyVOs);
			}

			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtTokenProvider.createToken(authentication);

			return new ResponseService(HttpStatus.CREATED, util.getCodigo(), Constantes.OPERACION_201, util.getFolio(),
					new AuthenticationResponse(jwt));
		} catch (SEExceptionAPI seExceptionAPI) {
			throw seExceptionAPI;

		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}

	}

	public List<MensajeBodyVO> validaBody(AuthenticationRequest authenticationRequest) {

		List<MensajeBodyVO> lisBodyVOs = new ArrayList<>();
		if (authenticationRequest.getUsername().isEmpty()) {
			lisBodyVOs.add(
					new MensajeBodyVO("username", Arrays.asList("El campo username esta vacio, favor de validar")));
		}

		if (authenticationRequest.getPassword().isEmpty()) {
			lisBodyVOs.add(
					new MensajeBodyVO("password", Arrays.asList("El campo password esta vacio, favor de validar")));
		}
		return lisBodyVOs;
	}

	@GetMapping("/logout")
	public Object logoutUser() {

		try {
			SecurityContextHolder.clearContext();
			return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.OPERACION_200, util.getFolio(),
					"Logout successful");
		} catch (SEExceptionAPI seExceptionAPI) {
			throw seExceptionAPI;

		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}

	}

}
