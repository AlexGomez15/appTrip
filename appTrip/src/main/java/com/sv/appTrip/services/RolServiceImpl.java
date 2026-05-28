package com.sv.appTrip.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sv.appTrip.models.Rol;
import com.sv.appTrip.repository.RolRepository;

@Service
public class RolServiceImpl implements IRolService {
    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> buscarTodos() {
        return rolRepository.findAll();
    }

    @Override
    public Rol buscarPorId(Integer idRol) {
        return rolRepository.findById(idRol).orElse(null);
    }

    @Override
    public void guardar(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public void eliminar(Integer idRol) {
        rolRepository.deleteById(idRol);
    }
}
