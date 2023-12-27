package ru.job4j.cinema.mapper;

import org.mapstruct.Mapper;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface FilmSessionMapper {
    default FilmSessionDto getFilmSessionDto(FilmSession filmSession, Film film, Hall hall) {
        var filmSessionDto = new FilmSessionDto(
                filmSession.getId(),
                film,
                hall,
                DateTimeFormatter.ofPattern("EEEE dd MMMM HH:mm").format(filmSession.getStartTime()),
                DateTimeFormatter.ofPattern("EEEE dd MMMM HH:mm").format(filmSession.getEndTime()),
                filmSession.getPrice()
        );
        return filmSessionDto;
    }
}
