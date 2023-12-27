package ru.job4j.cinema.dto;

import lombok.Data;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.Objects;

@Data
public class SessionPlaceDto {
    private int id;
    private Film film;
    private String startTime;
    private String endTime;
    private int price;
    private Collection<Integer> rowCount;
    private Collection<Integer> placeInRowCount;

    public SessionPlaceDto() {
    }

    public SessionPlaceDto(int id, Film film, String startTime, String endTime,
                           int price, Collection<Integer> rowCount, Collection<Integer> placeInRowCount) {
        this.id = id;
        this.film = film;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.rowCount = rowCount;
        this.placeInRowCount = placeInRowCount;
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

    public Collection<Integer> getRowCount() {
        return rowCount;
    }

    public void setRowCount(Collection<Integer> rowCount) {
        this.rowCount = rowCount;
    }

    public Collection<Integer> getPlaceInRowCount() {
        return placeInRowCount;
    }

    public void setPlaceInRowCount(Collection<Integer> placeInRowCount) {
        this.placeInRowCount = placeInRowCount;
    }
}
