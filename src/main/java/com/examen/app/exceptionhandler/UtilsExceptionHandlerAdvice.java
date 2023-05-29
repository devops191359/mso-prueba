package com.examen.app.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.examen.app.common.*;
import com.examen.app.exceptionhandler.model.*;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class UtilsExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	private final Util util;

	@Value("${info.app.response}")
	private String info;

	@ExceptionHandler(SEExceptionAPI.class)
	protected ResponseEntity<ResponseError> handleSEExceptionAPI(SEExceptionAPI e, final WebRequest request) {
		return new ResponseEntity<>(new ResponseError(util.getFormatoCodex(String.valueOf(e.getStatus().value())),
				e.getMensaje(), e.getFolio(), info.concat(util.getFormatoCodex(String.valueOf(e.getStatus().value()))),
				e.getDetalles()), e.getStatus());

	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ResponseError> handleAuthenticationException(AuthenticationException ex,
			HttpServletResponse response) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return new ResponseEntity<>(new ResponseError(util.getFormatoCodex(String.valueOf(status.value())),
				Constantes.OPERACION_401, util.getFolio(),
				info.concat(util.getFormatoCodex(String.valueOf(status.value()))), Arrays.asList(ex.getMessage())),
				status);
	}
	

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.getFolio(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseError> handleAllUncaughtException(Exception exception, final WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())),
						Constantes.OPERACION_500, util.getFolio(),
						info.concat(util.getFormatoCodex(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.getFolio(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);
	}

	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.getFolio(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ResponseError> handleNullFormat(NullPointerException ex, final WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.getFolio(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ResponseError> handleNumberFormat(NumberFormatException ex, final WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.getFolio(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<ResponseError> handleMissingParams(MissingRequestHeaderException ex,
			final WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.getFolio(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.getFolio(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		List<ErrorDescription> listaErrores = new ArrayList<>();
		for (FieldError error : errors) {
			listaErrores.add(new ErrorDescription(error.getField(), Arrays.asList(error.getDefaultMessage())));
		}
		listaErrores = listaErrores.stream().distinct().collect(Collectors.toList());
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.getFolio(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)), listaErrores),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseError> handleConstraintValidation(ConstraintViolationException ex,
			final WebRequest request) {
		Set<ConstraintViolation<?>> listaConstraints = ex.getConstraintViolations();
		List<String> listaErrores = new ArrayList<>();
		for (ConstraintViolation<?> constraintViolation : listaConstraints) {
			listaErrores.add(constraintViolation.getMessage());
		}
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.getFolio(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)), listaErrores),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ErrorDescription> listaErrores = new ArrayList<>();
		listaErrores.add(new ErrorDescription(ex.getPropertyName(), Arrays.asList(ex.getMessage())));
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.getFolio(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)), listaErrores),
				HttpStatus.BAD_REQUEST);
	}

}
