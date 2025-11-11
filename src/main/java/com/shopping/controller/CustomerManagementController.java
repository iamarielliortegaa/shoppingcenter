package com.shopping.controller;

import com.shopping.data.Customer;
import com.shopping.data.DataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Optional;

public class CustomerManagementController {

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private Button removeButton;

    DataHandler dataHandler;

    public CustomerManagementController(DataHandler dataHandler)
    {
        this.dataHandler = dataHandler;
    }

    @FXML
    protected void onAddCustomerButtonClick() {
        try {
            AddCustomerDialogController dialog = new AddCustomerDialogController(dataHandler);
            dialog.showAndWait().ifPresent(result -> {
                try {
                    addCustomer(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception ex) { }
    }

    private void addCustomer(Customer customer) throws IOException
    {
        if(customer != null) {
            dataHandler.addCustomer(customer);
        }
    }

    @FXML
    protected void onRemoveCustomerButtonClick() throws IOException{

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Confirmation");
        dialog.setContentText("Are you sure you want to remove ?");

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.get() == ButtonType.OK) {
            Customer customer = customerTableView.getSelectionModel().getSelectedItem();
            if(customer != null) {
                this.dataHandler.deleteCustomer(customer);
            }
        }
    }

    @FXML
    private void initialize() {
        TableColumn<Customer, Integer> column1 = new TableColumn<>("Customer ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("Id"));
        customerTableView.getColumns().add(column1);

        TableColumn<Customer, String> column2 = new TableColumn<>("Part Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerTableView.getColumns().add(column2);

        TableColumn<Customer, Integer> column3 = new TableColumn<>("Address");
        column3.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerTableView.getColumns().add(column3);

        TableColumn<Customer, Double> column4 = new TableColumn<>("Card Detail");
        column4.setCellValueFactory(new PropertyValueFactory<>("cardDetail"));
        customerTableView.getColumns().add(column4);

        customerTableView.setItems(this.dataHandler.getAllCustomers());

        removeButton.setVisible(this.dataHandler.getAllowedToBeRemoved());
    }

}
