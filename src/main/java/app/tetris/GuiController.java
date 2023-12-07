package app.tetris;

import app.tetris.Components.DownData;
import app.tetris.Components.ViewData;
import app.tetris.Events.EventSource;
import app.tetris.Events.EventType;
import app.tetris.Events.InputEventListener;
import app.tetris.Events.MoveEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GuiController implements Initializable {

    private static final int PIECE_SIZE = 20;
    Timeline timeline;
    private InputEventListener eventListener;
    private Rectangle[][] displayMatrix;
    private Rectangle[][] rectangles;
    private final BooleanProperty paused = new SimpleBooleanProperty();
    private final BooleanProperty isGameOver = new SimpleBooleanProperty();


    @FXML
    private ToggleButton pauseButton;

    @FXML
    private GridPane gamePanel;

    @FXML
    private GridPane nextPiecePane;

    @FXML
    private GridPane piecePanel;

    @FXML
    private Text scoreValue;

    @FXML
    private Group groupNotification;

    @FXML
    private GameOverPanel gameOverPanel;

    public void initGameView(int[][] boardMatrix, ViewData viewData) {
        displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for(int i = 2; i < boardMatrix.length; i++) {
            for(int j = 0; j <  boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(PIECE_SIZE, PIECE_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - 2 );
            }
        }

        rectangles = new Rectangle[viewData.getPieceData().length][viewData.getPieceData()[0].length];

        for(int i = 0; i < viewData.getPieceData().length; i++) {
            for(int j = 0; j < viewData.getPieceData()[i].length; j++) {
                Rectangle rectangle = new Rectangle(PIECE_SIZE, PIECE_SIZE);
                rectangle.setFill(getFillColor(viewData.getPieceData()[i][j]));
                rectangles[i][j] = rectangle;
                piecePanel.add(rectangle, j, i);
            }
        }

        piecePanel.setLayoutX(gamePanel.getLayoutX() + viewData.getXPosition() * PIECE_SIZE);
        piecePanel.setLayoutY(-42 + gamePanel.getLayoutY() + viewData.getYPosition() * PIECE_SIZE);

        generatePreviewPanel(viewData.getNextPieceData());

        timeline = new Timeline(new KeyFrame(Duration.millis(400),
                actionEvent -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void generatePreviewPanel(int[][] nextPieceData) {
        nextPiecePane.getChildren().clear();
        for(int i = 0; i < nextPieceData.length; i++) {
            for(int j = 0; j < nextPieceData[i].length; j++) {
                Rectangle rectangle = new Rectangle(PIECE_SIZE, PIECE_SIZE);
                setRectangleData(nextPieceData[i][j], rectangle);
                if(nextPieceData[i][j] != 0) {
                    nextPiecePane.add(rectangle, j, i);
                }
            }
        }
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(getFillColor(color));
        rectangle.setArcHeight(1);
        rectangle.setArcWidth(1);
    }

    public void refreshGameBackground(int[][] board) {
        for(int i = 2; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                setRectangleData(board[i][j], displayMatrix[i][j]);
            }
        }
    }

    private void moveDown(MoveEvent event) {
        DownData downData = eventListener.onDownEvent(event);
        if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
            NotificationPanel notificationPanel = new NotificationPanel(" + " + downData.getClearRow().getScoreBonus());
            groupNotification.getChildren().add(notificationPanel);
            notificationPanel.showScore(groupNotification.getChildren());
        }
        refresh(downData.getViewData());
    }

    private void refresh(ViewData viewData) {
        if(viewData == null) return;
        piecePanel.setLayoutX(gamePanel.getLayoutX() + viewData.getXPosition() * PIECE_SIZE);
        piecePanel.setLayoutY(-42 + gamePanel.getLayoutY() + viewData.getYPosition() * PIECE_SIZE);

        for (int i = 0; i < viewData.getPieceData().length; i++) {
            for (int j = 0; j < viewData.getPieceData()[i].length; j++) {
                setRectangleData(viewData.getPieceData()[i][j], rectangles[i][j]);
            }
        }

        generatePreviewPanel(viewData.getNextPieceData());
    }

    public void setEventListener(InputEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void bindScore(IntegerProperty integerProperty) {
        scoreValue.textProperty().bind(integerProperty.asString());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();
        gamePanel.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (paused.getValue() == Boolean.FALSE && isGameOver.getValue() == Boolean.FALSE) {
                    if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                        refresh(eventListener.onRotateEvent());
                        event.consume();
                    }
                    if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                        moveDown(new MoveEvent(EventType.DOWN, EventSource.USER));
                        event.consume();
                    }
                    if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                        refresh(eventListener.onLeftEvent());
                        event.consume();
                    }
                    if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                        refresh(eventListener.onRightEvent());
                        event.consume();
                    }
                }

                if (event.getCode() == KeyCode.P) {
                    pauseButton.selectedProperty().setValue(!pauseButton.selectedProperty().getValue());
                }
            }
        });

        gameOverPanel.setVisible(false);

        pauseButton.selectedProperty().bindBidirectional(paused);
        pauseButton.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    timeline.pause();
                    pauseButton.setText("Resume");
                } else {
                    timeline.play();
                    pauseButton.setText("Pause");
                }
                gamePanel.setFocusTraversable(true);
                gamePanel.requestFocus();
            }
        });

        Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOpacity(0.9);
        reflection.setTopOffset(-12);
        scoreValue.setEffect(reflection);
    }

    public void gameOver() {
        timeline.stop();
        gameOverPanel.setVisible(true);
        isGameOver.setValue(Boolean.TRUE);
        System.out.println("Game Over!");
    }
}