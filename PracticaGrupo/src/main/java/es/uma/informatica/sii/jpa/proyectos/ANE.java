package es.uma.informatica.sii.jpa.proyectos;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.util.Objects;

@Embeddable
public class ANE extends ESTUDIANTE{

    private String Descabezar;
    private String AulaAparte;


    public String getDescabezar() {
        return Descabezar;
    }

    public void setDescabezar(String descabezar) {
        Descabezar = descabezar;
    }

    public String getAulaAparte() {
        return AulaAparte;
    }

    public void setAulaAparte(String aulaAparte) {
        AulaAparte = aulaAparte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ANE ane = (ANE) o;
        return Objects.equals(Descabezar, ane.Descabezar) && Objects.equals(AulaAparte, ane.AulaAparte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Descabezar, AulaAparte);
    }
}
