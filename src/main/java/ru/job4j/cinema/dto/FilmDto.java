package ru.job4j.cinema.dto;
import lombok.Data;

@Data
public class FilmDto {
    private int id;
    private String title;
    private String description;
    private int year;
    private int minimalAge;
    private int duration;
    private String genre;
    private int fileId;

    public FilmDto(int id, String title, String description, int year, int minimalAge,
                   int duration, String genre, int fileId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.minimalAge = minimalAge;
        this.duration = duration;
        this.genre = genre;
        this.fileId = fileId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
}
