package com.sv.appTrip.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sv.appTrip.services.ITripService;

@Controller
public class HomeController {
    private final ITripService serviceTrip;

    public HomeController(ITripService serviceTrip) {
        this.serviceTrip = serviceTrip;
    }

    @GetMapping("/")
    public String mostrarHome(Model model) {
        model.addAttribute("trips", serviceTrip.buscarTodos());
        return "home";
    }

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        model.addAttribute("trips", serviceTrip.buscarTodos());
        return "tabla";
    }
}
