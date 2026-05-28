package com.sv.appTrip.services;

import java.util.List;
import com.sv.appTrip.models.Usuario;

public interface IUsuarioService {
    List<Usuario> buscarTodos();
    Usuario buscarPorId(Integer idUsuario);
    void guardar(Usuario usuario);
    void eliminar(Integer idUsuario);
}
