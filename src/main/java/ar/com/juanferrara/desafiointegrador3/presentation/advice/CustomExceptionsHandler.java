package ar.com.juanferrara.desafiointegrador3.presentation.advice;

import ar.com.juanferrara.desafiointegrador3.business.dto.ExceptionDto;
import ar.com.juanferrara.desafiointegrador3.domain.exceptions.GenericException;
import ar.com.juanferrara.desafiointegrador3.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionsHandler {

    @ExceptionHandler(value = {GenericException.class})
    @ResponseBody
    public ResponseEntity<ExceptionDto> handleGenericException(GenericException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(new ExceptionDto(exception.getStatus().value(), exception.getMessage()));
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseBody
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDto(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }
}
