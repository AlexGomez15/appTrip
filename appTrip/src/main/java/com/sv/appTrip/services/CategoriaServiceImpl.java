package com.sv.appTrip.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.sv.appTrip.models.Categoria;
import com.sv.appTrip.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        return categoriaRepository.findById(idCategoria).orElse(null);
    }

    @Override
    public void guardar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void eliminar(Integer idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }
}
