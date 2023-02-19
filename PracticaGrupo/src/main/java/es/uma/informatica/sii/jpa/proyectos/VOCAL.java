package es.uma.informatica.sii.jpa.proyectos;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class VOCAL {
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private String DNI;

    @JoinColumn(nullable = false)
    private String Nombre;

    @JoinColumn(nullable = false)
    private String Apellidos;

    private String Tipo;

    private String Cargo;

    //Relacion U-U SEDE VOCAL
    @OneToOne(mappedBy = "vocal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private SEDE sede;

    //Relacion M-M VOCAL-EXAMEN
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "vocal_examen",
            joinColumns = {@JoinColumn(name = "vocal_id")},
            inverseJoinColumns = {@JoinColumn(name = "examen_id")}
    )
    private Set<EXAMEN> examen;

    //R U-M VOCAL-EXAMEN
    @OneToMany(mappedBy = "vocalResponsable")
    private List<EXAMEN> examenes;


    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    public SEDE getSede() {
        return sede;
    }

    public void setSede(SEDE sede) {
        this.sede = sede;
    }

    public Set<EXAMEN> getExamen() {
        return examen;
    }

    public void setExamen(Set<EXAMEN> examen) {
        this.examen = examen;
    }

    public List<EXAMEN> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<EXAMEN> examenes) {
        this.examenes = examenes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VOCAL vocal = (VOCAL) o;
        return Objects.equals(DNI, vocal.DNI) && Objects.equals(Nombre, vocal.Nombre) && Objects.equals(Apellidos, vocal.Apellidos) && Objects.equals(Tipo, vocal.Tipo) && Objects.equals(Cargo, vocal.Cargo) && Objects.equals(sede, vocal.sede) && Objects.equals(examen, vocal.examen) && Objects.equals(examenes, vocal.examenes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DNI, Nombre, Apellidos, Tipo, Cargo, sede, examen, examenes);
    }
}

