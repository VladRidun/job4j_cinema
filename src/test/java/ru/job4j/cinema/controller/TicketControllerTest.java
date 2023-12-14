package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.SessionPlaceDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.ticket.TicketService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketControllerTest {
    private TicketService ticketService;
    private TicketController ticketController;

    @BeforeEach
    public void initServices() {
        ticketService = mock(TicketService.class);
        ticketController = new TicketController(ticketService);
    }

    @Test
    public void whenBuyTicketThenSave() {
        var model = new ConcurrentModel();
        var ticket = new Ticket(1, 1, 1, 1, 1);
        var sessionPlace = new SessionPlaceDto(
                1, null, "", "", 100, null, null);
        var user = new User(1, "test", "test@mail.com", "12345");

        var mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("sessionPlace", sessionPlace);
        mockHttpSession.setAttribute("user", user);

        var ticketArgumentCaptor = ArgumentCaptor.forClass(Ticket.class);
        var sessionPlaceArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        var userArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        when(ticketService.save(ticketArgumentCaptor.capture(),
                sessionPlaceArgumentCaptor.capture(), userArgumentCaptor.capture()))
                .thenReturn(Optional.of(ticket));

        var view = ticketController.register(model, mockHttpSession, ticket);
        var actualTicket = ticketArgumentCaptor.getValue();
        var actualMessage = model.getAttribute("message");
        var actualSessionCellId = sessionPlaceArgumentCaptor.getValue();
        var actualUserId = userArgumentCaptor.getValue();

        assertThat(view).isEqualTo("request/200");
        assertThat(actualTicket).isEqualTo(ticket);
        assertThat(actualMessage).isEqualTo("Вы успешно приобрели билет. Ряд: 1, место: 1");
        assertThat(actualSessionCellId).isEqualTo(sessionPlace.getId());
        assertThat(actualUserId).isEqualTo(user.getId());
    }

    @Test
    public void whenBuyTicketThenEmpty() {
        var session = new SessionPlaceDto(
                1, null, "", "", 100, null, null);
        var user = new User(1, "test", "test@mail.com", "12345");

        var mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("sessionPlace", session);
        mockHttpSession.setAttribute("user", user);

        var model = new ConcurrentModel();
        when(ticketService.save(any(Ticket.class), any(Integer.class), any(Integer.class))).thenReturn(Optional.empty());

        var view = ticketController.register(model, mockHttpSession, new Ticket());
        var actualMessage = model.getAttribute("message");

        assertThat(view).isEqualTo("error/409");
        assertThat(actualMessage).isEqualTo("Не удалось приобрести билет на данное место. Вероятно оно уже занято.Попробуйте заново выбрать место");
    }
}
