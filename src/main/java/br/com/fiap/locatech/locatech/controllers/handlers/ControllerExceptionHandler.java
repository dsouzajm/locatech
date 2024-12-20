package br.com.fiap.locatech.locatech.controllers.handlers;

import br.com.fiap.locatech.locatech.dtos.ResourceNotFoundDTO;
import br.com.fiap.locatech.locatech.dtos.ValidationErrorDTO;
import br.com.fiap.locatech.locatech.excepetions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException e){
        HttpStatus statusNotFound = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(statusNotFound.value()).body(new ResourceNotFoundDTO(e.getMessage(), statusNotFound.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerMethodArgumentNotFoundException(MethodArgumentNotValidException e){
        HttpStatus statusBadRequest = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<String>();
        for(var error: e.getBindingResult().getFieldErrors()){
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity.status(statusBadRequest.value()).body(new ValidationErrorDTO(errors, statusBadRequest.value()));
    }
}
