package com.shopping.controller;

import com.shopping.data.Customer;
import com.shopping.data.DataHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddCustomerDialogController extends Dialog<Customer> {

    public static Customer customer = null;

    @FXML
    private TextField name, ID, address, card;

    public static ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);

    private boolean nameFilled = false, addressFilled = false, cardFilled = false;

    /**
     * Constructor
     *
     * @param  dataHandler Object
     */
    public AddCustomerDialogController(DataHandler dataHandler) throws IOException {

        this.customer = new Customer();

        setTitle("Add Product");

        getDialogPane().getButtonTypes().setAll(saveButtonType, ButtonType.CANCEL);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-customer-view.fxml"));
        loader.setController(this);

        getDialogPane().setContent(loader.load());

        try {
            setResultConverter(dialogButton ->
                    (nameFilled && addressFilled && cardFilled) ? this.customer : null );
        } catch (Exception ex) {
            setResultConverter(dialogButton -> null);
        }

        ID.setEditable(false);
        ID.setText("Auto Gen- Disabled");
        this.customer.setId(0);

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            this.customer.setName(newValue.toString());

            if(newValue.length() > 0)
                nameFilled = true;
            else
                nameFilled = false;
        });

        address.textProperty().addListener((observable, oldValue, newValue) -> {
            this.customer.setAddress(newValue.toString());

            if(newValue.length() > 0)
                addressFilled = true;
            else
                addressFilled = false;
        });

        card.textProperty().addListener((observable, oldValue, newValue) -> {
            this.customer.setCardDetail(newValue.toString());

            if(newValue.length() > 0)
                cardFilled = true;
            else
                cardFilled = false;
        });


    }
}
