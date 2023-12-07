package app.tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gameLayout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 390, 500);
        GuiController controller = fxmlLoader.getController();

        stage.setTitle("Tetris!");
        stage.setScene(scene);
        stage.show();

        new GameController(controller);
    }

    public static void main(String[] args) {
        launch();
    }
}