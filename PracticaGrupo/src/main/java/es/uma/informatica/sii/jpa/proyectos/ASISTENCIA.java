package es.uma.informatica.sii.jpa.proyectos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ASISTENCIA implements Serializable {

    private boolean Asiste;
    private boolean Entrega;
/*
    //R U-M (ENT DEBIL) EXAMEN_ASISTENCIA
    @ManyToOne
    @JoinColumn(name = "sede_id")
    private EXAMEN examen;

    //R U-M (ENT DEBIL) MATERIA_ASISTENCIA
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private MATERIA materia;

    //R U-M (ENT DEBIL) ESTUDIANTE_ASISTENCIA
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private ESTUDIANTE estudiante;
*/

    public boolean isAsiste() {
        return Asiste;
    }

    public void setAsiste(boolean asiste) {
        Asiste = asiste;
    }

    public boolean isEntrega() {
        return Entrega;
    }

    public void setEntrega(boolean entrega) {
        Entrega = entrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASISTENCIA that = (ASISTENCIA) o;
        return Asiste == that.Asiste && Entrega == that.Entrega;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Asiste, Entrega);
    }
}

