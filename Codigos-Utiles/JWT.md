[Web Origen](https://blog.softtek.com/es/autenticando-apis-con-spring-y-jwt)

1. El cliente se autentica y garantiza su identidad haciendo una petición al servidor de autenticación. Esta petición puede ser mediante usuario contraseña, mediante proveedores externos (Google, Facebook, etc) o mediante otros servicios como LDAP, Active Directory, etc.
2. Una vez que el servidor de autenticación garantiza la identidad del cliente, se genera un token de acceso (JWT).
3. El cliente usa ese token para acceder a los recursos protegidos que se publican mediante API.
4. En cada petición, el servidor desencripta el token y comprueba si el cliente tiene permisos para acceder al recurso haciendo una petición al servidor de autorización
![Diagrama](https://github.com/x1n4px/Trabajo-Grupo-SII/blob/main/Codigos-Utiles/Imagenes-Aux/jwtexplanation.png?raw=true)

## Composicion del token
- Header: Contiene el hash que se usa para encriptar el token
- Payload: Contiene una serie de atributos (clave, valor) que se encripta en el token
- Firma: Contiene header y payload concatenados y encriptados (Header + '.' + Payload + SecretKey)

## Crear aplicación Spring Boot
Accedemos a la web de [Spring Initializr](https://start.spring.io/) y generamos un proyecto Maven con Java y Spring Boot 2.1.1, rellenamos el group, el artifact de nuestro proyecto (en este caso "es.softtek" y "jwt-demo") y añadimos dependencias para Web:
![JPW](https://github.com/x1n4px/Trabajo-Grupo-SII/blob/main/Codigos-Utiles/Imagenes-Aux/jwp.png?raw=true)

## Implementando nuestro API
```
package es.softtek.jwtDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping("hello")
	public String helloWorld(@RequestParam(value="name", defaultValue="World") String name) {
		return "Hello "+name+"!!";
	}
}
```

Básicamente hemos usado las siguientes anotaciones:
- @RestController para habilitar esta clase como un controlador REST y que pueda interceptar peticiones al servidor.
- @RequestMapping para habilitar a este método para interceptar una llamada al servidor, en este caso, a /hello.
- @RequestParam para habilitar este argumento como parámetro del servicio.

Si arrancamos la aplicación (ejecutamos comando "mvn spring-boot:run" desde la raíz de nuestra aplicación) podemos testear nuestro servicio. Aún no hemos añadido ninguna configuración de seguridad, por lo que podemos invocar al servicio sin restricciones

## Dependencias
Lo primero que necesitamos es añadir las dependencias para Spring Security y de JWT:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## Autenticación
Vamos a crear otro controlador REST para implementar el proceso de autenticación mediante un login usuario/contraseña:

```
package es.softtek.jwtDemo.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.softtek.jwtDemo.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	@PostMapping("user")
	public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		
		String token = getJWTToken(username);
		User user = new User();
		user.setUser(username);
		user.setToken(token);		
		return user;
		
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
```

El método login(...) interceptará las peticiones POST al endpoint /user y recibirá como parámetros el usuario y contraseña. Como se puede observar, para este ejemplo no se realiza ninguna validación de usuario y contraseña, por lo que para cualquier valor de dichos parámetros dejaremos paso. Obviamente, para un proyecto real, en este punto deberíamos autenticar el usuario contra nuestra base de datos o contra cualquier proveedor externo.

Utilizamos el método getJWTToken(...) para construir el token, delegando en la clase de utilidad Jwts que incluye información sobre su expiración y un objeto de GrantedAuthority de Spring que, como veremos más adelante, usaremos para autorizar las peticiones a los recursos protegidos. 

Por último, editaremos nuestra clase de arranque JwtDemoApplication para añadir la siguiente configuración:

```
package es.softtek.jwtDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.softtek.jwtDemo.security.JWTAuthorizationFilter;

@SpringBootApplication
public class JwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user").permitAll()
				.anyRequest().authenticated();
		}
	}

}
```

La clase interna WebSecurityConfig, decorada con @EnableWebSecurity y @Configuration, nos permite especificar la configuración de acceso a los recursos publicados. En este caso se permiten todas las llamadas al controlador /user, pero el resto de las llamadas requieren autenticación

En este momento, si reiniciamos la aplicación y hacemos una llamada a http://localhost:8080/hello, nos devolverá un error 403 informando al usuario de que no está autorizado para acceder a ese recurso que se encuentra protegido

## Autorización

Por último, necesitamos implementar el proceso de autorización, que sea capaz de interceptar las invocaciones a recursos protegidos para recuperar el token y determinar si el cliente tiene permisos o no.

Para ello implementaremos el siguiente filtro, JWTAuthorizationFilter
```
package es.softtek.jwtDemo.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private final String SECRET = "mySecretKey";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			if (existeJWTToken(request, response)) {
				Claims claims = validateToken(request);
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			} else {
					SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}	

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
	}

	/**
	 * Metodo para autenticarnos dentro del flujo de Spring
	 * 
	 * @param claims
	 */
	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List) claims.get("authorities");

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}

}
```







