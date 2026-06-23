package shared.controller;

import shared.model.Genre;
import shared.rmi.RmiClient;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;


public class GenreController {

    public void addGenre(Genre genre) {
        try {
            RmiClient.getService().addGenre(genre);
        } catch (RemoteException e) {
            throw new IllegalStateException("Could not add genre through RMI.", e);
        }
    }

    public void deleteGenre(int genreId) {
        try {
            RmiClient.getService().deleteGenre(genreId);
        } catch (RemoteException e) {
            throw new IllegalStateException("Could not delete genre through RMI.", e);
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