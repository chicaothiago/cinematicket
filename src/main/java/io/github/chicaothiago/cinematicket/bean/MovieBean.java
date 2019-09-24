package io.github.chicaothiago.cinematicket.bean;

public class MovieBean extends DefaultBean{
    /**
     * No getters/setters (SOLID principles)
     * However JSP needs getters...
     */
    public String name;
    public String url_image;

    public MovieBean() {
        this.countFields += 2;
    }

    public String getName() {
        return name;
    }

    public String getUrl_image() {
        return url_image;
    }
}
