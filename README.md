# Trabajo-Grupo-SII-Grupo-12-FIJIS

[Comentarios sobre la implementación del código](https://github.com/x1n4px/Trabajo-Grupo-SII/blob/main/PracticaGrupo/README.md)

## Microservicios a implementar:
1. Gestión de usuarios de la aplicación
- [Token autenticación](https://github.com/x1n4px/Trabajo-Grupo-SII/blob/main/Codigos-Utiles/JWT.md)
- [Modificación datos usuario](https://github.com/x1n4px/Desarrollo-de-servicios-telematicos/tree/main/practicas/practica3DST/practica3/src/main/java/csccorner)
2. Gestión de horario y las materias

## Microservicios
1. Gestión de las sedes y aulas -> CRUD de sedes, responsables de sedes, aulas, vigilantes de ulas y responsables de aulas. Cada año se podría usar distintas sedes. Este servicio permitirá asignar vigilantes y responsables a aulas y responsables de sedes a sedes.
2. **Gestión de usuarios de la aplicación** -> CRUD de usuarios de la aplicación. Permitirá a cada usuario a cambiar sus datos de contacto y cambiar su contraseña en caso de que se le olvide. Permitirá hacer login y generará el token de autenticación para el acceso a los servicios Web
3. Gestión del estudiantado	-> Importación de información de alumnos a partir de CSV o Excel. Permitirá obtener el lisado de alumnos con posibilidad de filtrarlo y ordenarlos or algunos campos. La información de los alumnos deberá estar contextualizada a cada año. En el curso vigente se podrá eliminar y regenerar el listado (pasada una fecha se bloquea)
4. Asignación de materias y estudiantes a aulas	-> Permitirá asociar alumnos a sedes. Permitirá asignar materias (y alumnos) a aulas en una sede. Permitirá el reporte de los alumnos presentados a los 30 minutos del inicio del examen y el reporte de los alumnos que han presentado el examen al final. Permitirá distinguir a los alumnos con necesidades especiales para su asignación a ciertas aulas especiales. Hay que tener en cuenta que el primer día el alumno no puede cambiar de aula en los tres primeros exámenes. Este servicio debe generar los anexos I y II en PDF
5. Notificaciones -> Envío de notificaciones para los alumnos, sobre lugar y hora del examen y notificación a los vigilantes y y responsables de aula de su aula para vigilar. Las notificaciones se harán por correo electrónico y SMS (cuando el móvil está disponible). También permitirá la notificación al alumno de la noa obtenida en cada examen cuando el vicerrectorado haga pública esta información
6. Gestión de correctores	-> CRUD de los correctores de exámenes
7. Evaluación de exámenes	-> Asignación de exámenes a correctores. Un corrector no puede corregir más exámenes de lo que ha indicado y solo puede corregir exámenes de su materia. El servicio debe generar la asignación de forma automática y permitir la consulta a los correctores de los exámenes que deben corregir. El servicio permitirá a cada evaluador incluir la calificación para el examen. El vicerrectorado deberá poder comprobar cuántos exámenes hay corregidos en cada momento del total
8. **Gestión del horario y las materias**	-> Permitirá definir el horario de la PEvAU para cada curso y gestionar las materias disponibles.

## Requisitos de Información
1. Estudiante -> DNI, nombre, apellidos, materias en las que está matriculad@, correo electrónico (opcional), teléfono móvil (opcional), neceisdad especial, código de identificación para exámenes (pegatinas), instituto de procedencia
2. Aula -> Aforo, disponibilidad temporal (en el horario de la PEVAU)
3. Responsable de Sede -> Nombre, apellidos, correo electrónico, teléfono móvil (opcional)
4. Vigilante/responsable de aula -> Nombre, apellidos, correo electrónico, teléfono móvil (opcional), disponibilidad temporal durante la PEvAU
5. Corrector -> Nombre, apellidos, correo electrónico, teléfono móvil (opcional), materia en que es especialista, número máximo de exámenes a corregir
6. Vicerrectorado -> Correo electrónico
7. Sede -> Nombre, dirección, coordenadas geográficas


