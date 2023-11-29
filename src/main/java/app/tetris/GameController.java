package app.tetris;

public class GameController {

    private final GuiController viewController;

    public GameController(GuiController controller) {
        this.viewController = controller;
        this.viewController.initGameView();
    }
}
