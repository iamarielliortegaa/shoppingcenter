package com.shopping.controller;

import com.shopping.data.DataHandler;
import com.shopping.data.Product;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Optional;

public class ProductManagementController {

    @FXML
    private TableView<Product> productTableView;

    DataHandler dataHandler;

    @FXML
    private Button removeButton;

    public ProductManagementController(DataHandler dataHandler)
    {
        this.dataHandler = dataHandler;
    }

    @FXML
    protected void onAddProductButtonClick() throws IOException{
        try {
            AddProductDialogController dialog = new AddProductDialogController(dataHandler);
            dialog.showAndWait().ifPresent(result -> {
                try {
                    addProduct(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception ex) { }
    }

    private void addProduct(Product product) throws IOException
    {
        if(product != null) {
            dataHandler.addProduct(product);
        }
    }

    @FXML
    protected void onRemoveProductButtonClick() throws IOException {

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Confirmation");
        dialog.setContentText("Are you sure you want to remove ?");

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.get() == ButtonType.OK) {
            Product product = productTableView.getSelectionModel().getSelectedItem();
            if(product != null) {
                this.dataHandler.deleteProduct(product);
            }
        }
    }

    @FXML
    private void initialize() {
        TableColumn<Product, Integer> column1 = new TableColumn<>("Product ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("Id"));
        productTableView.getColumns().add(column1);

        TableColumn<Product, String> column2 = new TableColumn<>("Part Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        productTableView.getColumns().add(column2);

        TableColumn<Product, Integer> column3 = new TableColumn<>("Inventory Level");
        column3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productTableView.getColumns().add(column3);

        TableColumn<Product, Double> column4 = new TableColumn<>("Price/ Cost per Unit");
        column4.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTableView.getColumns().add(column4);

        productTableView.setItems(this.dataHandler.getAllProducts());

        removeButton.setVisible(this.dataHandler.getAllowedToBeRemoved());
    }

}
