package com.sv.appTrip;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
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
@Profile("dev")
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

        if (categoriaRepository.count() == 0) {
            Categoria playa = categoriaRepository.save(new Categoria("Playas", "Trips hacia playas de El Salvador", true));
            Categoria montana = categoriaRepository.save(new Categoria("Montaña", "Trips de montaña y naturaleza", true));
            Categoria ciudad = categoriaRepository.save(new Categoria("Ciudad", "Trips culturales y urbanos", true));

            Perfil admin = perfilRepository.save(new Perfil("Administrador", "Puede administrar la aplicación", true));
            Perfil cliente = perfilRepository.save(new Perfil("Cliente", "Puede consultar y publicar trips", true));

            Usuario u1 = new Usuario("Carlos Pérez", "carlos", "12345", true);
            u1.setPerfiles(List.of(admin, cliente));
            Usuario u2 = new Usuario("María López", "maria", "12345", true);
            u2.setPerfiles(List.of(cliente));
            usuarioRepository.save(u1);
            usuarioRepository.save(u2);

            Trip t1 = new Trip("Playa El Tunco", "Viaje a la playa El Tunco", 25.00, "trip01.png", "Salida desde San Salvador, transporte incluido y guía turístico.", true, playa);
            t1.setUsuarios(List.of(u1));
            Trip t2 = new Trip("Ruta de las Flores", "Paseo por pueblos turísticos", 35.00, "trip02.png", "Visita a Ataco, Apaneca y Juayúa. Incluye paradas para fotografías.", true, montana);
            t2.setUsuarios(List.of(u1, u2));
            Trip t3 = new Trip("Centro Histórico", "Recorrido cultural por San Salvador", 15.00, "tirp03.png", "Recorrido por plazas, iglesia El Rosario, Palacio Nacional y Biblioteca Nacional.", true, ciudad);
            t3.setUsuarios(List.of(u2));
            tripRepository.saveAll(List.of(t1, t2, t3));
        }
    }
}
