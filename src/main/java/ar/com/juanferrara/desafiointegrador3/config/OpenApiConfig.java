package ar.com.juanferrara.desafiointegrador3.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        description = "Token JWT que permite la autorizacion al acceso a los recursos. Este puede ser solicitado al iniciar sesion desde el endpoint de autentitacion.",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
