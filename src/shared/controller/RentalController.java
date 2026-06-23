package shared.controller;

import shared.model.Customer;
import shared.model.Genre;
import shared.model.Movie;
import shared.model.Rental;
import shared.rmi.RmiClient;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

/**
 * Coordinates rental UI actions with persistence.
 */
public class RentalController {

    public List<Customer> getCustomers() {
        try {
            return RmiClient.getService().getCustomers();
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

    public List<Movie> getAvailableMoviesByGenre(Genre genre) {
        if (genre == null) {
            return java.util.Collections.emptyList();
        }

        try {
            return RmiClient.getService().getAvailableMoviesByGenre(genre.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public boolean rentMovie(Customer customer, Movie movie) {
        if (customer == null || movie == null) {
            return false;
        }

        try {
            return RmiClient.getService().rentMovie(customer.getId(), movie.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean returnMovie(Rental rental) {
        if (rental == null) {
            return false;
        }

        try {
            return RmiClient.getService().returnMovie(rental.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Rental> getBorrowedRentals(Customer customer) {
        if (customer == null) {
            return java.util.Collections.emptyList();
        }

        try {
            return RmiClient.getService().getBorrowedRentals(customer.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Rental> getReturnedRentals(Customer customer) {
        if (customer == null) {
            return java.util.Collections.emptyList();
        }

        try {
            return RmiClient.getService().getReturnedRentals(customer.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Rental> getRentalHistory(Customer customer) {
        if (customer == null) {
            return java.util.Collections.emptyList();
        }

        try {
            return RmiClient.getService().getRentalHistory(customer.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}