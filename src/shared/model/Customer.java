package shared.model;

import java.io.Serializable;

/**
 * Represents a customer registered in the video library system.
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String phone;
    private String email;

    public Customer(
            int id,
            String name,
            String phone,
            String email) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Customer(
            String name,
            String phone,
            String email) {

        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name + " | " + phone + " | " + email;
    }
}