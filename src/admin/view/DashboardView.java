package admin.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main navigation window for the VLS admin client.
 */
public class DashboardView {

    /**
     * Displays the admin dashboard.
     *
     * @param stage primary JavaFX stage
     */
    public void show(Stage stage) {
        Label title = new Label("Video Library System");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label subtitle = new Label("Admin Dashboard");
        subtitle.setStyle("-fx-font-size: 13px;");

        Button btnGenres = createMenuButton("Manage Genres");
        Button btnMovies = createMenuButton("Manage Movies");
        Button btnCustomers = createMenuButton("Manage Customers");
        Button btnExit = new Button("Exit");
        btnExit.setMaxWidth(Double.MAX_VALUE);

        btnGenres.setOnAction(e ->
                new GenreView().show(new Stage())
        );

        btnMovies.setOnAction(e ->
                new MovieView().show(new Stage())
        );

        btnCustomers.setOnAction(e ->
                new CustomerView().show(new Stage())
        );

        btnExit.setOnAction(e ->
                stage.close()
        );

        VBox root = new VBox(15);
        root.setStyle("-fx-padding: 25; -fx-alignment: center; -fx-background-color: #f5f7fb;");

        root.getChildren().addAll(
                title,
                subtitle,
                btnGenres,
                btnMovies,
                btnCustomers,
                btnExit
        );
        VBox.setVgrow(btnGenres, Priority.NEVER);

        Scene scene =
                new Scene(root, 360, 300);

        stage.setTitle(
                "Admin Dashboard"
        );

        stage.setScene(scene);

        stage.show();
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(40);
        return button;
    }
}