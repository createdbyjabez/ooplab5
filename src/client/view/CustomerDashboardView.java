package client.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main screen for the customer-side VLS client.
 */
public class CustomerDashboardView {

    /**
     * Displays the customer dashboard.
     *
     * @param stage JavaFX stage
     */
    public void show(Stage stage) {
        Label title = new Label("Video Library System");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label subtitle = new Label("Customer Client");
        subtitle.setStyle("-fx-font-size: 13px;");

        Button btnRentals = createMenuButton("Rent / Return Movies");
        Button btnExit = createMenuButton("Exit");

        btnRentals.setOnAction(e ->
                new RentalView().show(new Stage())
        );

        btnExit.setOnAction(e -> stage.close());

        VBox root = new VBox(15, title, subtitle, btnRentals, btnExit);
        root.setStyle("-fx-padding: 25; -fx-alignment: center; -fx-background-color: #f5f7fb;");

        stage.setTitle("Customer Client");
        stage.setScene(new Scene(root, 360, 240));
        stage.show();
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(40);
        return button;
    }
}