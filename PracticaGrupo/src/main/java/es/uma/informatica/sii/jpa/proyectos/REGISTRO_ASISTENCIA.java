package es.uma.informatica.sii.jpa.proyectos;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class REGISTRO_ASISTENCIA {

    @EmbeddedId
    private ASISTENCIA asistencia;

    // otros atributos y relaciones
}

