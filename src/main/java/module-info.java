module app.tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens app.tetris to javafx.fxml;
    exports app.tetris;
    exports app.tetris.Pieces;
    opens app.tetris.Pieces to javafx.fxml;
    exports app.tetris.Events;
    opens app.tetris.Events to javafx.fxml;
    exports app.tetris.Components;
    opens app.tetris.Components to javafx.fxml;
}