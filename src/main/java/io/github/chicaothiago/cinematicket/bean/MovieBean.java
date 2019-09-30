package io.github.chicaothiago.cinematicket.bean;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieBean extends DefaultBean {
    /**
     * No getters/setters (SOLID principles)
     * However JSP needs getters...
     */
    public Integer id;
    public String name = "";
    public String url_image = "";
    public HashMap<String, CinemaBean> cinemas = new HashMap<>();
    public String hash;

    public MovieBean() {
        this.table = "movies";
        this.countFields += 4;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl_image() {
        return url_image;
    }

    public HashMap<String, CinemaBean> getCinemas() {
        return cinemas;
    }
}
