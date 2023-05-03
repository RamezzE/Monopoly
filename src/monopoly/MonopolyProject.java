/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package monopoly;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author ramez
 */
public class MonopolyProject extends Application {
    
    private Game myGame;
    
    @Override
    public void start(Stage primaryStage) {
        myGame = new Game();
        myGame.startProgram();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
