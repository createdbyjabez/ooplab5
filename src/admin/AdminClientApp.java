package admin;

import admin.view.DashboardView;
import javafx.application.Application;
import javafx.stage.Stage;


public class AdminClientApp extends Application {

    @Override
    public void start(Stage stage) {
        new DashboardView().show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}