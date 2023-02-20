package es.uma.informatica.sii.jpa.proyectos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class MATERIA {
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private String Codigo;
    @JoinColumn(nullable = false)
    private String Nombre;

    //R M-M ESTUDIANTE-MATERIA
    @ManyToMany(mappedBy = "materia")
    private Set<ESTUDIANTE> estudiante;
/*
    //R U-M (ENT DEBIL) MATERIA_ASISTENCIA
    @OneToMany(mappedBy = "materia")
    private List<ASISTENCIA> asistencias;
*/
    //R M-M EXAMEN-MATERIA
    @ManyToMany(mappedBy = "materias")
    private List<EXAMEN> examenes = new ArrayList<>();


    @Embedded
    private ASISTENCIA asistencia;



// constructor, getters y setters


    public String getCodigo() {
        return Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public Set<ESTUDIANTE> getEstudiante() {
        return estudiante;
    }



    public List<EXAMEN> getExamenes() {
        return examenes;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setEstudiante(Set<ESTUDIANTE> estudiante) {
        this.estudiante = estudiante;
    }



    public void setExamenes(List<EXAMEN> examenes) {
        this.examenes = examenes;
    }

    public ASISTENCIA getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(ASISTENCIA asistencia) {
        this.asistencia = asistencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MATERIA materia = (MATERIA) o;
        return Objects.equals(Codigo, materia.Codigo) && Objects.equals(Nombre, materia.Nombre) && Objects.equals(estudiante, materia.estudiante) && Objects.equals(examenes, materia.examenes) && Objects.equals(asistencia, materia.asistencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Codigo, Nombre, estudiante, examenes, asistencia);
    }
}