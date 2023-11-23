/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monopoly;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        
    
    Scene scene;
    
    public WelcomeScreen() {
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
            Game.primaryStage.setScene(Game.menuScreen.getScene());
        });
        
        // end of startButton
            
        background = new ImageView(Game.boardImage);
        
        pane = new StackPane();
        pane.getChildren().addAll(background, startButton);
        
        scene = new Scene(pane,Game.screenWidth,Game.screenHeight);
        // end of screen1 
    }
    
    public Scene getScene() {
        return scene;
    }
    
}
