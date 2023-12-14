package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.session.FilmSessionService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sessions")
public class FilmSessionController {
    private final FilmSessionService filmSessionService;

    public FilmSessionController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id, HttpServletRequest request) {
        var sessionPlace = filmSessionService.findById(id);
        model.addAttribute("sessionPlace", sessionPlace);
        var session = request.getSession();
        session.setAttribute("sessionPlace", sessionPlace);
        return "sessions/one";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("sessions", filmSessionService.findAll());
        return "sessions/list";
    }
}