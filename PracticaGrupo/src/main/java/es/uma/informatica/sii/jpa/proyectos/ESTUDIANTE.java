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

    //R U-M (ENT DEBIL) ESTUDIANTE_ASISTENCIA
    @OneToMany(mappedBy = "estudiante")
    private List<ASISTENCIA> asistencias;








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

    public List<ASISTENCIA> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<ASISTENCIA> asistencias) {
        this.asistencias = asistencias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ESTUDIANTE that = (ESTUDIANTE) o;
        return Objects.equals(DNI, that.DNI) && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos) && Objects.equals(Telefono, that.Telefono) && Objects.equals(Correoe, that.Correoe) && Objects.equals(centro, that.centro) && Objects.equals(materia, that.materia) && Objects.equals(asistencias, that.asistencias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DNI, nombre, apellidos, Telefono, Correoe, centro, materia, asistencias);
    }
}
