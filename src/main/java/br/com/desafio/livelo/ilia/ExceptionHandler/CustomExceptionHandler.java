package br.com.desafio.livelo.ilia.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String systemMessage = messageSource.getMessage("error.message", null, LocaleContextHolder.getLocale());
		String detailedSystemMessage = ex.getCause().toString();
		
		return handleExceptionInternal(ex, new Error(systemMessage, detailedSystemMessage), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Error> errors = createErrorList(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String systemMessage = messageSource.getMessage("resource.notFound", null, LocaleContextHolder.getLocale());
		String detailedSystemMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(systemMessage, detailedSystemMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	private List<Error> createErrorList(BindingResult bindingResult){
		List<Error> errors = new ArrayList<>();
		
		for (FieldError fielError : bindingResult.getFieldErrors()) {
			String systemMessage = messageSource.getMessage(fielError, LocaleContextHolder.getLocale());
			String detailedSystemMessage = fielError.toString();
			errors.add(new Error(systemMessage, detailedSystemMessage));
		}
		
		return errors;
	}
	
	/**
	 * Inner Class for error messages.
	 * @author bruno
	 */
	public static class Error {
		private String systemMessage;
		private String detailedSystemMessage;

		public Error(String systemMessage, String detailedSystemMessage) {
			super();
			this.systemMessage = systemMessage;
			this.detailedSystemMessage = detailedSystemMessage;
		}

		public String getSystemMessage() {
			return systemMessage;
		}

		public void setSystemMessage(String systemMessage) {
			this.systemMessage = systemMessage;
		}

		public String getDetailedSystemMessage() {
			return detailedSystemMessage;
		}

		public void setDetailedSystemMessage(String detailedSystemMessage) {
			this.detailedSystemMessage = detailedSystemMessage;
		}
		
	}
	
}
