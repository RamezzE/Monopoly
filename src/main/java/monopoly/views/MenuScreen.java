package monopoly.views;

import monopoly.Game;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author ramez
 */
public class MenuScreen {
    
    private Button newGameB;
    private Button contGameB;
    
    private VBox menuBox;
    private GridPane menu;
    private Scene scene;   
    
    public Alert alert;
    
    public MenuScreen() {
        initialize();
    }
    
    public void initialize() {
        //Menu aka screen2
        
            //new Game button
        newGameB = new Button("New Game");
        newGameB.setMinSize(250, 90);
        newGameB.setStyle("-fx-background-color: transparent;-fx-cursor: hand;-fx-border-color:white;-fx-border-width: 1.5; -fx-text-fill: #ffffff");
            //
          
            //continue Game button
        contGameB = new Button("Continue Game");
        contGameB.setMinSize(150, 70);
        contGameB.setStyle("-fx-background-color: transparent;-fx-cursor: hand;-fx-border-color:white; -fx-border-width: 1.5; -fx-text-fill: #ffffff");
            //
        
            //back button
        menu =new GridPane();
        
        menu.add(Game.back[0],0,0);
        Game.back[0].setTranslateX(-330);
        Game.back[0].setTranslateY(-240);
            
            
            //vBox
        menuBox =new VBox (40);
        menuBox.getChildren().addAll(newGameB,contGameB);
        menuBox.setAlignment(Pos.CENTER);
            //
            
            //menu grid itself
        menu.setVgap(10);
        menu.setAlignment(Pos.CENTER);
        menu.add(menuBox,0,0);
        
        menu.setStyle("-fx-background-color: #002230;"); 
        
        scene = new Scene(menu,Game.screenWidth,Game.screenHeight);
            
        //end of menu  
        
        //setting up alert
        
        //dialog to show that there is no older game to continue and changes scene to newGameScreen
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        
        newGameB.setOnAction((ActionEvent event) -> {
            Game.primaryStage.setOnCloseRequest( e -> {
                Game.saveGame();
                Game.primaryStage.close();
            });
            Game.primaryStage.setScene(Game.newGameScreen.getScene());
        });
        
        contGameB.setOnAction((ActionEvent event) -> {
            Game.playScreen.loadGame();            
        });

    }
    
    public Scene getScene() {
        return scene;
    }
}
