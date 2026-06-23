package shared.model;

import java.io.Serializable;

public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private int genreId;
    private String genreName;

    // For display (JOIN)
    public Movie(int id, String title, String genreName) {
        this.id = id;
        this.title = title;
        this.genreName = genreName;
    }

    // For insert
    public Movie(String title, int genreId) {
        this.title = title;
        this.genreId = genreId;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getGenreId() { return genreId; }
    public String getGenreName() { return genreName; }

    @Override
    public String toString() {
        return title;
    }
}