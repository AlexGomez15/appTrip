package com.sv.appTrip.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sv.appTrip.models.Trip;
import com.sv.appTrip.models.Usuario;
import com.sv.appTrip.services.ICategoriaService;
import com.sv.appTrip.services.ITripService;
import com.sv.appTrip.services.IUsuarioService;

@Controller
@RequestMapping("/trips")
public class TripController {
    private final ITripService serviceTrip;
    private final ICategoriaService serviceCategoria;
    private final IUsuarioService serviceUsuario;

    public TripController(ITripService serviceTrip, ICategoriaService serviceCategoria, IUsuarioService serviceUsuario) {
        this.serviceTrip = serviceTrip;
        this.serviceCategoria = serviceCategoria;
        this.serviceUsuario = serviceUsuario;
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") Integer idTrip, Model model) {
        model.addAttribute("trip", serviceTrip.buscarPorId(idTrip));
        return "trips/detalle";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("categorias", serviceCategoria.buscarTodas());
        model.addAttribute("usuarios", serviceUsuario.buscarTodos());
        model.addAttribute("selectedUsuarioIds", List.of());
        return "trips/formTrip";
    }

    @PostMapping("/save")
    public String guardar(Trip trip,
            @RequestParam(value = "usuariosIds", required = false) List<Integer> usuariosIds) {
        if (trip.getImagen() == null || trip.getImagen().isBlank()) {
            trip.setImagen("no-image.png");
        }
        List<Usuario> usuarios = usuariosIds == null
                ? new ArrayList<>()
                : serviceUsuario.buscarTodos().stream()
                        .filter(usuario -> usuariosIds.contains(usuario.getId()))
                        .toList();
        trip.setUsuarios(usuarios);
        serviceTrip.guardar(trip);
        return "redirect:/tabla";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") Integer idTrip, Model model) {
        serviceTrip.eliminar(idTrip);
        model.addAttribute("mensaje", "El Trip fue eliminado correctamente");
        return "mensaje";
    }
}
