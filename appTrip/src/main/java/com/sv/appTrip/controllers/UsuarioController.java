package com.sv.appTrip.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sv.appTrip.models.Perfil;
import com.sv.appTrip.models.Trip;
import com.sv.appTrip.models.Usuario;
import com.sv.appTrip.repository.PerfilRepository;
import com.sv.appTrip.services.ITripService;
import com.sv.appTrip.services.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private final IUsuarioService usuarioService;
    private final PerfilRepository perfilRepository;
    private final ITripService tripService;

    public UsuarioController(IUsuarioService usuarioService, PerfilRepository perfilRepository, ITripService tripService) {
        this.usuarioService = usuarioService;
        this.perfilRepository = perfilRepository;
        this.tripService = tripService;
    }

    @GetMapping("/index")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.buscarTodos());
        return "usuarios/listUsuario";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("perfiles", perfilRepository.findAll());
        model.addAttribute("trips", tripService.buscarTodos());
        model.addAttribute("selectedPerfilIds", List.of());
        model.addAttribute("selectedTripIds", List.of());
        return "usuarios/formUsuario";
    }

    @GetMapping("/edit")
    public String editar(@RequestParam("id") Integer idUsuario, Model model) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        List<Integer> selectedPerfilIds = usuario.getPerfiles().stream()
                .map(Perfil::getId)
                .toList();
        List<Integer> selectedTripIds = usuario.getTrips().stream()
                .map(Trip::getId)
                .toList();
        usuario.setPassword("");
        model.addAttribute("usuario", usuario);
        model.addAttribute("perfiles", perfilRepository.findAll());
        model.addAttribute("trips", tripService.buscarTodos());
        model.addAttribute("selectedPerfilIds", selectedPerfilIds);
        model.addAttribute("selectedTripIds", selectedTripIds);
        return "usuarios/formUsuario";
    }

    @PostMapping("/save")
    public String guardar(Usuario usuario,
            @RequestParam(value = "perfilesIds", required = false) List<Integer> perfilesIds,
            @RequestParam(value = "tripsIds", required = false) List<Integer> tripsIds) {
        if (usuario.getActivo() == null) {
            usuario.setActivo(false);
        }

        if (usuario.getId() != null) {
            Usuario usuarioActual = usuarioService.buscarPorId(usuario.getId());
            if (usuarioActual != null) {
                usuario.setFecha(usuarioActual.getFecha());
                if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
                    usuario.setPassword(usuarioActual.getPassword());
                }
            }
        } else {
            usuario.setFecha(LocalDateTime.now());
        }

        List<Perfil> perfiles = perfilesIds == null
                ? new ArrayList<>()
                : perfilRepository.findAllById(perfilesIds);
        usuario.setPerfiles(perfiles);
        usuarioService.guardar(usuario);

        List<Trip> tripsSeleccionados = tripsIds == null
                ? new ArrayList<>()
                : tripService.buscarTodos().stream()
                        .filter(trip -> tripsIds.contains(trip.getId()))
                        .toList();
        for (Trip trip : tripService.buscarTodos()) {
            trip.getUsuarios().removeIf(usuarioTrip -> usuario.getId().equals(usuarioTrip.getId()));
            if (tripsSeleccionados.stream().anyMatch(seleccionado -> seleccionado.getId().equals(trip.getId()))) {
                trip.getUsuarios().add(usuario);
            }
            tripService.guardar(trip);
        }
        return "redirect:/usuarios/index";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") Integer idUsuario) {
        usuarioService.eliminar(idUsuario);
        return "redirect:/usuarios/index";
    }
}
