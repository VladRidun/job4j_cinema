package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.FilmSession;

import java.time.LocalDateTime;
import java.util.Properties;

class Sql2oSessionRepositoryTest {
    private static Sql2oFilmSessionRepository sql2oFilmSessionRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFileRepository.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");
        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);
        sql2oFilmSessionRepository = new Sql2oFilmSessionRepository(sql2o);
    }

    @Test
    public void whenFindAllSessionsThenGetSame() {
        var startTime = LocalDateTime.of(2023, 9, 25, 13, 00);
        var endTime = LocalDateTime.of(2023, 9, 25, 16, 0);
        FilmSession filmSession = new FilmSession(6, 2, 2, startTime, endTime, 450);
        assertThat(sql2oFilmSessionRepository.findAll())
                .hasSize(9)
                .element(5)
                .usingRecursiveComparison().isEqualTo(filmSession);
    }

    @Test
    public void whenFindByIdSessionThenGetSame() {
        var startTime = LocalDateTime.of(2023, 9, 25, 13, 00);
        var endTime = LocalDateTime.of(2023, 9, 25, 16, 0);
        FilmSession session = new FilmSession(6, 2, 2, startTime, endTime, 450);
        assertThat(sql2oFilmSessionRepository.findById(6).get())
                .usingRecursiveComparison().isEqualTo(session);
    }
}