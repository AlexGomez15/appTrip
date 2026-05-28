package com.sv.appTrip.services;

import java.util.List;

import com.sv.appTrip.models.Rol;

public interface IRolService {
    List<Rol> buscarTodos();
    Rol buscarPorId(Integer idRol);
    void guardar(Rol rol);
    void eliminar(Integer idRol);
}
