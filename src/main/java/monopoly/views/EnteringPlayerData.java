package monopoly.views;

import monopoly.Queue;
import monopoly.models.Game;
import monopoly.models.Player;

import monopoly.controllers.PlayerController;

import java.util.ArrayList;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import monopoly.controllers.GameController;

/**
 *
 * @author ramez
 */
public class EnteringPlayerData {

    private Label[] Names;
    private TextField[] TNames;
    private HBox[] HNames;
    private VBox vNames;
    private HBox h;
    private final ImageView logo = new ImageView(new Image("file:Images\\logo.png"));

    private Scene scene;

    public ArrayList<Integer> playerList;
    private int playerNum;

    private Button goButton, backButton;

    GameController myGameController;

    public EnteringPlayerData(GameController Game, GameView GameView, int playerNum) {
        myGameController = Game;
        myGameController.startNewGame();
        // Game.reloadPlayerData();
        this.playerNum = playerNum;

        // Entering player data screen

        Names = new Label[playerNum];
        TNames = new TextField[playerNum];
        HNames = new HBox[playerNum];

        vNames = new VBox(10);

        vNames.setAlignment(Pos.CENTER);
        vNames.getChildren().add(logo);
        logo.setTranslateX(0);
        logo.setTranslateY(-100);

        

        for (int i = 0; i < playerNum; i++) {

            Names[i] = new Label("Player " + (i + 1) + " : ");

            Names[i].setStyle("-fx-text-fill: #ffffff");

            TNames[i] = new TextField();

            HNames[i] = new HBox(10);

            HNames[i].getChildren().addAll(Names[i], TNames[i]);

            vNames.getChildren().add(HNames[i]);
        }

        goButton = new Button("Go");

        goButton.setStyle(
                "-fx-background-color: transparent;-fx-cursor: hand; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
        goButton.setAlignment(Pos.BOTTOM_RIGHT);
        vNames.getChildren().add(goButton);

        backButton = new Button("", new ImageView("file:assets\\imgs\\buttons\\back.png"));
        backButton.setTranslateX(-200);
        backButton.setTranslateY(60);

        backButton.setTranslateX(-540);
        backButton.setTranslateY(-230);

        h = new HBox();
        h.setAlignment(Pos.CENTER);

        h.getChildren().addAll(vNames, backButton);

        h.setStyle("-fx-background-color: #002230;");
        scene = new Scene(h, GameView.getWidth(), GameView.getHeight());

        // end of screen

        goButton.setOnAction((ActionEvent event) -> {

            String[] arr = new String[playerNum];
            Alert alert = new Alert((Alert.AlertType.ERROR));

            for (int i = 0; i < playerNum; i++) {
                if (TNames[i].getText().length() == 0) {
                    alert.setContentText("Please enter all " + playerNum + " names");
                    alert.show();
                    return;
                } else {
                    arr[i] = TNames[i].getText().toLowerCase();
                }
            }

            for (int i = 0; i < PlayScreen.playersCount; i++) {
                for (int j = 0; j < PlayScreen.playersCount; j++) {
                    if (i != j) {
                        if (arr[j].equals(arr[i])) {
                            // duplicate name
                            alert.setContentText("Duplicate name! Please change the name");
                            alert.show();
                            return;
                        }
                    }
                }
            }

            for (int i = 0; i < PlayScreen.playersCount; i++) {

                String name = TNames[i].getText().toLowerCase();

                myGameController.addPlayer(name);
            }

            myGameController.changeScreen("PlayScreen");
        });
    }

    public Scene getScene() {
        return scene;
    }
}
