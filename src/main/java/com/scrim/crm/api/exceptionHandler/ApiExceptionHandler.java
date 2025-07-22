package com.scrim.crm.api.exceptionHandler;

import com.scrim.crm.domain.exception.SystemException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatusCode statusCode,
                                                                WebRequest request) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(statusCode);
    problemDetail.setTitle("Invalid fields.");

    Map<String, String> fields = ex.getBindingResult().getAllErrors()
      .stream()
      .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
        objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));

    problemDetail.setProperty("fields", fields);

    return handleExceptionInternal(ex, problemDetail, headers, statusCode, request);
  }

  @ExceptionHandler(SystemException.class)
  public ProblemDetail handleSystem(SystemException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    problemDetail.setTitle(e.getMessage());

    return problemDetail;
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ProblemDetail handleDataIntegrity(DataIntegrityViolationException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    problemDetail.setTitle("Resource in use");

    return problemDetail;
  }

}
