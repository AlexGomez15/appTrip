package com.sv.appTrip;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sv.appTrip.models.Categoria;
import com.sv.appTrip.models.Perfil;
import com.sv.appTrip.models.Rol;
import com.sv.appTrip.models.Trip;
import com.sv.appTrip.models.Usuario;
import com.sv.appTrip.repository.CategoriaRepository;
import com.sv.appTrip.repository.PerfilRepository;
import com.sv.appTrip.repository.RolRepository;
import com.sv.appTrip.repository.TripRepository;
import com.sv.appTrip.repository.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {
    private final CategoriaRepository categoriaRepository;
    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final TripRepository tripRepository;
    private final RolRepository rolRepository;

    public DataLoader(CategoriaRepository categoriaRepository, PerfilRepository perfilRepository,
            UsuarioRepository usuarioRepository, TripRepository tripRepository, RolRepository rolRepository) {
        this.categoriaRepository = categoriaRepository;
        this.perfilRepository = perfilRepository;
        this.usuarioRepository = usuarioRepository;
        this.tripRepository = tripRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public void run(String... args) {
        if (rolRepository.count() == 0) {
            Rol vendedor = new Rol("Vendedor", "Puede ofrecer y gestionar trips para los visitantes", true);
            Rol visitante = new Rol("Visitante", "Puede consultar la información de los trips disponibles", true);
            Rol coordinador = new Rol("Coordinador", "Puede coordinar actividades y revisar reservaciones", true);
            rolRepository.saveAll(List.of(vendedor, visitante, coordinador));
        }

        List<Categoria> categorias = new ArrayList<>(categoriaRepository.findAll());
        if (categoriaRepository.count() == 0) {
            categorias.add(categoriaRepository.save(new Categoria("Playas", "Trips hacia playas de El Salvador", true)));
            categorias.add(categoriaRepository.save(new Categoria("Montaña", "Trips de montaña y naturaleza", true)));
            categorias.add(categoriaRepository.save(new Categoria("Ciudad", "Trips culturales y urbanos", true)));
        }

        while (categorias.size() < 3) {
            categorias.add(categoriaRepository.save(new Categoria("Categoría " + (categorias.size() + 1), "Categoría demo", true)));
        }

        List<Perfil> perfiles = new ArrayList<>(perfilRepository.findAll());
        if (perfilRepository.count() == 0) {
            perfiles.add(perfilRepository.save(new Perfil("Administrador", "Puede administrar la aplicación", true)));
            perfiles.add(perfilRepository.save(new Perfil("Cliente", "Puede consultar y publicar trips", true)));
        }

        while (perfiles.size() < 2) {
            perfiles.add(perfilRepository.save(new Perfil("Perfil " + (perfiles.size() + 1), "Perfil demo", true)));
        }

        List<Usuario> usuarios = new ArrayList<>(usuarioRepository.findAll());
        if (usuarioRepository.count() == 0) {
            Usuario u1 = new Usuario("Carlos Pérez", "carlos", "12345", true);
            u1.setPerfiles(List.of(perfiles.get(0), perfiles.get(1)));
            Usuario u2 = new Usuario("María López", "maria", "12345", true);
            u2.setPerfiles(List.of(perfiles.get(1)));
            usuarios.add(usuarioRepository.save(u1));
            usuarios.add(usuarioRepository.save(u2));
        }

        while (usuarios.size() < 2) {
            usuarios.add(usuarioRepository.save(new Usuario("Usuario " + (usuarios.size() + 1), "usuario" + (usuarios.size() + 1), "12345", true)));
        }

        if (tripRepository.count() == 0) {
            Trip t1 = new Trip("Playa El Tunco", "Viaje a la playa El Tunco", 25.00, "trip01.png", "Salida desde San Salvador, transporte incluido y guía turístico.", true, categorias.get(0));
            t1.setUsuarios(List.of(usuarios.get(0)));
            Trip t2 = new Trip("Ruta de las Flores", "Paseo por pueblos turísticos", 35.00, "trip02.png", "Visita a Ataco, Apaneca y Juayúa. Incluye paradas para fotografías.", true, categorias.get(1));
            t2.setUsuarios(List.of(usuarios.get(0), usuarios.get(1)));
            Trip t3 = new Trip("Centro Histórico", "Recorrido cultural por San Salvador", 15.00, "tirp03.png", "Recorrido por plazas, iglesia El Rosario, Palacio Nacional y Biblioteca Nacional.", true, categorias.get(2));
            t3.setUsuarios(List.of(usuarios.get(1)));
            tripRepository.saveAll(List.of(t1, t2, t3));
        }
    }
}
