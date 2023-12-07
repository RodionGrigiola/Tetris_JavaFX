package app.tetris;

import app.tetris.Components.ClearRow;
import app.tetris.Components.DownData;
import app.tetris.Components.SimpleBoard;
import app.tetris.Components.ViewData;
import app.tetris.Events.EventSource;
import app.tetris.Events.InputEventListener;
import app.tetris.Events.MoveEvent;

public class GameController implements InputEventListener {

    private final SimpleBoard board = new SimpleBoard(10, 25);
    private final GuiController viewController;

    public GameController(GuiController controller) {
        this.viewController = controller;
        this.viewController.setEventListener(this);
        board.createNewPiece();
        this.viewController.initGameView(board.getBoardMatrix(), board.getViewData());
        this.viewController.bindScore(board.getScore().scoreProperty());
    }

    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.movePieceDown();
        ClearRow clearRow = null;
        if(!canMove) {
            board.mergePieceToBackground(); // добавляем фигурку к бэкграунду
            clearRow = board.clearRows(); // удаляем заполненные ряды
            if(clearRow.getLinesRemoved() > 0) {
                board.getScore().add(clearRow.getScoreBonus()); // добавляем бонус к скору (50очков * кол. рядов)
            }
            if(board.createNewPiece()) {
                viewController.gameOver();
            }
        }
        else {
            if(event.getEventSource() == EventSource.USER) {
                // Добавляем 1 к скору если юзер ускоряет фигуру
                board.getScore().add(1);
            }
        }
        viewController.refreshGameBackground(board.getBoardMatrix()); // обновляем gui уже с новой матрицей
        return new DownData(clearRow, board.getViewData());
    }

    @Override
    public ViewData onLeftEvent() {
        board.movePieceLeft();
        return board.getViewData();
    }

    @Override
    public ViewData onRightEvent() {
        board.movePieceRight();
        return board.getViewData();
    }

    @Override
    public ViewData onRotateEvent() {
        board.rotatePiece();
        return null;
    }


}
