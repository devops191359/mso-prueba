package com.examen.app.security.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examen.app.common.Constantes;
import com.examen.app.common.Util;
import com.examen.app.exceptionhandler.model.ResponseService;
import com.examen.app.exceptionhandler.model.SEExceptionAPI;
import com.examen.app.security.config.JwtTokenProvider;
import com.examen.app.security.model.JwtUser;
import com.examen.app.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UsuarioRestController {

	@Value("${jwt.header}")
	private String tokenHeader;

	private final JwtTokenProvider jwtTokenUtil;

	private final CustomUserDetailsService userDetailsService;

	private final Util util;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/user")
	public Object getAuthenticatedUser(HttpServletRequest request) {

		try {
			String token = jwtTokenUtil.resolveToken(request);
			String username = jwtTokenUtil.getUsername(token);
			JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
			return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.OPERACION_200, util.getFolio(),
					user);
		} catch (SEExceptionAPI seExceptionAPI) {
			throw seExceptionAPI;

		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}

	}

}
