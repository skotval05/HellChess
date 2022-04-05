module com.example.thehellchess {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.github.jingerjesus.thehellchess.control;
    opens com.github.jingerjesus.thehellchess.control to javafx.fxml;
}