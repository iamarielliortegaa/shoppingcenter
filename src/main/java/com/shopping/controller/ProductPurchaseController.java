package com.shopping.controller;

import com.shopping.data.DataHandler;
import com.shopping.data.Product;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Optional;

public class ProductPurchaseController {

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableView<Product> cartTableView;

    DataHandler dataHandler;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button purchaseButton;

    public ProductPurchaseController()
    {

    }

    public void setDataHandler(DataHandler handler) {

        this.dataHandler = handler;


    }

    @FXML
    protected void onAddtoCartButtonClick() {
        Product selected = productTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            return;
        }

        if (selected.getStock() <= 0) {
            return;
        }

        selected.setStock(selected.getStock() - 1);
        productTableView.refresh();

        Product cartItem = new Product(selected.getId(), selected.getName(), selected.getPrice(), 1);
        dataHandler.getSelectedCustomer().addToCart(cartItem);
    }


    @FXML
    protected void onRemoveProductButtonClick() throws IOException {

        Product selected = cartTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            return;
        }

        dataHandler.getSelectedCustomer().removeFromCart(selected);

        Product product = null;

        for (Product p : dataHandler.getAllProducts()) {
            if (p.getId() == selected.getId()) product = p;
        }

        if (product != null) {
            product.setStock(product.getStock() + 1);
        }

        productTableView.refresh();
    }

    @FXML
    protected void onPurchaseClick() throws IOException {
        if (dataHandler.getSelectedCustomer() == null ||
                dataHandler.getSelectedCustomer().getCartProducts().isEmpty()) {
            showInfo("Cart is empty", "There are no items to purchase.");
            return;
        }

        double total = 0.0;

        for (Product p : dataHandler.getSelectedCustomer().getCartProducts()) {
            total += p.getPrice() * p.getStock();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Purchase Successful");
        alert.setHeaderText("Thank you for your purchase!");
        alert.setContentText("Total amount: $" + String.format("%.2f", total));
        alert.showAndWait();

        dataHandler.getSelectedCustomer().resetCart();
    }

    private void showInfo(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
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

        TableColumn<Product, Integer> column3 = new TableColumn<>("Qty");
        column3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        column3.prefWidthProperty().bind(productTableView.widthProperty().multiply(0.15));
        productTableView.getColumns().add(column3);

        TableColumn<Product, Double> column4 = new TableColumn<>("Price");
        column4.setCellValueFactory(new PropertyValueFactory<>("price"));
        column4.prefWidthProperty().bind(productTableView.widthProperty().multiply(0.30));
        productTableView.getColumns().add(column4);

        productTableView.setItems(this.dataHandler.getAllProducts());

        initializeCartTable();

        productTableView.setItems(dataHandler.getAllProducts());

        if (dataHandler.getSelectedCustomer() != null) {
            dataHandler.getSelectedCustomer().resetCart();
            cartTableView.setItems(dataHandler.getSelectedCustomer().getCartProducts());
        }
    }

    private void initializeCartTable() {
        TableColumn<Product, Integer> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("Id"));
        column1.prefWidthProperty().bind(cartTableView.widthProperty().multiply(0.10));
        cartTableView.getColumns().add(column1);

        TableColumn<Product, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.prefWidthProperty().bind(cartTableView.widthProperty().multiply(0.45));
        cartTableView.getColumns().add(column2);

        TableColumn<Product, Integer> column3 = new TableColumn<>("Stock");
        column3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        column3.prefWidthProperty().bind(cartTableView.widthProperty().multiply(0.15));
        cartTableView.getColumns().add(column3);

        TableColumn<Product, Double> column4 = new TableColumn<>("Price");
        column4.setCellValueFactory(new PropertyValueFactory<>("price"));
        column4.prefWidthProperty().bind(cartTableView.widthProperty().multiply(0.30));
        cartTableView.getColumns().add(column4);

    }

}
