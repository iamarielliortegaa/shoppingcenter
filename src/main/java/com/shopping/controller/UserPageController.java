
package com.shopping.controller;

import com.shopping.data.Customer;
import com.shopping.data.DataHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class UserPageController {
    @FXML
    private Label welcomeText;
    private DataHandler dataHandler;

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private Button loginButton;

    @FXML
    private Button viewProductsButton;

    @FXML
    private Button purchaseButton;

    public UserPageController() throws IOException {
        this.dataHandler = new DataHandler(false);
    }

    @FXML
    private void initialize() {

        customerComboBox.setItems(dataHandler.getAllCustomers());


        customerComboBox.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getId() + " - " + item.getName());
                }
            }
        });
        customerComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getId() + " - " + item.getName());
                }
            }
        });
    }

    @FXML
    private void onLoginClick() {

        Customer selected = customerComboBox.getSelectionModel().getSelectedItem();

        if (selected == null) {
            return;
        }

        dataHandler.setSelectedCustomer(selected);

        customerComboBox.setDisable(true);
        loginButton.setDisable(true);

        viewProductsButton.setVisible(true);
        viewProductsButton.setManaged(true);

        purchaseButton.setVisible(true);
        purchaseButton.setManaged(true);

        welcomeText.setText("Shopping Center - " + selected.getName());
    }

    @FXML
    protected void onManageProductsClick() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/shopping/controller/project-management-view.fxml"));

        ProductManagementController controller = new ProductManagementController();
        controller.setDataHandler(this.dataHandler);
        loader.setController(controller);

        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Product Management");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void onPurchaseProductsClick() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/shopping/controller/product-purchase-view.fxml"));

        ProductPurchaseController controller = new ProductPurchaseController();

        controller.setDataHandler(this.dataHandler);

        loader.setController(controller);



        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Product Purchase");
        stage.setScene(new Scene(root));
        stage.show();
    }
}