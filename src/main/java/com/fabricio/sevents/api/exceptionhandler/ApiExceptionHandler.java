package com.fabricio.sevents.api.exceptionhandler;

import com.fabricio.sevents.api.exceptionhandler.errors.NotFoundAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_ERRO_GENERICO = "Ocorreu um erro interno inesperado no sistema. Tente novamente e"
			+ " se o problema persistir, entre em contato com o administrador do sistema.";
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
	    List<Error.Field> errorFields = ex.getConstraintViolations().stream()
	            .map(constraintViolation -> {
	                String fieldName = constraintViolation.getPropertyPath().toString();
	                String message = constraintViolation.getMessage();
	                return Error.Field.builder()
	                        .name(fieldName)
	                        .userMessage(message)
	                        .build();
	            })
	            .collect(Collectors.toList());

	    Error error = Error.builder()
	            .timestamp(OffsetDateTime.now())
	            .status(HttpStatus.BAD_REQUEST.value())
	            .type("https://gitlab.com/behoh/bevent/dados-invalidos")
	            .title("Dados inválidos")
	            .detail("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	            .userMessage("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	            .fields(errorFields)
	            .build();

	    return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

//	@Override
//	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//		String paramName = ex.getParameterName();
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(HttpStatus.BAD_REQUEST.value())
//				.type("https://gitlab.com/behoh/bevent/dados-invalidos")
//				.title("Dados inválidos")
//				.detail("O parâmetro '" + paramName + "' é obrigatório. Preencha corretamente e tente novamente.")
//				.userMessage("O parâmetro '" + paramName + "' é obrigatório. Preencha corretamente e tente novamente.")
//				.build();
//
//		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//	}
//
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();

		List<Error.Field> errorFields = bindFieldErrors(bindingResult);

		Error error = Error.builder()
				.timestamp(OffsetDateTime.now())
				.status(status.value())
				.type("https://gitlab.com/behoh/besx/invalid-data")
				.title("Dados inválidos")
				.detail("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
				.userMessage("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
				.fields(errorFields)
				.build();

		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}


	@ExceptionHandler(NotFoundAlertException.class)
	public ResponseEntity<?> handleNotFoundAlertException(NotFoundAlertException ex, WebRequest request){

		HttpStatus status = HttpStatus.NOT_FOUND;

		Error error = Error.builder()
				.timestamp(OffsetDateTime.now())
				.status(status.value())
				.type("https://gitlab.com/behoh/beevent/not-found")
				.title("Entidade não encontrada")
				.detail(ex.getMessage())
				.build();

		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Error error = Error.builder()
				.timestamp(OffsetDateTime.now())
				.status(status.value())
				.type("https://gitlab.com/behoh/besx/illegal-argument")
				.title("Illegal Argument Exception")
				.detail(ex.getMessage())
				.build();
		
		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}
	
//	@ExceptionHandler(PhysicalPersonNotFoundException.class)
//	public ResponseEntity<?> handlePhysicalPersonNotFoundException(PhysicalPersonNotFoundException ex, WebRequest request){
//
//		HttpStatus status = HttpStatus.NOT_FOUND;
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(status.value())
//				.type("https://gitlab.com/behoh/besx/physical-person-not-found")
//				.title("Physical Person not found")
//				.detail(ex.getMessage())
//				.build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
//
//	@ExceptionHandler(SasEventNotFoundException.class)
//	public ResponseEntity<?> handlePhysicalPersonNotFoundException(SasEventNotFoundException ex, WebRequest request){
//
//		HttpStatus status = HttpStatus.NOT_FOUND;
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(status.value())
//				.type("https://gitlab.com/behoh/besx/sas-event-not-found")
//				.title("Sas Event not found")
//				.detail(ex.getMessage())
//				.build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
//
//	@ExceptionHandler(QrCodeNotFoundException.class)
//	public ResponseEntity<?> handleQrCodeNotFoundException(QrCodeNotFoundException ex, WebRequest request){
//
//		HttpStatus status = HttpStatus.NOT_FOUND;
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(status.value())
//				.type("https://gitlab.com/behoh/besx/qr-code-not-found")
//				.title("QrCode Not Found")
//				.detail(ex.getMessage())
//				.build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
//
//	@ExceptionHandler(QrCodeAlreadyExistsException.class)
//	public ResponseEntity<?> handleQrCodeNotFoundException(QrCodeAlreadyExistsException ex, WebRequest request){
//
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(status.value())
//				.type("https://gitlab.com/behoh/besx/qr-code-already-exists")
//				.title("QrCode Already Exists")
//				.detail(ex.getMessage())
//				.build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
//
//	@ExceptionHandler(RegistrationNotFoundException.class)
//	public ResponseEntity<?> handleRegistrationNotFoundException(RegistrationNotFoundException ex, WebRequest request){
//
//		HttpStatus status = HttpStatus.NOT_FOUND;
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(status.value())
//				.type("https://gitlab.com/behoh/besx/qr-code-already-exists")
//				.title("Registration Not Found")
//				.detail(ex.getMessage())
//				.build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
//
//	@ExceptionHandler(RegistrationAlreadyExistsException.class)
//	public ResponseEntity<?> handleRegistrationAlreadyExistsException(RegistrationAlreadyExistsException ex, WebRequest request){
//
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(status.value())
//				.type("https://gitlab.com/behoh/besx/registration-already-exists")
//				.title("Registration Already Exists")
//				.detail(ex.getMessage())
//				.build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
//
//	@ExceptionHandler(InvalidTokenException.class)
//	public ResponseEntity<?> handleInvalidTokenException(InvalidTokenException ex, WebRequest request){
//
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(status.value())
//				.type("https://gitlab.com/behoh/besx/invalid-token")
//				.title("Token inválido")
//				.detail(ex.getMessage())
//				.build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
//
//	@ExceptionHandler(InvalidPasswordException.class)
//	public ResponseEntity<?> handleInvalidPasswordException(InvalidPasswordException ex, WebRequest request){
//
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(status.value())
//				.type("https://gitlab.com/behoh/besx/invalid-password")
//				.title("Senha inválida")
//				.detail(ex.getMessage())
//				.build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
//
//	@ExceptionHandler(SasGenericException.class)
//	public ResponseEntity<?> handleSasGenericException(SasGenericException ex, WebRequest request){
//
//		HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(status.value())
//				.type("https://gitlab.com/behoh/besx/sas-generic-exception")
//				.title("Sas generic exception")
//				.detail(ex.getMessage())
//				.build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
//
//	@ExceptionHandler({
//		AlreadyHaveCompanyException.class,
//		PhysicalPersonAlreadyExistsException.class})
//	public ResponseEntity<?> handleAlreadyHaveCompanyException(Exception ex, WebRequest request){
//
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(status.value())
//				.type("https://gitlab.com/behoh/besx/resource-already-exists")
//				.title("Recurso já existe")
//				.detail(ex.getMessage())
//				.build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
//
//	@ExceptionHandler({ReportException.class,})
//	public ResponseEntity<?> handleReportException(ReportException ex, WebRequest request){
//
//		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//
//		Error error = Error.builder()
//				.timestamp(OffsetDateTime.now())
//				.status(status.value())
//				.type("https://gitlab.com/behoh/besx/report")
//				.title("Não foi possivel gerar relatório")
//				.detail(ex.getMessage())
//				.build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
//
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request){
//		HttpStatus status = HttpStatus.NOT_FOUND;
//
//	    Error error = Error.builder()
//	            .timestamp(OffsetDateTime.now())
//	            .status(status.value())
//	            .type("https://gitlab.com/behoh/besx/" + ex.getType())
//	            .title(ex.getTitle())
//	            .detail(ex.getMessage())
//	            .build();
//
//		return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
//	}
	
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = Error.builder()
					.timestamp(OffsetDateTime.now())
					.title(status.getReasonPhrase())
					.status(status.value())
					.userMessage(MSG_ERRO_GENERICO)
					.build();

		} else if (body instanceof String) {
			body = Error.builder()
					.timestamp(OffsetDateTime.now())
					.title((String) body)
					.status(status.value())
					.userMessage(MSG_ERRO_GENERICO)
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private List<Error.Field> bindFieldErrors(BindingResult bindingResult) {
		return bindingResult.getFieldErrors().stream()
				.map(fieldError -> {
					String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
					return Error.Field.builder()
							.name(fieldError.getField())
							.userMessage(message)
							.build();
				})
				.collect(Collectors.toList());
	}
}
