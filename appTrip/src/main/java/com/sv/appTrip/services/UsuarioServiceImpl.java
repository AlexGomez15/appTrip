package com.sv.appTrip.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sv.appTrip.models.Usuario;
import com.sv.appTrip.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    @Override
    public void guardar(Usuario usuario) {
        if (usuario.getPassword() != null && !usuario.getPassword().isBlank() && !isBCryptHash(usuario.getPassword())) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        usuarioRepository.save(usuario);
    }

    private boolean isBCryptHash(String password) {
        return password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$");
    }

    @Override
    @Transactional
    public void eliminar(Integer idUsuario) {
        usuarioRepository.findById(idUsuario).ifPresent(usuario -> {
            usuario.getPerfiles().clear();
            usuario.getTrips().forEach(trip -> trip.getUsuarios().remove(usuario));
            usuarioRepository.delete(usuario);
        });
    }
}
