module com.shopping.shoppingcenter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.shopping.shoppingcenter to javafx.fxml;
    exports com.shopping.shoppingcenter;
    exports com.shopping.data;
    opens com.shopping.data to javafx.fxml;
    exports com.shopping.controller;
    opens com.shopping.controller to javafx.fxml;
}