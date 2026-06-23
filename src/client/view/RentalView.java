package client.view;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shared.controller.RentalController;
import shared.model.Customer;
import shared.model.Genre;
import shared.model.Movie;
import shared.model.Rental;

/**
 * Customer rental management screen.
 */
public class RentalView {

    private ComboBox<Customer> cbCustomer;
    private ComboBox<Genre> cbGenre;
    private ListView<Movie> availableMovies;
    private ListView<Rental> borrowedMovies;
    private TableView<Rental> historyTable;
    private TableView<Rental> returnedTable;
    private RentalController controller;

    public RentalView() {
        controller = new RentalController();
    }

    /**
     * Displays the rental management screen.
     *
     * @param stage JavaFX stage
     */
    public void show(Stage stage) {
        cbCustomer = new ComboBox<>();
        cbGenre = new ComboBox<>();
        availableMovies = new ListView<>();
        borrowedMovies = new ListView<>();
        historyTable = createRentalTable();
        returnedTable = createRentalTable();

        configureComboCells();
        loadCustomers();
        loadGenres();

        Button btnRent = new Button("Rent Movie");
        Button btnReturn = new Button("Return Movie");
        Button btnRefresh = new Button("Refresh");
        Button btnClose = new Button("Close");

        cbCustomer.setOnAction(e -> refreshRentalLists());
        cbGenre.setOnAction(e -> loadAvailableMovies());
        btnRent.setOnAction(e -> rentSelectedMovie());
        btnReturn.setOnAction(e -> returnSelectedMovie());
        btnRefresh.setOnAction(e -> refreshAll());
        btnClose.setOnAction(e -> stage.close());

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.add(new Label("Customer:"), 0, 0);
        form.add(cbCustomer, 1, 0);
        form.add(new Label("Genre:"), 0, 1);
        form.add(cbGenre, 1, 1);

        VBox availableBox = new VBox(8, new Label("Available Movies"), availableMovies, btnRent);
        VBox borrowedBox = new VBox(8, new Label("Borrowed Movies"), borrowedMovies, btnReturn);
        HBox movieLists = new HBox(15, availableBox, borrowedBox);

        TabPane tabPane = new TabPane();
        Tab returnedTab = new Tab("Returned Movies", returnedTable);
        Tab historyTab = new Tab("Rental History", historyTable);
        returnedTab.setClosable(false);
        historyTab.setClosable(false);
        tabPane.getTabs().addAll(returnedTab, historyTab);

        HBox actions = new HBox(10, btnRefresh, btnClose);
        VBox root = new VBox(12,
                new Label("Rental Management"),
                form,
                movieLists,
                tabPane,
                actions
        );
        root.setStyle("-fx-padding: 18;");

        stage.setTitle("Rental Management");
        stage.setScene(new Scene(root, 850, 650));
        stage.show();

        refreshAll();
    }

    private TableView<Rental> createRentalTable() {
        TableView<Rental> table = new TableView<>();

        TableColumn<Rental, String> colMovie = new TableColumn<>("Movie");
        colMovie.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getMovieTitle()));

        TableColumn<Rental, String> colGenre = new TableColumn<>("Genre");
        colGenre.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getGenreName()));

        TableColumn<Rental, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));

        TableColumn<Rental, String> colRented = new TableColumn<>("Rented At");
        colRented.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getRentedAt()));

        TableColumn<Rental, String> colReturned = new TableColumn<>("Returned At");
        colReturned.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getReturnedAt()));

        table.getColumns().addAll(colMovie, colGenre, colStatus, colRented, colReturned);
        table.setPrefHeight(220);
        return table;
    }

    private void configureComboCells() {
        cbCustomer.setButtonCell(new ListCell<Customer>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getName());
            }
        });

        cbCustomer.setCellFactory(list -> new ListCell<Customer>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getName());
            }
        });

        cbGenre.setButtonCell(new ListCell<Genre>() {
            @Override
            protected void updateItem(Genre item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getName());
            }
        });

        cbGenre.setCellFactory(list -> new ListCell<Genre>() {
            @Override
            protected void updateItem(Genre item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getName());
            }
        });
    }

    private void loadCustomers() {
        cbCustomer.setItems(FXCollections.observableArrayList(controller.getCustomers()));
        if (!cbCustomer.getItems().isEmpty() && cbCustomer.getValue() == null) {
            cbCustomer.getSelectionModel().selectFirst();
        }
    }

    private void loadGenres() {
        cbGenre.setItems(FXCollections.observableArrayList(controller.getGenres()));
        if (!cbGenre.getItems().isEmpty() && cbGenre.getValue() == null) {
            cbGenre.getSelectionModel().selectFirst();
        }
    }

    private void loadAvailableMovies() {
        availableMovies.setItems(FXCollections.observableArrayList(
                controller.getAvailableMoviesByGenre(cbGenre.getValue())
        ));
    }

    private void refreshRentalLists() {
        Customer customer = cbCustomer.getValue();
        borrowedMovies.setItems(FXCollections.observableArrayList(controller.getBorrowedRentals(customer)));
        returnedTable.setItems(FXCollections.observableArrayList(controller.getReturnedRentals(customer)));
        historyTable.setItems(FXCollections.observableArrayList(controller.getRentalHistory(customer)));
    }

    private void refreshAll() {
        loadCustomers();
        loadGenres();
        loadAvailableMovies();
        refreshRentalLists();
    }

    private void rentSelectedMovie() {
        Customer customer = cbCustomer.getValue();
        Movie movie = availableMovies.getSelectionModel().getSelectedItem();

        if (customer == null) {
            showWarning("Select a customer.");
            return;
        }

        if (movie == null) {
            showWarning("Select an available movie.");
            return;
        }

        if (!controller.rentMovie(customer, movie)) {
            showWarning("Movie could not be rented. It may already be borrowed.");
            refreshAll();
            return;
        }

        refreshAll();
    }

    private void returnSelectedMovie() {
        Rental rental = borrowedMovies.getSelectionModel().getSelectedItem();

        if (rental == null) {
            showWarning("Select a borrowed movie to return.");
            return;
        }

        if (!controller.returnMovie(rental)) {
            showWarning("Movie could not be returned.");
            refreshAll();
            return;
        }

        refreshAll();
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Rental Management");
        alert.setContentText(message);
        alert.showAndWait();
    }
}