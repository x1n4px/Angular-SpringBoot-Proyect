package es.uma.informatica.sii.jpa.proyectos;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class CENTRO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Codigo;
    @Column(nullable = false)
    private String Nombre;
    private String Direccion;
    private String Poblacion;

    //Relacion M-U SEDE CENTRO
    @ManyToOne
    @JoinColumn(name = "codigo_sede")
    private SEDE sede;


    //R U-M CENTRO-ESTUDIANTE
    @OneToMany(mappedBy = "centro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ESTUDIANTE> estudiantes;


    public Long getCodigo() {
        return Codigo;
    }

    public void setCodigo(Long codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getPoblacion() {
        return Poblacion;
    }

    public void setPoblacion(String poblacion) {
        Poblacion = poblacion;
    }

    public SEDE getSede() {
        return sede;
    }

    public void setSede(SEDE sede) {
        this.sede = sede;
    }

    public List<ESTUDIANTE> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<ESTUDIANTE> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CENTRO centro = (CENTRO) o;
        return Objects.equals(Codigo, centro.Codigo) && Objects.equals(Nombre, centro.Nombre) && Objects.equals(Direccion, centro.Direccion) && Objects.equals(Poblacion, centro.Poblacion) && Objects.equals(sede, centro.sede) && Objects.equals(estudiantes, centro.estudiantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Codigo, Nombre, Direccion, Poblacion, sede, estudiantes);
    }
}
