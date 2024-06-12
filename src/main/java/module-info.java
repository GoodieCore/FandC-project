module org.example.fandcproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires java.desktop;
    requires poi.ooxml;
    requires poi;
    requires javafx.swing;
    requires xmlbeans;


    opens org.example.fandcproject to javafx.fxml;
    exports org.example.fandcproject;
}