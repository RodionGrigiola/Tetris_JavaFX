package app.tetris;

import Pieces.TPiece;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GuiController {

    private static final int PIECE_SIZE = 20;

    @FXML
    private GridPane gamePanel;

    @FXML
    private GridPane piecePanel;

    public void initGameView(int[][] boardMatrix, int[][] pieceData) {
        for(int i = 2; i < boardMatrix.length; i++) {
            for(int j = 0; j <  boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(PIECE_SIZE, PIECE_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                gamePanel.add(rectangle, j, i - 2);
            }
        }

        for(int i = 0; i < pieceData.length; i++) {
            for(int j = 0; j < pieceData[i].length; j++) {
                Rectangle rectangle = new Rectangle(PIECE_SIZE, PIECE_SIZE);
                rectangle.setFill(getFillColor(pieceData[i][j]));
                piecePanel.add(rectangle, j, i);
            }
        }
    }

    public Color getFillColor(int i) {
        return switch (i) {
            case 0 -> Color.TRANSPARENT;
            case 1 -> Color.AQUA;
            case 2 -> Color.BLUEVIOLET;
            case 3 -> Color.BURLYWOOD;
            case 4 -> Color.CORAL;
            case 5 -> Color.CADETBLUE;
            case 6 -> Color.DEEPPINK;
            case 7 -> Color.ORCHID;
            default -> Color.WHITE;
        };
    }
}