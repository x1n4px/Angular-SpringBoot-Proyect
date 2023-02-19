package es.uma.informatica.sii.jpa.proyectos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class AULA {
    @EmbeddedId
    private String Codigo;
    @JoinColumn(nullable = false)
    private Double Capacidad;
    @JoinColumn(nullable = false)
    private Double Capacidad_Examen;
    private String Descripcion;


    //R U-M (Ent Debil) SEDE-AULA
    @ManyToOne
    @JoinColumn(name = "sede_id")
    private SEDE sede;

    //R U-M (ENT DEBIL) AULA-EXAMEN
    @OneToMany(mappedBy = "aula")
    private List<EXAMEN> examen;


    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public Double getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(Double capacidad) {
        Capacidad = capacidad;
    }

    public Double getCapacidad_Examen() {
        return Capacidad_Examen;
    }

    public void setCapacidad_Examen(Double capacidad_Examen) {
        Capacidad_Examen = capacidad_Examen;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public SEDE getSede() {
        return sede;
    }

    public void setSede(SEDE sede) {
        this.sede = sede;
    }

    public List<EXAMEN> getExamen() {
        return examen;
    }

    public void setExamen(List<EXAMEN> examen) {
        this.examen = examen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AULA aula = (AULA) o;
        return Objects.equals(Codigo, aula.Codigo) && Objects.equals(Capacidad, aula.Capacidad) && Objects.equals(Capacidad_Examen, aula.Capacidad_Examen) && Objects.equals(Descripcion, aula.Descripcion) && Objects.equals(sede, aula.sede) && Objects.equals(examen, aula.examen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Codigo, Capacidad, Capacidad_Examen, Descripcion, sede, examen);
    }
}
