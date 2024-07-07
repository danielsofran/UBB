module app.template {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens app.template to javafx.fxml;
    exports app.template;

    opens app.template.models to javafx.fxml;
    exports app.template.models;

    exports app.template.controller;
    opens app.template.controller to javafx.fxml;

    exports app.template.service;
    opens app.template.service to javafx.fxml;
}