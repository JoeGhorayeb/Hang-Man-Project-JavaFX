module com.example.projectoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;

    opens com.example.projectoop to javafx.graphics;
}