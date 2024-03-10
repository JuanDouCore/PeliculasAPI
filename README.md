# PeliculasAPI

Este proyecto consta de una API Rest que permite tener un sistema gestionable de peliculas.
A las cuales se les puede asignar multiples generos y una imagen. 
A su vez consta con un sistema de autentication y autorizacion para el control de ciertos recursos.

## Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- MySQL
- Lombok
- Mapstruct
- Swagger

## Seguridad
La seguridad de la API se basa en un sistema de autenticacion y autorizacion mediante JWT.
Para la autenticacion se utiliza un sistema de login con usuario y contraseña, el cual devuelve un token JWT.
Para la autorizacion se utiliza un sistema de roles, los cuales se asignan a los usuarios y permiten el acceso a ciertos recursos.

## Endpoints
La API cuenta con los siguientes endpoints:

/api/auth - Endpoints de autenticacion.
- [POST] /api/auth/login: Permite el login de un usuario y devuelve un token JWT.
- [POST] /api/auth/register: Permite el registro de un usuario.

/api/generos - Endpoints de generos.
- [GET] /api/generos: Devuelve todos los generos.
- [GET] /api/generos/{id}: Devuelve un genero por su id.
- [POST] /api/generos: Crea un genero.
- [PUT] /api/generos/{id}: Actualiza un genero por su id.

/api/peliculas - Endpoints de peliculas.
- [POST] /api/peliculas: Crea una pelicula.
- [GET] /api/peliculas: Devuelve todas las peliculas.
- [GET] /api/peliculas/buscar/genero?genero=nombreDelGenero: Devuelve todas las peliculas que contengan el genero.
- [GET] /api/peliculas/buscar/titulo?titulo=nombre_del_titulo: Devuelve todas las peliculas que contengan el titulo. 

## Documentacion
La documentacion de la API se encuentra en la siguiente URL:
- http://localhost:8080/docs

## DER Base de Datos


## Autor
- [Juan Ferrara](https://www.linkedin.com/in/juan-ferrara/)