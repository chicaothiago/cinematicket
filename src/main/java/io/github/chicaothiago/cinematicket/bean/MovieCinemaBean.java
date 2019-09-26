package io.github.chicaothiago.cinematicket.bean;

public class MovieCinemaBean extends DefaultBean {
    public Integer id;
    public Integer cinema_id;
    public Integer movie_id;

    public MovieCinemaBean() {
        this.countFields += 3;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCinema_id() {
        return cinema_id;
    }

    public Integer getMovie_id() {
        return movie_id;
    }
}
