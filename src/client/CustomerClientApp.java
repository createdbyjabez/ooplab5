package client;

import client.view.CustomerDashboardView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point for the VLS customer client.
 */
public class CustomerClientApp extends Application {

    @Override
    public void start(Stage stage) {
        new CustomerDashboardView().show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}