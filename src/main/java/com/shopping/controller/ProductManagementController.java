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

    @FXML
    private Button addButton;

    public ProductManagementController()
    {

    }

    public void setDataHandler(DataHandler handler) {
        this.dataHandler = handler;
    }

    @FXML
    protected void onAddProductButtonClick() {
        try {
            AddProductDialogController dialog = new AddProductDialogController();
            dialog.showAndWait().ifPresent(result -> {
                try {
                    addProduct(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception ex)
        {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setHeaderText("Invalid Product Data");
            alert2.setContentText(ex.toString());
            alert2.showAndWait();
        }
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
        TableColumn<Product, Integer> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("Id"));
        column1.prefWidthProperty().bind(productTableView.widthProperty().multiply(0.10));
        productTableView.getColumns().add(column1);

        TableColumn<Product, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.prefWidthProperty().bind(productTableView.widthProperty().multiply(0.45));
        productTableView.getColumns().add(column2);

        TableColumn<Product, Integer> column3 = new TableColumn<>("Stock");
        column3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        column3.prefWidthProperty().bind(productTableView.widthProperty().multiply(0.15));
        productTableView.getColumns().add(column3);

        TableColumn<Product, Double> column4 = new TableColumn<>("Price");
        column4.setCellValueFactory(new PropertyValueFactory<>("price"));
        column4.prefWidthProperty().bind(productTableView.widthProperty().multiply(0.30));
        productTableView.getColumns().add(column4);

        productTableView.setItems(this.dataHandler.getAllProducts());

        addButton.setVisible(this.dataHandler.getAllowedToBeRemoved());

        removeButton.setVisible(this.dataHandler.getAllowedToBeRemoved());
    }

}
