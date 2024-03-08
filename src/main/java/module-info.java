module manager.managerproduct {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;

    opens Model to javafx.base;
    opens View to javafx.fxml;
    opens Controller;
    exports View;
    exports Controller;
}