package ar.com.juanferrara.desafiointegrador3;

import ar.com.juanferrara.desafiointegrador3.domain.entity.Usuario;
import ar.com.juanferrara.desafiointegrador3.domain.enums.Rol;
import ar.com.juanferrara.desafiointegrador3.persistence.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Desafiointegrador3Application {

    public static void main(String[] args) {
        SpringApplication.run(Desafiointegrador3Application.class, args);
    }


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Generacion de admin por defecto
    @Value("${admin.password}")
    private String adminPassword;

    @PostConstruct
    public void initAdmin() {
        if(!usuarioRepository.existsByCorreo("admin@admin.com")){
            usuarioRepository.save(Usuario.builder()
                    .correo("admin@admin.com")
                    .contraseña(passwordEncoder.encode(adminPassword))
                    .role(Rol.ADMIN)
                    .build());
        }
    }
}
