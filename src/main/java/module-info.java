module com.example.vetclinic {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.vetclinic to javafx.fxml;
    exports com.example.vetclinic;
}