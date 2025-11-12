package com.shopping.controller;

import com.shopping.data.DataHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Parent;

import java.io.IOException;

public class AdminPageController {
    @FXML
    private Label welcomeText;
    private DataHandler dataHandler;

    public AdminPageController() throws IOException {
        this.dataHandler = new DataHandler(true);

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
    protected void onManageCustomerClick() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/shopping/controller/customer-management-view.fxml"));

        CustomerManagementController controller = new CustomerManagementController(this.dataHandler);
        loader.setController(controller);

        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Customer Management");
        stage.setScene(new Scene(root));
        stage.show();
    }
}