package es.uma.informatica.sii.jpa.proyectos;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ESTUDIANTE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String DNI;
    @JoinColumn(nullable = false)
    private String nombre;
    @JoinColumn(nullable = false)
    private String apellidos;
    @JoinColumn(nullable = false)
    private String Telefono;
    private String Correoe;

    //R U-M CENTRO-ESTUDIANTE
    @ManyToOne()
    @JoinColumn(name = "Centro_id")
    private CENTRO centro;

    //R M-M ESTUDIANTE-MATERIA
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "estudiante_materia",
            joinColumns = {@JoinColumn(name = "estudiante_id")},
            inverseJoinColumns = {@JoinColumn(name = "materia-id")}
    )
    private Set<MATERIA> materia;
/*
    //R U-M (ENT DEBIL) ESTUDIANTE_ASISTENCIA
    @OneToMany(mappedBy = "estudiante")
    private List<ASISTENCIA> asistencias;
*/

    @Embedded
    private ANE ane;

    @Embedded
    private ASISTENCIA asistencia;




    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreoe() {
        return Correoe;
    }

    public void setCorreoe(String correoe) {
        Correoe = correoe;
    }

    public CENTRO getCentro() {
        return centro;
    }

    public void setCentro(CENTRO centro) {
        this.centro = centro;
    }

    public Set<MATERIA> getMateria() {
        return materia;
    }

    public void setMateria(Set<MATERIA> materia) {
        this.materia = materia;
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
        ESTUDIANTE that = (ESTUDIANTE) o;
        return DNI.equals(that.DNI) && nombre.equals(that.nombre) && apellidos.equals(that.apellidos) && Telefono.equals(that.Telefono) && Correoe.equals(that.Correoe) && centro.equals(that.centro) && materia.equals(that.materia) && ane.equals(that.ane) && asistencia.equals(that.asistencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DNI, nombre, apellidos, Telefono, Correoe, centro, materia, ane, asistencia);
    }
}
