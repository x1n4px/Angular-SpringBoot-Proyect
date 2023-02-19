package es.uma.informatica.sii.jpa.proyectos;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class SEDE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Codigo;
    @Column(nullable = false)
    private String Nombre;
    private String Tipo;

    //Relacion U-U SEDE VOCAL
    @OneToOne(mappedBy = "sedeSecretario")
    private VOCAL vocalSecretario;

    @OneToOne(mappedBy = "sedeRepresentante")
    private VOCAL vocalResponsable;


    //Relacion M-U SEDE CENTRO
    @OneToMany(mappedBy = "sede", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CENTRO> centros;


    //R U-M (Ent Debil) SEDE-AULA
    @OneToMany(mappedBy = "sede")
    private List<AULA> aulas;


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

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public VOCAL getVocalSecretario() {
        return vocalSecretario;
    }

    public void setVocalSecretario(VOCAL vocalSecretario) {
        this.vocalSecretario = vocalSecretario;
    }

    public VOCAL getVocalResponsable() {
        return vocalResponsable;
    }

    public void setVocalResponsable(VOCAL vocalResponsable) {
        this.vocalResponsable = vocalResponsable;
    }

    public List<CENTRO> getCentros() {
        return centros;
    }

    public void setCentros(List<CENTRO> centros) {
        this.centros = centros;
    }

    public List<AULA> getAulas() {
        return aulas;
    }

    public void setAulas(List<AULA> aulas) {
        this.aulas = aulas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SEDE sede = (SEDE) o;
        return Objects.equals(Codigo, sede.Codigo) && Objects.equals(Nombre, sede.Nombre) && Objects.equals(Tipo, sede.Tipo) && Objects.equals(vocalSecretario, sede.vocalSecretario) && Objects.equals(vocalResponsable, sede.vocalResponsable) && Objects.equals(centros, sede.centros) && Objects.equals(aulas, sede.aulas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Codigo, Nombre, Tipo, vocalSecretario, vocalResponsable, centros, aulas);
    }
}
