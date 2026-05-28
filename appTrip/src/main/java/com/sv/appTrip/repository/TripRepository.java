package com.sv.appTrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sv.appTrip.models.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
