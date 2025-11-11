
package com.shopping.controller;

import com.shopping.data.DataHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserPageController {
    @FXML
    private Label welcomeText;
    private DataHandler dataHandler;

    public UserPageController() throws IOException {
        this.dataHandler = new DataHandler(false);

    }
    @FXML
    protected void onManageProductsClick() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/shopping/shoppingcenter/project-management-view.fxml"));

        ProductManagementController controller = new ProductManagementController(this.dataHandler);
        loader.setController(controller);

        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Product Management");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void onManageCustomerClick() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/shopping/shoppingcenter/customer-management-view.fxml"));

        CustomerManagementController controller = new CustomerManagementController(this.dataHandler);
        loader.setController(controller);

        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Customer Management");
        stage.setScene(new Scene(root));
        stage.show();
    }
}