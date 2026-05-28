package com.sv.appTrip.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nomCategoria", nullable = false, length = 255)
    private String nomCategoria;

    @Column(length = 255)
    private String descripcion;

    private Boolean activo = true;

    private LocalDateTime fecha = LocalDateTime.now();

    @OneToMany(mappedBy = "categoria")
    private List<Trip> trips = new ArrayList<>();

    public Categoria() {}
    public Categoria(String nomCategoria, String descripcion, Boolean activo) {
        this.nomCategoria = nomCategoria;
        this.descripcion = descripcion;
        this.activo = activo;
        this.fecha = LocalDateTime.now();
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomCategoria() { return nomCategoria; }
    public void setNomCategoria(String nomCategoria) { this.nomCategoria = nomCategoria; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public List<Trip> getTrips() { return trips; }
    public void setTrips(List<Trip> trips) { this.trips = trips; }
    @Override public String toString() { return "Categoria [id=" + id + ", nomCategoria=" + nomCategoria + "]"; }
}
