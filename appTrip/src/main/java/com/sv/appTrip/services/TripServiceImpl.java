package com.sv.appTrip.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.sv.appTrip.models.Trip;
import com.sv.appTrip.repository.TripRepository;

@Service
public class TripServiceImpl implements ITripService {
    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public List<Trip> buscarTodos() {
        return tripRepository.findAll();
    }

    @Override
    public Trip buscarPorId(Integer idTrip) {
        return tripRepository.findWithCategoriaAndUsuariosById(idTrip).orElse(null);
    }

    @Override
    public void guardar(Trip trip) {
        tripRepository.save(trip);
    }

    @Override
    public void eliminar(Integer idTrip) {
        tripRepository.deleteById(idTrip);
    }
}
