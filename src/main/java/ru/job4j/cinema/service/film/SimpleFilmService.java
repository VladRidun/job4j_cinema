package ru.job4j.cinema.service.film;

import net.jcip.annotations.ThreadSafe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.mapper.FilmMapper;
import ru.job4j.cinema.mapper.FilmSessionMapper;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.film.FilmRepository;
import ru.job4j.cinema.service.genre.GenreService;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
@Service
public class SimpleFilmService implements FilmService {
    private final FilmRepository filmRepository;
    private final GenreService genreService;
    private final FilmMapper filmMapper = Mappers.getMapper(FilmMapper.class);
    private final ConcurrentHashMap<Integer, FilmDto> filmsDto = new ConcurrentHashMap<>();

    public SimpleFilmService(FilmRepository sql2oFilmRepository, GenreService genreService, FilmMapper filmMapper) {
        this.filmRepository = sql2oFilmRepository;
        this.genreService = genreService;
    }

    @Override
    public Optional<Film> findById(int id) {
        return filmRepository.findById(id);
    }

    @Override
    public Collection<FilmDto> findAll() {
        var films = filmRepository.findAll();
        for (Film film : films) {
            FilmDto filmDto = filmMapper.getFilmDto(film, genreService.findById(film.getId()));
            filmsDto.putIfAbsent(film.getId(), filmDto);
        }
        return filmsDto.values();
    }
}
