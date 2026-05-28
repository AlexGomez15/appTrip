package com.sv.appTrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sv.appTrip.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
