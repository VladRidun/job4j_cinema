package ru.job4j.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;

@Mapper(componentModel = "spring")
public interface FilmMapper {
    @Mapping(source = "film.id", target = "id")
    @Mapping(source = "genre.name", target = "genre")
    FilmDto getFilmDto(Film film, Genre genre);
}
