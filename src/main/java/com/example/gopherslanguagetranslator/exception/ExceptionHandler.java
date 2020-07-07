package com.example.gopherslanguagetranslator.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers,
                                                             final HttpStatus status, final WebRequest request) {
    return ExceptionHandler.constructResponseEntity(ex.getBindingResult(), headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status,
                                                       final WebRequest request) {
    return ExceptionHandler.constructResponseEntity(ex.getBindingResult(), headers, status, request);
  }

  private static ResponseEntity<Object> constructResponseEntity(final BindingResult bindingResult, final HttpHeaders headers, final HttpStatus status,
                                                                final WebRequest request) {
    final List<String> messages = bindingResult.getAllErrors()
                                               .stream()
                                               .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                               .collect(Collectors.toList());

    final ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), messages, request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, headers, status);
  }
}
