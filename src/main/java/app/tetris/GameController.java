package app.tetris;

import Pieces.TPiece;

public class GameController {

    private SimpleBoard board = new SimpleBoard(25, 10);
    private final GuiController viewController;

    public GameController(GuiController controller) {
        this.viewController = controller;
        board.createNewPiece();
        this.viewController.initGameView(board.getBoardMatrix(), board.getCurrentShape());
    }
}
