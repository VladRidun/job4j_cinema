package ru.job4j.cinema.service.session;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.dto.SessionPlaceDto;

import java.util.Collection;

public interface FilmSessionService {

    SessionPlaceDto findById(int id);

    Collection<FilmSessionDto> findAll();
}
