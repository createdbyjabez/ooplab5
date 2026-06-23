package shared.controller;

import shared.model.Genre;
import shared.model.Movie;
import shared.rmi.RmiClient;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

/**
 * Coordinates movie UI actions with the remote VLS server.
 */
public class MovieController {

    public void addMovie(Movie movie) {
        try {
            RmiClient.getService().addMovie(movie);
        } catch (RemoteException e) {
            throw new IllegalStateException("Could not add movie through RMI.", e);
        }
    }

    public void deleteMovie(int movieId) {
        try {
            RmiClient.getService().deleteMovie(movieId);
        } catch (RemoteException e) {
            throw new IllegalStateException("Could not delete movie through RMI.", e);
        }
    }

    public List<Movie> getMovies() {
        try {
            return RmiClient.getService().getMovies();
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Genre> getGenres() {
        try {
            return RmiClient.getService().getGenres();
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}