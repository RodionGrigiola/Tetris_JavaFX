module app.tetris {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.tetris to javafx.fxml;
    exports app.tetris;
}