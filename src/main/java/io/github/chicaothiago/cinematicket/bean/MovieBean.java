package io.github.chicaothiago.cinematicket.bean;

public class MovieBean extends DefaultBean {
    /**
     * No getters/setters (SOLID principles)
     * However JSP needs getters...
     */
    public Integer id;
    public String name;
    public String url_image;

    public MovieBean() {
        this.countFields += 3;
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
}
