module com.example.vetclinic {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.vetclinic to javafx.fxml;
    exports com.example.vetclinic;
    exports com.example.vetclinic.core.models;
    opens com.example.vetclinic.core.models to javafx.fxml;
    exports com.example.vetclinic.presentation;
    opens com.example.vetclinic.presentation to javafx.fxml;
    exports com.example.vetclinic.db;
    opens com.example.vetclinic.db to javafx.fxml;
    exports com.example.vetclinic.config;
    opens com.example.vetclinic.config to javafx.fxml;
}