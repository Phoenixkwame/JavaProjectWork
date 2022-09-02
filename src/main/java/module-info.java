module network2c.hostelallocationsystemfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens network2c.hostelallocationsystemfinal to javafx.fxml;
    exports network2c.hostelallocationsystemfinal;
}