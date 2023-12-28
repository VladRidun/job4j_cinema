package ru.job4j.cinema.mapper;

import org.mapstruct.Mapper;
import ru.job4j.cinema.dto.SessionPlaceDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Mapper(componentModel = "spring")
public interface SessionPlaceMapper {
    default SessionPlaceDto getSessionPlaceDto(FilmSession filmSession, Film film, Collection<Integer> savedPlaces, Collection<Integer> savedRows) {
        return new SessionPlaceDto(
                filmSession.getId(),
                film,
                DateTimeFormatter.ofPattern("EEEE dd MMMM HH:mm").format(filmSession.getStartTime()),
                DateTimeFormatter.ofPattern("EEEE dd MMMM HH:mm").format(filmSession.getEndTime()),
                filmSession.getPrice(),
                savedPlaces,
                savedRows
        );
    }
}
