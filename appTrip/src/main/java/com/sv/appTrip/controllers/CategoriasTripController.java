package com.sv.appTrip.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sv.appTrip.models.Categoria;
import com.sv.appTrip.services.ICategoriaService;

@Controller
@RequestMapping(value = "/categorias")
public class CategoriasTripController {
    private final ICategoriaService serviceCategoria;

    public CategoriasTripController(ICategoriaService serviceCategoria) {
        this.serviceCategoria = serviceCategoria;
    }

    @GetMapping("/index")
    public String mostrarCategorias(Model model) {
        model.addAttribute("categorias", serviceCategoria.buscarTodas());
        return "categorias/listCategoria";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/formCategoria";
    }

    @PostMapping("/save")
    public String guardar(Categoria categoria) {
        if (categoria.getActivo() == null) {
            categoria.setActivo(true);
        }
        serviceCategoria.guardar(categoria);
        return "redirect:/categorias/index";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") Integer idCategoria) {
        serviceCategoria.eliminar(idCategoria);
        return "redirect:/categorias/index";
    }
}
