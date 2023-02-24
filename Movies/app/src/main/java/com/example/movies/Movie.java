package com.example.movies;

public class Movie {

    private int id;
    private String filmTitleColumn;
    private String filmReleaseYearColumn;
    private String filmDurationColumn;
    private String filmDirectorColumn;
    private String filmDistributedByColumn;
    private String filmImageColumn;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFilmTitleColumn() {
        return filmTitleColumn;
    }

    public void setFilmTitleColumn(String filmTitleColumn) {
        this.filmTitleColumn = filmTitleColumn;
    }

    public String getFilmReleaseYearColumn() {
        return filmReleaseYearColumn;
    }

    public void setFilmReleaseYearColumn(String filmReleaseYearColumn) {
        this.filmReleaseYearColumn = filmReleaseYearColumn;
    }

    public String getFilmDurationColumn() {
        return filmDurationColumn;
    }

    public void setFilmDurationColumn(String filmDurationColumn) {
        this.filmDurationColumn = filmDurationColumn;
    }

    public String getFilmDirectorColumn() {
        return filmDirectorColumn;
    }

    public void setFilmDirectorColumn(String filmDirectorColumn) {
        this.filmDirectorColumn = filmDirectorColumn;
    }

    public String getFilmDistributedByColumn() {
        return filmDistributedByColumn;
    }

    public void setFilmDistributedByColumn(String filmDistributedByColumn) {
        this.filmDistributedByColumn = filmDistributedByColumn;
    }

    public String getFilmImageColumn() {
        return filmImageColumn;
    }

    public void setFilmImageColumn(String filmImageColumn) {
        this.filmImageColumn = filmImageColumn;
    }

}
