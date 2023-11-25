package monopoly;

import monopoly.controllers.GameController;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author ramez
 */
public class MonopolyProject extends Application {
    
    private GameController myGameController;
    
    @Override
    public void start(Stage primaryStage) {
        myGameController = new GameController();
        myGameController.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
