package com.sv.appTrip.services;

import java.util.List;
import com.sv.appTrip.models.Categoria;

public interface ICategoriaService {
    List<Categoria> buscarTodas();
    Categoria buscarPorId(Integer idCategoria);
    void guardar(Categoria categoria);
    void eliminar(Integer idCategoria);
}
