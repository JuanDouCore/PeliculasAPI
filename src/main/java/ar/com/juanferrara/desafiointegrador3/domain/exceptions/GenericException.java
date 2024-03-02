package ar.com.juanferrara.desafiointegrador3.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.http.HttpResponse;

@Getter
public class GenericException extends RuntimeException {

    private HttpStatus status;

    public GenericException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
