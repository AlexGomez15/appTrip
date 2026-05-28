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
import com.sv.appTrip.models.Usuario;
import com.sv.appTrip.repository.PerfilRepository;
import com.sv.appTrip.services.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private final IUsuarioService usuarioService;
    private final PerfilRepository perfilRepository;

    public UsuarioController(IUsuarioService usuarioService, PerfilRepository perfilRepository) {
        this.usuarioService = usuarioService;
        this.perfilRepository = perfilRepository;
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
        model.addAttribute("selectedPerfilIds", List.of());
        return "usuarios/formUsuario";
    }

    @GetMapping("/edit")
    public String editar(@RequestParam("id") Integer idUsuario, Model model) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        List<Integer> selectedPerfilIds = usuario.getPerfiles().stream()
                .map(Perfil::getId)
                .toList();
        usuario.setPassword("");
        model.addAttribute("usuario", usuario);
        model.addAttribute("perfiles", perfilRepository.findAll());
        model.addAttribute("selectedPerfilIds", selectedPerfilIds);
        return "usuarios/formUsuario";
    }

    @PostMapping("/save")
    public String guardar(Usuario usuario,
            @RequestParam(value = "perfilesIds", required = false) List<Integer> perfilesIds) {
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
        return "redirect:/usuarios/index";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") Integer idUsuario) {
        usuarioService.eliminar(idUsuario);
        return "redirect:/usuarios/index";
    }
}
