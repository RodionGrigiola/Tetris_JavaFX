package app.tetris;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GuiController {

    private static final int PIECE_SIZE = 20;

    @FXML
    private GridPane gamePane1;

    public void initGameView() {
        for(int i = 2; i < 25; i++) {
            for(int j = 0; j < 10; j++) {
                Rectangle rectangle = new Rectangle(PIECE_SIZE, PIECE_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                gamePane1.add(rectangle, j, i - 2);
            }
        }
    }
}