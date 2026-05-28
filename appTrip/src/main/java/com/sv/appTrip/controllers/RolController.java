package com.sv.appTrip.controllers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sv.appTrip.models.Rol;
import com.sv.appTrip.services.IRolService;

@Controller
@RequestMapping(value = "/roles")
public class RolController {
    private final IRolService rolService;

    public RolController(IRolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/index")
    public String mostrarRoles(Model model) {
        model.addAttribute("roles", rolService.buscarTodos());
        return "roles/listRol";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        model.addAttribute("rol", new Rol());
        return "roles/formRol";
    }

    @GetMapping("/edit")
    public String editar(@RequestParam("id") Integer idRol, Model model) {
        Rol rol = rolService.buscarPorId(idRol);
        model.addAttribute("rol", rol);
        return "roles/formRol";
    }

    @PostMapping("/save")
    public String guardar(Rol rol) {
        if (rol.getActivo() == null) {
            rol.setActivo(false);
        }
        if (rol.getId() != null) {
            Rol rolActual = rolService.buscarPorId(rol.getId());
            if (rolActual != null) {
                rol.setFecha(rolActual.getFecha());
            }
        } else {
            rol.setFecha(LocalDateTime.now());
        }
        rolService.guardar(rol);
        return "redirect:/roles/index";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") Integer idRol) {
        rolService.eliminar(idRol);
        return "redirect:/roles/index";
    }
}
