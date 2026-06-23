package admin.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shared.controller.GenreController;
import shared.model.Genre;

import java.util.List;

public class GenreView {

    private TableView<Genre> table;
    private TextField txtName;
    private GenreController controller;

    public GenreView() {
        controller = new GenreController();
    }

    public void show(Stage stage) {

        // Input
        txtName = new TextField();
        txtName.setPromptText("Enter genre name");

        // Buttons
        Button btnAdd = new Button("Add Genre");
        Button btnDelete = new Button("Delete Selected");
        Button btnRefresh = new Button("Refresh");
        Button btnClose = new Button("Close");

        // Table
        table = new TableView<>();

        TableColumn<Genre, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getId()
                ).asObject()
        );

        TableColumn<Genre, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getName()
                )
        );

        table.getColumns().addAll(colId, colName);

        // Load data
        loadData();

        // ADD
        btnAdd.setOnAction(e -> {
            String name = txtName.getText();

            if (name.isEmpty()) {
                showAlert("Error", "Name cannot be empty!");
                return;
            }

            controller.addGenre(new Genre(name));
            txtName.clear();
            loadData();
        });

        // DELETE
        btnDelete.setOnAction(e -> {
            Genre selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showAlert("Error", "Select a genre to delete!");
                return;
            }

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Delete");
            confirm.setContentText("Delete selected genre?");

            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    controller.deleteGenre(selected.getId());
                    loadData();
                }
            });
        });

        // REFRESH
        btnRefresh.setOnAction(e -> loadData());

        // CLOSE
        btnClose.setOnAction(e -> stage.close());

        // Layout
        VBox root = new VBox(10);
        root.getChildren().addAll(
                new Label("Genre Management"),
                txtName,
                btnAdd,
                btnDelete,
                btnRefresh,
                table,
                btnClose
        );

        Scene scene = new Scene(root, 400, 450);

        stage.setTitle("Genres");
        stage.setScene(scene);
        stage.show();
    }

    private void loadData() {
        List<Genre> list = controller.getGenres();
        ObservableList<Genre> data =
                FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}