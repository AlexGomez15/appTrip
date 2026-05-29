package com.sv.appTrip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sv.appTrip.models.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    @EntityGraph(attributePaths = { "categoria", "usuarios" })
    Optional<Trip> findWithCategoriaAndUsuariosById(Integer id);
}
