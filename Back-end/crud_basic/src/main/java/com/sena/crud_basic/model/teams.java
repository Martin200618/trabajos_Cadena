package com.sena.crud_basic.model;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity(name="teams")
public class teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="team_id")
    private int team_id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="descripcion")
    private String descripcion;

    @ManyToMany
    @JoinTable(
        name = "team_user",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<user> miembros;

    public teams() {}

    public teams(int team_id, String nombre, String descripcion) {
        this.team_id = team_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<user> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<user> miembros) {
        this.miembros = miembros;
    }
}