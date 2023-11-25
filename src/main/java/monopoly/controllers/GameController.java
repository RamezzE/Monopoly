package monopoly.controllers;

import monopoly.models.Game;
import monopoly.views.GameView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class GameController {

    private Game myGame;
    private GameView myGameView;


    public GameController() {
        
    }

    public void start() {
        myGame = new Game();
        myGameView = new GameView(myGame, this);
    }

    public void setPlayersCount(int playersCount) {
        myGame.setPlayersNum(playersCount);
    }

    public void saveGame() {
        myGame.saveGame();
    }

    public Boolean loadGame() {
        // if (myGame.resumeGame()) {
        //     myGameView.changeScreen("PlayScreen");
        //     return true;
        // }

        return false;
    }

    public void startNewGame() {
        myGame.loadBoardData();
    }

    public void changeScreen(String screenName) {
        System.out.println("Changing screen to " + screenName);
        myGameView.changeScreen(screenName);
    }

    public void addPlayer(String playerName) {
        PlayerController.addPlayer(myGame.getPlayers(), playerName);
    }

    
}
