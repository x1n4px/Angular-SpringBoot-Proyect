package es.uma.informatica.sii.jpa.proyectos;

import javax.persistence.*;
import java.util.*;


@Entity
public class EXAMEN {
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private Date FechayHora;


    //Relacion M-M VOCAL-EXAMEN
    @ManyToMany(mappedBy = "examen")
    private Set<VOCAL> vocal;

    //R U-M (ENT DEBIL) AULA-EXAMEN
    @ManyToOne
    @JoinColumn(name = "aula_id")
    private AULA aula;

    //R U-M VOCAL-EXAMEN
    @ManyToOne
    @JoinColumn(name = "vocal_id")
    private VOCAL vocalResponsable;

    //R U-M (ENT DEBIL) EXAMEN_ASISTENCIA
    @OneToMany(mappedBy = "examen")
    private List<ASISTENCIA> asistencia;


    //R M-M EXAMEN-MATERIA
    @ManyToMany
    @JoinTable(
            name = "examen_materia",
            joinColumns = @JoinColumn(name = "examen_fechayhora"),
            inverseJoinColumns = @JoinColumn(name = "materia_codigo")
    )
    private List<MATERIA> materias = new ArrayList<>();

    public Date getFechayHora() {
        return FechayHora;
    }

    public void setFechayHora(Date fechayHora) {
        FechayHora = fechayHora;
    }

    public Set<VOCAL> getVocal() {
        return vocal;
    }

    public void setVocal(Set<VOCAL> vocal) {
        this.vocal = vocal;
    }

    public AULA getAula() {
        return aula;
    }

    public void setAula(AULA aula) {
        this.aula = aula;
    }

    public VOCAL getVocalResponsable() {
        return vocalResponsable;
    }

    public void setVocalResponsable(VOCAL vocalResponsable) {
        this.vocalResponsable = vocalResponsable;
    }

    public List<ASISTENCIA> getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(List<ASISTENCIA> asistencia) {
        this.asistencia = asistencia;
    }

    public List<MATERIA> getMaterias() {
        return materias;
    }

    public void setMaterias(List<MATERIA> materias) {
        this.materias = materias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EXAMEN examen = (EXAMEN) o;
        return Objects.equals(FechayHora, examen.FechayHora) && Objects.equals(vocal, examen.vocal) && Objects.equals(aula, examen.aula) && Objects.equals(vocalResponsable, examen.vocalResponsable) && Objects.equals(asistencia, examen.asistencia) && Objects.equals(materias, examen.materias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FechayHora, vocal, aula, vocalResponsable, asistencia, materias);
    }
}