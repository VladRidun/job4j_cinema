package ru.job4j.cinema.dto;

import lombok.Data;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Hall;


import java.util.Objects;

@Data
public class FilmSessionDto {
    private int id;
    private Film film;
    private Hall hall;
    private String startTime;
    private String endTime;
    private int price;

    public FilmSessionDto() {
    }

    public FilmSessionDto(int id, Film film, Hall hall, String startTime,
                          String endTime, int price) {
        this.id = id;
        this.film = film;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
