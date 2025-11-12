package com.shopping.controller;

import com.shopping.data.DataHandler;
import com.shopping.data.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

public class AddProductDialogController extends Dialog<Product> {

    public static Product product = null;

    @FXML
    private TextField name, inventory, price, ID;

    private boolean nameFilled = false, inventoryFilled = false, priceFilled = false;

    /**
     * Constructor
     */
    public AddProductDialogController() throws IOException {

        this.product = new Product();

        setTitle("Add Product");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-product-view.fxml"));
        loader.setController(this);

        getDialogPane().setContent(loader.load());

        ID.setEditable(false);
        ID.setText("Auto Gen- Disabled");
        this.product.setId(0);

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            this.product.setName(newValue.toString());

            if(newValue.length() > 0)
                nameFilled = true;
            else
                nameFilled = false;
        });

        inventory.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                this.product.setStock(Integer.parseInt(newValue.toString()));
                inventoryFilled = true;
            } catch (Exception ex) {
                inventoryFilled = false;
            }
        });

        price.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                this.product.setPrice(Double.parseDouble(newValue.toString()));
                priceFilled = true;
            } catch (Exception ex) {
                priceFilled = false;
            }

        });

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().setAll(saveButtonType, ButtonType.CANCEL);

        try {
            setResultConverter(dialogButton ->
                    (nameFilled && inventoryFilled && priceFilled) ? this.product : null );
        } catch (Exception ex) {




            setResultConverter(dialogButton -> null);
        }

    }
}
