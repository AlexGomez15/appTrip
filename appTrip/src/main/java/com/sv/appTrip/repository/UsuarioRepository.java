package com.sv.appTrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sv.appTrip.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
