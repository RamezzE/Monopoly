package monopoly.views;

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

import javafx.scene.control.Button;

import monopoly.controllers.GameController;

/**
 *
 * @author ramez
 */
public class NewGameScreen {
    private Label nOP;
    private ObservableList<String> NoOfPlayers;
    private ComboBox<String> combobox; // Fix: Parameterize ComboBox with String
    private HBox nOPBox;
    private GridPane newGameGrid;
    private Button goButton, backButton;
    private Scene scene;
    private ImageView logo = new ImageView(new Image("file:..\\assets\\imgs\\logo.png"));

    public NewGameScreen(GameController Game, GameView GameView) {
   
        // newGame screen

        // num of Players label
        nOP = new Label("Enter No of Players: ");
        nOP.setStyle("-fx-text-fill: #ffffff");
        nOP.setScaleX(1.3);
        //

        // Enter button
        goButton = new Button("Go");
        goButton.setStyle(
                "-fx-background-color: transparent;-fx-cursor: hand; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
        goButton.setTranslateY(-15);

        //

        // Combo Box aka drop down menu
        NoOfPlayers = FXCollections.observableArrayList("2", "3", "4", "5", "6", "7", "8");
        combobox = new ComboBox <String>(NoOfPlayers);
        //

        // Hbox
        nOPBox = new HBox(20);
        nOPBox.getChildren().addAll(nOP, combobox, goButton);
        //

        // back button
        backButton = new Button("Back");
        backButton.setTranslateY(15);
        backButton.setTranslateX(10);

        // grid itself
        newGameGrid = new GridPane();
        newGameGrid.add(backButton, 0, 0);
        newGameGrid.add(nOPBox, 0, 0);
        nOPBox.setTranslateX(350);
        nOPBox.setTranslateY(250);
        newGameGrid.setStyle("-fx-background-color: #002230;");
        newGameGrid.add(logo, 0, 0);
        logo.setTranslateX(350);
        logo.setTranslateY(50);
        //

        scene = new Scene(newGameGrid, GameView.getWidth(), GameView.getHeight());
        // end of new Game Screen

        goButton.setOnAction((ActionEvent event) -> {

            if (combobox.getValue() == null)
                return;

            Game.setPlayersCount(Integer.parseInt((String) combobox.getValue()));
            
            Game.changeScreen("EnteringPlayerData");
        });

    }

    public Scene getScene() {
        return scene;
    }
}
