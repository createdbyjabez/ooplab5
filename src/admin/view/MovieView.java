package admin.view;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shared.controller.MovieController;
import shared.model.Genre;
import shared.model.Movie;

import java.util.List;

public class MovieView {

    private TextField txtTitle;
    private ComboBox<Genre> cbGenre;
    private TableView<Movie> table;

    private MovieController controller = new MovieController();

    public void show(Stage stage) {

        txtTitle = new TextField();
        txtTitle.setPromptText("Movie title");

        cbGenre = new ComboBox<>();
        loadGenres();

        Button btnAdd = new Button("Add Movie");
        Button btnDelete = new Button("Delete Selected");
        Button btnRefresh = new Button("Refresh");

        table = new TableView<>();

        // Columns
        TableColumn<Movie, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getId()).asObject());

        TableColumn<Movie, String> colTitle = new TableColumn<>("Title");
        colTitle.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getTitle()));

        TableColumn<Movie, String> colGenre = new TableColumn<>("Genre");
        colGenre.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getGenreName()));

        table.getColumns().addAll(colId, colTitle, colGenre);

        loadMovies();

        // ADD
        btnAdd.setOnAction(e -> {
            String title = txtTitle.getText();
            Genre selected = cbGenre.getValue();

            if (title.isEmpty() || selected == null) {
                showAlert("Fill all fields!");
                return;
            }

            controller.addMovie(new Movie(title, selected.getId()));
            txtTitle.clear();
            loadMovies();
        });

        // DELETE
        btnDelete.setOnAction(e -> {
            Movie selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showAlert("Select a movie!");
                return;
            }

            controller.deleteMovie(selected.getId());
            loadMovies();
        });

        // REFRESH
        btnRefresh.setOnAction(e -> loadMovies());

        VBox root = new VBox(10,
                new Label("Movie Management"),
                txtTitle,
                cbGenre,
                btnAdd,
                btnDelete,
                btnRefresh,
                table
        );

        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle("Movies");
        stage.show();
    }

    private void loadGenres() {
        List<Genre> list = controller.getGenres();
        cbGenre.setItems(FXCollections.observableArrayList(list));

        cbGenre.setCellFactory(lv -> new ListCell<Genre>() {
            @Override
            protected void updateItem(Genre item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });

        cbGenre.setButtonCell(new ListCell<Genre>() {
            @Override
            protected void updateItem(Genre item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });
    }

    private void loadMovies() {
        List<Movie> list = controller.getMovies();
        table.setItems(FXCollections.observableArrayList(list));
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}