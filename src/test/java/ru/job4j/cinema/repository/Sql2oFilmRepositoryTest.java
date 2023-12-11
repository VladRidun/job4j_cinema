package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Film;

import java.util.Optional;
import java.util.Properties;

class Sql2oFilmRepositoryTest {
    private static Sql2oFilmRepository sql2oFilmRepository;

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
        sql2oFilmRepository = new Sql2oFilmRepository(sql2o);
    }

    @Test
    public void whenFindAllFilmsThenGetSame() {
        Film film = new Film(1, "007:Не время умирать", "Джеймс Бонд оставил оперативную службу и наслаждается спокойной жизнью на Ямайке",
                2021, 1, 12, 163, 1);
        assertThat(sql2oFilmRepository.findAll())
                .hasSize(6)
                .element(0)
                .usingRecursiveComparison().isEqualTo(film);
    }

    @Test
    public void whenFindByIdFilmThenGetSame() {
        Film film = new Film(1, "007:Не время умирать", "Джеймс Бонд оставил оперативную службу и наслаждается спокойной жизнью на Ямайке",
                2021, 1, 12, 163, 1);
        assertThat(sql2oFilmRepository.findById(film.getId()).get())
                .usingRecursiveComparison().isEqualTo(film);
    }
}
