package dev.trajano.gym.core.exception.handler;

import dev.trajano.gym.core.exception.AlreadyExistsException;
import dev.trajano.gym.core.exception.BusinessException;
import dev.trajano.gym.core.exception.InvalidTokenException;
import dev.trajano.gym.core.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        return build(HttpStatus.BAD_REQUEST, "Business Error", resolveMessage(ex.getMessage()));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException ex) {
        return build(HttpStatus.UNAUTHORIZED, "Unauthorized", resolveMessage(ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, "Not Found", resolveMessage(ex.getMessage()));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException ex) {
        return build(HttpStatus.CONFLICT, "Conflict", resolveMessage(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + (error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value")).toList();
        return build(HttpStatus.BAD_REQUEST, "Validation Error", errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        log.error("Unhandled exception caught by GlobalExceptionHandler: ", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", List.of("An unexpected internal error occurred. Please contact support."));
    }

    private List<String> resolveMessage(String message) {
        return List.of(message != null ? message : "No details provided");
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String typeError, List<String> messages) {
        ErrorResponse error = new ErrorResponse(Instant.now(), status.value(), typeError, messages);
        return ResponseEntity.status(status).body(error);
    }
}