package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.dto.SessionPlaceDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.repository.FilmSessionRepository;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Service
public class SimpleFilmSessionService implements FilmSessionService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEEE dd MMMM HH:mm");
    private final FilmSessionRepository sessionRepository;
    private final HallService hallService;
    private final FilmService filmService;
    private final ConcurrentHashMap<Integer, FilmSessionDto> sessionPlaces = new ConcurrentHashMap<>();
    private final AtomicInteger countRows = new AtomicInteger();
    private final AtomicInteger countPlaces = new AtomicInteger();
    private final Collection<Integer> savedPlaces = new ConcurrentLinkedQueue<>();
    private final Collection<Integer> savedRows = new ConcurrentLinkedQueue<>();

    public SimpleFilmSessionService(FilmSessionRepository sessionRepository,
                                HallService hallService, FilmService filmService) {
        this.sessionRepository = sessionRepository;
        this.hallService = hallService;
        this.filmService = filmService;
    }

    @Override
    public SessionPlaceDto findById(int id) {
        var session = sessionRepository.findById(id).get();
        var film = filmService.findById(session.getFilmId()).get();
        var hall = hallService.findById(session.getHallId());
        while (countRows.incrementAndGet() <= hall.getRowCount()) {
            savedRows.add(countRows.get());
        }
        int placeInRowCount = hall.getPlaceCount() / hall.getRowCount();
        while (countPlaces.incrementAndGet() <= placeInRowCount) {
            savedPlaces.add(countPlaces.get());
        }
        return new SessionPlaceDto(
                session.getId(), film, FORMATTER.format(session.getStartTime()),
                FORMATTER.format(session.getEndTime()),
                session.getPrice(), savedRows, savedPlaces);
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        var sessions = sessionRepository.findAll();
        for (FilmSession session : sessions) {
            var filmSession = new FilmSessionDto(
                    session.getId(),
                    filmService.findById(session.getFilmId()).get(),
                    hallService.findById(session.getHallId()),
                    FORMATTER.format(session.getStartTime()), FORMATTER.format(session.getEndTime()),
                    session.getPrice());
            sessionPlaces.putIfAbsent(session.getId(), filmSession);
        }
        return sessionPlaces.values();
    }
}