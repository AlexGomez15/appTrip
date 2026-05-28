package com.sv.appTrip.services;

import java.util.List;
import com.sv.appTrip.models.Trip;

public interface ITripService {
    List<Trip> buscarTodos();
    Trip buscarPorId(Integer idTrip);
    void guardar(Trip trip);
    void eliminar(Integer idTrip);
}
