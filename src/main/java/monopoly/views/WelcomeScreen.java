package monopoly.views;

import monopoly.controllers.GameController;

import java.util.Map;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author ramez
 */

public class WelcomeScreen {
    
    private Button startButton;
    private StackPane pane;
    private ImageView background;

    private GameController myGameController;
    
    Scene scene;
    
    public WelcomeScreen(GameController Game, GameView GameView) {
        myGameController = Game;
        initialize();
    }
    
    public void initialize() {
       // screen 1
        
        //startButton
        startButton = new Button("Play");
        
        //startButton.setStyle("-fx-background-color: transparent;-fx-cursor: hand; -fx-border-thickness:3px;-fx-border-color:black;-fx-text-fill: #ffffff");
        startButton.setOpacity(0);
        
        int width = 300, height = 60;
        startButton.setMinSize(width,height);
        startButton.setMaxSize(width, height);
        startButton.setTranslateY(-176);
        
        startButton.setOnAction((ActionEvent event) -> {
            myGameController.changeScreen("MenuScreen");
        });
        
        // end of startButton
            
        background = new ImageView(new Image("file:assets\\imgs\\monopoly.jpg"));
        
        pane = new StackPane();
        pane.getChildren().addAll(background, startButton); 

        scene = new Scene(pane, 1280, 720);
    }
    
    public Scene getScene() {
        return scene;
    }
    
}
