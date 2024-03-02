package ar.com.juanferrara.desafiointegrador3.business.dto;

import org.springframework.http.HttpStatus;

public record ExceptionDto(Integer statusCode, String message) {
}
