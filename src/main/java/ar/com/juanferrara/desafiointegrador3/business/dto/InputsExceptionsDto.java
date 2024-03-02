package ar.com.juanferrara.desafiointegrador3.business.dto;

import java.util.Map;

public record InputsExceptionsDto(int code, String error, Map valueError) {
}
