package com.sv.appTrip.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_usuario", nullable = false, length = 255)
    private String nomUsuario;

    @Column(name = "username", nullable = false, length = 255)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    private Boolean activo = true;

    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToMany
    @JoinTable(name = "perfil_usuario",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfiles = new ArrayList<>();

    @ManyToMany(mappedBy = "usuarios")
    private List<Trip> trips = new ArrayList<>();

    public Usuario() {}
    public Usuario(String nomUsuario, String username, String password, Boolean activo) {
        this.nomUsuario = nomUsuario;
        this.username = username;
        this.password = password;
        this.activo = activo;
        this.fecha = LocalDateTime.now();
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomUsuario() { return nomUsuario; }
    public void setNomUsuario(String nomUsuario) { this.nomUsuario = nomUsuario; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public List<Perfil> getPerfiles() { return perfiles; }
    public void setPerfiles(List<Perfil> perfiles) { this.perfiles = perfiles; }
    public List<Trip> getTrips() { return trips; }
    public void setTrips(List<Trip> trips) { this.trips = trips; }
}
