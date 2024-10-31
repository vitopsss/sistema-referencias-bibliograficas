package com.aranoua.referencias_bibliograficas.controller.exception;

import com.aranoua.referencias_bibliograficas.model.MensagemException;
import com.aranoua.referencias_bibliograficas.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<MensagemException> handleObjectNotFound(ObjectNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(new MensagemException(HttpStatus.NOT_FOUND.toString(), exception.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MensagemException> handleRuntimeException(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MensagemException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> hendArgumentNotValid(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            if(error instanceof FieldError fieldError){
                String nomeCampo = fieldError.getField();
                String mensagem = fieldError.getDefaultMessage();
                errors.put(nomeCampo, mensagem);
            }
        });
        return ResponseEntity.internalServerError().body(errors);
    }
}
