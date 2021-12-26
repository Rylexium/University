module app {
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;
    requires java.base;
    requires javafx.fxml;

    opens com.example to javafx.fxml;

    exports com.example;
}