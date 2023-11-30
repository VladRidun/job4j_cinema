package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.dto.SessionPlaceDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.TicketService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/buy")
    public String register(Model model, HttpSession session,
                           @ModelAttribute Ticket ticket) {
        var sessionPlace = (SessionPlaceDto) session.getAttribute("sessionPlace");
        var user = (User) session.getAttribute("user");
        var savedTicket = ticketService.save(ticket, sessionPlace.getId(), user.getId());
        if (savedTicket.isEmpty()) {
            model.addAttribute("message",
                    "Не удалось приобрести билет на данное место. "
                            + "Вероятно оно уже занято." + "Попробуйте заново выбрать место");
            return "error/409";
        }
        model.addAttribute("message",
                String.format("Вы успешно приобрели билет. Ряд: %s, место: %s",
                        savedTicket.get().getRowNumber(),
                        savedTicket.get().getPlaceNumber()));
        return "request/200";
    }
}