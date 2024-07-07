module com.example.temalab3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.logging.log4j;
    requires java.sql;

    opens com.example.temalab3 to javafx.fxml;
    exports com.example.temalab3;

    opens com.example.temalab3.controller to javafx.fxml;
    exports com.example.temalab3.controller;

    opens com.example.temalab3.domain to javafx.fxml;
    exports com.example.temalab3.domain;
}