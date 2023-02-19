1. El cliente se autentica y garantiza su identidad haciendo una petición al servidor de autenticación. Esta petición puede ser mediante usuario contraseña, mediante proveedores externos (Google, Facebook, etc) o mediante otros servicios como LDAP, Active Directory, etc.
2. Una vez que el servidor de autenticación garantiza la identidad del cliente, se genera un token de acceso (JWT).
3. El cliente usa ese token para acceder a los recursos protegidos que se publican mediante API.
4. En cada petición, el servidor desencripta el token y comprueba si el cliente tiene permisos para acceder al recurso haciendo una petición al servidor de autorización
![Diagrama]([https://blog.softtek.com/hs-fs/hubfs/jwtexplanation.png?width=600&name=jwtexplanation.png](https://github.com/x1n4px/Trabajo-Grupo-SII/blob/main/Codigos-Utiles/Imagenes-Aux/jwtexplanation.png))
