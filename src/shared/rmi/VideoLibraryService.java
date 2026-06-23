package shared.rmi;

import shared.model.Customer;
import shared.model.Genre;
import shared.model.Movie;
import shared.model.Rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface VideoLibraryService extends Remote {

    String SERVICE_NAME = "VideoLibraryService";
    int DEFAULT_PORT = 1099;

    void saveCustomer(Customer customer) throws RemoteException;

    void deleteCustomer(int customerId) throws RemoteException;

    List<Customer> getCustomers() throws RemoteException;

    void addGenre(Genre genre) throws RemoteException;

    void deleteGenre(int genreId) throws RemoteException;

    List<Genre> getGenres() throws RemoteException;

    void addMovie(Movie movie) throws RemoteException;

    void deleteMovie(int movieId) throws RemoteException;

    List<Movie> getMovies() throws RemoteException;

    List<Movie> getAvailableMoviesByGenre(int genreId) throws RemoteException;

    boolean rentMovie(int customerId, int movieId) throws RemoteException;

    boolean returnMovie(int rentalId) throws RemoteException;

    List<Rental> getBorrowedRentals(int customerId) throws RemoteException;

    List<Rental> getReturnedRentals(int customerId) throws RemoteException;

    List<Rental> getRentalHistory(int customerId) throws RemoteException;
}