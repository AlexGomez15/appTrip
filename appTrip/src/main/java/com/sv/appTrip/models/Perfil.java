package com.sv.appTrip.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nomPerfil", nullable = false, length = 255)
    private String nomPerfil;

    @Column(length = 255)
    private String descripcion;

    private Boolean activo = true;

    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToMany(mappedBy = "perfiles")
    private List<Usuario> usuarios = new ArrayList<>();

    public Perfil() {}
    public Perfil(String nomPerfil, String descripcion, Boolean activo) {
        this.nomPerfil = nomPerfil;
        this.descripcion = descripcion;
        this.activo = activo;
        this.fecha = LocalDateTime.now();
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomPerfil() { return nomPerfil; }
    public void setNomPerfil(String nomPerfil) { this.nomPerfil = nomPerfil; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }
}
