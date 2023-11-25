package monopoly.views;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import monopoly.models.Game;
import monopoly.controllers.GameController;

public class GameView {

    private Scene scene;
    private Stage primaryStage;
    private int width, height;

    private Map<String, Scene> myScreens;

    private Game myGame;
    private GameController myGameController;

    public GameView(Game Game, GameController GameController) {
        myGame = Game;
        myGameController = GameController;
        
        primaryStage = new Stage();
        myScreens = new HashMap<>();

        changeScreen("WelcomeScreen");
        primaryStage.setTitle("Monopoly");
        primaryStage.show();
    }

    public void changeScreen(String screenName) {
        scene = myScreens.get(screenName);

        if (scene == null)  {
            switch (screenName) {
                case "WelcomeScreen":
                    scene = new WelcomeScreen(myGameController, this).getScene();
                    break;
                case "MenuScreen":
                    scene = new MenuScreen(myGameController, this).getScene();
                    break;
                case "NewGameScreen":
                    scene = new NewGameScreen(myGameController, this).getScene();
                    break;
                case "EnteringPlayerData":
                    scene = new EnteringPlayerData(myGameController, this, myGame.getPlayersNum()).getScene();
                    break;
                case "PlayScreen":
                    scene = new PlayScreen(myGameController, this, myGame.getPlayers(), myGame.getPlayersQueue(), myGame.getBoard()).getScene();
                    break;
            }
        }

        myScreens.put(screenName, scene);
        primaryStage.setScene(scene);
        update();
    }

    private void update() {
        primaryStage.setScene(scene);
        // primaryStage.show();
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
