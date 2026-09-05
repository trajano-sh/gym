package dev.trajano.mastersys.exception.handler;

import dev.trajano.mastersys.exception.AlreadyExistsException;
import dev.trajano.mastersys.exception.BusinessException;
import dev.trajano.mastersys.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        return build(HttpStatus.BAD_REQUEST, "Error de service", List.of(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return build(HttpStatus.BAD_REQUEST, "Bad Request", ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).toList());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException ex) {
        return build(HttpStatus.BAD_REQUEST, "Bad Request", List.of(ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, "Not Found", List.of(ex.getMessage()));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException ex) {
        return build(HttpStatus.CONFLICT, "Conflict", List.of(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", List.of(ex.getMessage()));
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String typeError, List<String> messages) {
        ErrorResponse error = new ErrorResponse(Instant.now(), status.value(), typeError, messages);
        return ResponseEntity.status(status).body(error);
    }
}
