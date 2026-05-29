package com.sv.appTrip.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_trip", nullable = false, length = 255)
    private String nomTrip;

    @Column(length = 255)
    private String descripcion;

    private Double costo;

    @Column(length = 255)
    private String imagen = "no-image.png";

    @Column(columnDefinition = "TEXT")
    private String detalles;

    private Boolean activo = true;

    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToMany
    @JoinTable(name = "trip_usuario",
        joinColumns = @JoinColumn(name = "trip_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> usuarios = new ArrayList<>();

    public Trip() {}
    public Trip(String nomTrip, String descripcion, Double costo, String imagen, String detalles, Boolean activo, Categoria categoria) {
        this.nomTrip = nomTrip;
        this.descripcion = descripcion;
        this.costo = costo;
        this.imagen = imagen;
        this.detalles = detalles;
        this.activo = activo;
        this.categoria = categoria;
        this.fecha = LocalDateTime.now();
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomTrip() { return nomTrip; }
    public void setNomTrip(String nomTrip) { this.nomTrip = nomTrip; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
    public String getDetalles() { return detalles; }
    public void setDetalles(String detalles) { this.detalles = detalles; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }
    @Override public String toString() { return "Trip [id=" + id + ", nomTrip=" + nomTrip + ", costo=" + costo + "]"; }
}
