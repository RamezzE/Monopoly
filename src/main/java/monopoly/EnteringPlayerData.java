/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monopoly;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static monopoly.PlayScreen.playerQueue;
import static monopoly.PlayScreen.playersCount;

/**
 *
 * @author ramez
 */
public class EnteringPlayerData {
    
    private Label[] Names;
    private TextField []TNames;
    private HBox [] HNames;
    private VBox vNames;
    private HBox h;
    private final ImageView logo = new ImageView(new Image("file:Images\\logo.png"));
    
    private Scene scene;
    
    public static ArrayList<Integer> playerList;
    
    public EnteringPlayerData(int playerNum) {
        Game.reloadPlayerData();
        Game.playScreen.playersCount = playerNum;
        initialize();
        
    }
    
    public void initialize() {
        // Entering player data screen
        
        Names = new Label[Game.playScreen.playersCount];
        TNames = new TextField[Game.playScreen.playersCount];
        HNames = new HBox[Game.playScreen.playersCount];
        
        vNames = new VBox(10);
        
        vNames.setAlignment(Pos.CENTER);
        vNames.getChildren().add(logo);
        logo.setTranslateX(0);
        logo.setTranslateY(-100);
        
        
        for(int i=0;i<Game.playScreen.playersCount;i++) {
            
                Names[i] = new Label("Player "+(i+1)+" : ");
                
                Names[i].setStyle( "-fx-text-fill: #ffffff");
                
                TNames[i] = new TextField();
                
                HNames[i] = new HBox(10);
                
                HNames[i].getChildren().addAll(Names[i],TNames[i]);
                
                vNames.getChildren().add(HNames[i]);
        }
        
        Game.go[1].setStyle("-fx-background-color: transparent;-fx-cursor: hand; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
        
        Game.go[1].setAlignment(Pos.BOTTOM_RIGHT);
        
        vNames.getChildren().add(Game.go[1]);
        
        
        h = new HBox();
        h.setAlignment(Pos.CENTER);

        h.getChildren().addAll(vNames,Game.back[2]);
        Game.back[2].setTranslateX(-200);
        Game.back[2].setTranslateY(60);

        Game.back[2].setTranslateX(-540);
        Game.back[2].setTranslateY(-230);

        h.setStyle("-fx-background-color: #002230;");
        scene = new Scene(h,Game.screenWidth,Game.screenHeight);  
        
        //end of screen
        
        
        Game.go[1].setOnAction((ActionEvent event) -> {
            
            
            String [] arr = new String[Game.playScreen.playersCount];
            Alert alert = new Alert((Alert.AlertType.ERROR));
            
            
            for (int i = 0;i<Game.playScreen.playersCount;i++)  {
                if (TNames[i].getText().length() == 0) {
                    alert.setContentText("Please enter all " + Game.playScreen.playersCount + " names");
                    alert.show();
                    return;
                }
                else {
                    arr[i] = TNames[i].getText().toLowerCase(); 
                }
            }
            
            
            for (int i = 0;i<Game.playScreen.playersCount;i++) {
                for (int j = 0;j<Game.playScreen.playersCount;j++) {
                    if (i!=j) {
                        if (arr[j].equals(arr[i])) {
                            //duplicate name
                            alert.setContentText("Duplicate name! Please change the name");
                            alert.show();
                            return;
                        }
                    }
                }
            }
            
            playerList = new ArrayList<>();
            
            for (int i = 0;i<Game.playScreen.playersCount;i++) {
                
                String name = TNames[i].getText().toLowerCase();
                
                int index = Player.isNewPlayer(Game.player, name); //returns Player's index or -1 if new Player
                
                if (index < 0) {
                    
                    System.out.println("NEW PLAYER");
                    
                    Game.player.add(Game.player.size(),new Player(name,Game.player.size()));
                      
                    playerList.add(Game.player.size()-1);
                    
                }
                else {
                    
                    // player already exists so adds index to queue   
                    System.out.println("Welcome back");
                    Game.player.get(index).startNewGame();
                    playerList.add(index);
                }
                
                
                
            }   
            
            
            Game.primaryStage.setScene(Game.playScreen.getScene());
            Game.primaryStage.setFullScreen(true);

            
            playerQueue = new Queue(playersCount);
            Game.playScreen.rollSum = new int[playersCount][2]; 
            
            Game.playScreen.figurePlayerOrder();
           
            
        });
        
    }
    
    
    
    public Scene getScene() {
        return scene;
    }
}
