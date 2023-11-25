package monopoly.views;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import monopoly.controllers.GameController;

/**
 *
 * @author ramez
 */
public class MenuScreen {

    private Button newGameB, contGameB, backButton;

    private VBox menuBox;
    private GridPane menu;
    private Scene scene;

    public Alert alert;

    public MenuScreen(GameController Game, GameView GameView) {    
        // Menu aka screen2

        // new Game button
        newGameB = new Button("New Game");
        newGameB.setMinSize(250, 90);
        newGameB.setStyle(
                "-fx-background-color: transparent;-fx-cursor: hand;-fx-border-color:white;-fx-border-width: 1.5; -fx-text-fill: #ffffff");
        //

        // continue Game button
        contGameB = new Button("Continue Game");
        contGameB.setMinSize(150, 70);
        contGameB.setStyle(
                "-fx-background-color: transparent;-fx-cursor: hand;-fx-border-color:white; -fx-border-width: 1.5; -fx-text-fill: #ffffff");
        //

        // back button
        backButton = new Button("", new ImageView("file:assets\\imgs\\buttons\\back.png"));
        menu = new GridPane();

        menu.add(backButton, 0, 0);
        backButton.setTranslateX(-330);
        backButton.setTranslateY(-240);

        // vBox
        menuBox = new VBox(40);
        menuBox.getChildren().addAll(newGameB, contGameB);
        menuBox.setAlignment(Pos.CENTER);
        //

        // menu grid itself
        menu.setVgap(10);
        menu.setAlignment(Pos.CENTER);
        menu.add(menuBox, 0, 0);

        menu.setStyle("-fx-background-color: #002230;");

        scene = new Scene(menu, GameView.getWidth(), GameView.getHeight());

        // end of menu

        // setting up alert

        // dialog to show that there is no older game to continue and changes scene to
        // newGameScreen
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        newGameB.setOnAction((ActionEvent event) -> {
            // myGameView.primaryStage.setOnCloseRequest(e -> {
            //     myGameController.saveGame();
            //     Game.primaryStage.close();
            // });
            Game.changeScreen("NewGameScreen");
        });

        contGameB.setOnAction((ActionEvent event) -> {

            if (!Game.loadGame()) {
                alert.setContentText("No saved game found");
                alert.show();
                return;
            }
            
            Game.changeScreen("PlayScreen");
        });

    }

    public Scene getScene() {
        return scene;
    }
}
