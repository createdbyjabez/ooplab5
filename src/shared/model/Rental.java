package shared.model;

import java.io.Serializable;

/**
 * Represents a movie rental transaction.
 */
public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int customerId;
    private int movieId;
    private String customerName;
    private String movieTitle;
    private String genreName;
    private boolean returned;
    private String rentedAt;
    private String returnedAt;

    public Rental(
            int id,
            int customerId,
            int movieId,
            String customerName,
            String movieTitle,
            String genreName,
            boolean returned,
            String rentedAt,
            String returnedAt) {

        this.id = id;
        this.customerId = customerId;
        this.movieId = movieId;
        this.customerName = customerName;
        this.movieTitle = movieTitle;
        this.genreName = genreName;
        this.returned = returned;
        this.rentedAt = rentedAt;
        this.returnedAt = returnedAt;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getGenreName() {
        return genreName;
    }

    public boolean isReturned() {
        return returned;
    }

    public String getStatus() {
        return returned ? "Returned" : "Borrowed";
    }

    public String getRentedAt() {
        return rentedAt;
    }

    public String getReturnedAt() {
        return returnedAt == null ? "" : returnedAt;
    }

    @Override
    public String toString() {
        return movieTitle + " (" + getStatus() + ")";
    }
}