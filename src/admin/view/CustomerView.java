package admin.view;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shared.controller.CustomerController;
import shared.model.Customer;

/**
 * JavaFX screen for registering and removing customers.
 */
public class CustomerView {

    private TextField txtName;
    private TextField txtPhone;
    private TextField txtEmail;
    private ListView<Customer> list;
    private CustomerController controller;

    public CustomerView() {
        controller = new CustomerController();
    }

    /**
     * Displays the customer management screen.
     *
     * @param stage JavaFX stage
     */
    public void show(Stage stage) {

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setStyle("-fx-padding: 20;");

        txtName = new TextField();
        txtPhone = new TextField();
        txtEmail = new TextField();

        txtName.setPromptText("Full name");
        txtPhone.setPromptText("Phone number");
        txtEmail.setPromptText("Email address");

        Button btnSave =
                new Button("Save Customer");

        Button btnRemove =
                new Button("Remove Customer");

        Button btnRefresh =
                new Button("Refresh");

        Button btnClose =
                new Button("Close");

        list = new ListView<>();

        root.add(new Label("Name"),0,0);
        root.add(txtName,1,0);

        root.add(new Label("Phone"),0,1);
        root.add(txtPhone,1,1);

        root.add(new Label("Email"),0,2);
        root.add(txtEmail,1,2);

        HBox buttons = new HBox(10, btnSave, btnRemove, btnRefresh, btnClose);
        root.add(buttons,1,3);

        root.add(new Label("Registered"),0,4);
        root.add(list,1,4);

        btnSave.setOnAction(e -> saveCustomer());
        btnRemove.setOnAction(e -> removeCustomer());
        btnRefresh.setOnAction(e -> loadCustomers());
        btnClose.setOnAction(e -> stage.close());

        loadCustomers();

        VBox wrapper = new VBox(10, new Label("Customer Management"), root);

        stage.setScene(
                new Scene(wrapper,650,500)
        );
        stage.setTitle("Customers");

        stage.show();
    }

    private void saveCustomer() {
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }

        controller.saveCustomer(new Customer(name, phone, email));
        txtName.clear();
        txtPhone.clear();
        txtEmail.clear();
        loadCustomers();
    }

    private void removeCustomer() {
        Customer selected = list.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Select a customer to remove.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Remove");
        confirm.setContentText("Remove selected customer?");

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                controller.removeCustomer(selected);
                loadCustomers();
            }
        });
    }

    private void loadCustomers() {
        list.setItems(FXCollections.observableArrayList(controller.getCustomers()));
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Customer Validation");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}