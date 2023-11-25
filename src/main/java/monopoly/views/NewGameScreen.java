package monopoly.views;

import monopoly.Game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 *
 * @author ramez
 */
public class NewGameScreen {
    
    private Label nOP;
    
    private ObservableList<String> NoOfPlayers;
    
    private ComboBox combobox;
    private HBox nOPBox;
    private GridPane newGameGrid;
    
    private Scene scene;
    
    private ImageView logo = new ImageView(new Image("file:..\\assets\\imgs\\logo.png"));
    
    public NewGameScreen() {
        initialize();
    }
    
    public void initialize() {
       //newGame screen
            
            // num of Players label
        nOP=new Label("Enter No of Players: ");
        nOP.setStyle( "-fx-text-fill: #ffffff");
        nOP.setScaleX(1.3);
            //
        
           //Enter button
        Game.go[0].setStyle("-fx-background-color: transparent;-fx-cursor: hand; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
        Game.go[0].setTranslateY(-15);

        //
            
            //Combo Box aka drop down menu
        NoOfPlayers= FXCollections.observableArrayList("2","3","4","5","6","7","8");
        combobox=new ComboBox(NoOfPlayers);
            //
            
            //Hbox
        nOPBox= new HBox(20);
        nOPBox.getChildren().addAll(nOP,combobox,Game.go[0]);
          //
        
            //grid itself
        newGameGrid=new GridPane();
        
        newGameGrid.add(Game.back[1], 0, 0);
        Game.back[1].setTranslateY(15);
        Game.back[1].setTranslateX(10);
        newGameGrid.add(nOPBox, 0, 0);
        nOPBox.setTranslateX(350);
        nOPBox.setTranslateY(250);
         newGameGrid.setStyle("-fx-background-color: #002230;");
        newGameGrid.add(logo, 0, 0);
        logo.setTranslateX(350);
        logo.setTranslateY(50);
            //
        
        
        scene = new Scene(newGameGrid, Game.screenWidth, Game.screenHeight);
        // end of new Game Screen
        
        Game.go[0].setOnAction((ActionEvent event) -> {
            
            if (combobox.getValue() == null) return;
            
            int num = Integer.parseInt((String)combobox.getValue());
            
            Game.enterDataScreen = new EnteringPlayerData(num);
            Game.primaryStage.setScene(Game.enterDataScreen.getScene());
            
        });
        
        
   }
   
   public Scene getScene() {
       return scene;
   }
}
