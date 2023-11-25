package monopoly.controllers;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import monopoly.models.Player;

public class PlayerController {

    public static int roll() {
        // dice roll
        return (int) (Math.random() * 6 + 1);
    }

    public static void addPlayer(ArrayList<Player> players, String playerName) {
        
        int playerID = isNewPlayer(players, playerName);
        if (playerID == -1) {
            players.add(new Player(playerName, players.size()));
            playerID = players.size() - 1;
        }

        players.get(playerID).startNewGame();
    }

    private static int isNewPlayer(ArrayList<Player> players, String name) {
        // check if this player is a new player or not

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public static void goToJail(Player player, ImageView token) {
        player.inJail = true;
        player.roundsInJail = 0;

        int temp = player.getBalance();
        while (player.getPosition() != 10) {
            move(player, token, 1);
        }

        //subtract go value
        if (temp != player.getBalance()) {
            player.setBalance(temp - 20);
        }

    }

    public static void getOutOfJail(Player player, ImageView token, int steps) {
        player.inJail = false;
        player.roundsInJail = 0;
        move(player, token, steps);
        //position-=1;
    }

    public static void move(Player player, ImageView token, int steps) {
        for (int i = 0; i < steps; i++) {
            player.setPosition(player.getPosition()+1);
            
            moveToken(token);
            if (player.getPosition() == 40) {
                // if he passes by the GO, add money and make position 0
                player.setPosition(player.getPosition()%40);
                //deposit(200);
                player.deposit(20);
            }
        }
    }

    public static void setTokenPosition(Player player, ImageView token) {
        for (int i = 0; i < player.getPosition(); i++) {
            moveToken(token);
        }
    }

    private static void moveToken(ImageView token) {
        int OFFSET = 0;
        int XSTEP, YSTEP;
        XSTEP = YSTEP = 65;
        int steps = 1;
        for (int i = 1; i <= steps; i++) {
            // bottom row
            if (token.getTranslateX() >= (-265 + OFFSET) && token.getTranslateY() >= 315) {
                token.setTranslateX(token.getTranslateX() - XSTEP);
            } // left column
            else if (token.getTranslateX() <= (-315 + OFFSET) && token.getTranslateY() >= -280) {
                token.setTranslateY(token.getTranslateY() - YSTEP);
            } // top row
            else if (token.getTranslateX() <= (300 + OFFSET)) {
                token.setTranslateX(token.getTranslateX() + XSTEP);
            } // right column
            else if (token.getTranslateX() >= (315 + OFFSET) && token.getTranslateY() <= 265) {
                token.setTranslateY(token.getTranslateY() + YSTEP);
            }
            // alligning piece on just visiting tile for correct movement
            if (token.getTranslateX() == (-325 + OFFSET) && (token.getTranslateY() == 370 || token.getTranslateY() == 320 || token.getTranslateY() == 350)) {
                if (token.getTranslateY() == 320) {
                    token.setTranslateX(-315 + OFFSET);
                } else if (token.getTranslateY() == 370) {
                    token.setTranslateX(-380 + OFFSET);
                } else if (token.getTranslateY() == 350) {
                    token.setTranslateX(-345 + OFFSET);
                }
                token.setTranslateY(320);
            } // alligning piece on free parking tile for correct movement
            else if ((token.getTranslateX() == (-315 + OFFSET) || token.getTranslateX() == (-380 + OFFSET) || token.getTranslateX() == (-345 + OFFSET)) && token.getTranslateY() == -330) {
                if (token.getTranslateX() == (-380 + OFFSET)) {
                    token.setTranslateY(-380);
                } else if (token.getTranslateX() == (-315 + OFFSET)) {
                    token.setTranslateY(-320);
                } else if (token.getTranslateX() == (-345 + OFFSET)) {
                    token.setTranslateY(-345);
                }
                token.setTranslateX(-320 + OFFSET);
            } // alligning piece on jail tile for correct movement
            else if (token.getTranslateX() == (330 + OFFSET) && (token.getTranslateY() == -320 || token.getTranslateY() == -380 || token.getTranslateY() == -345)) {
                if (token.getTranslateY() == -320) {
                    token.setTranslateX(315 + OFFSET);
                } else if (token.getTranslateY() == -380) {
                    token.setTranslateX(380 + OFFSET);
                } else if (token.getTranslateY() == -345) {
                    token.setTranslateX(345 + OFFSET);
                }
                token.setTranslateY(-320);
            } // alligning piece on GO tile for correct movement
            else if ((token.getTranslateX() == (315 + OFFSET) || token.getTranslateX() == (380 + OFFSET) || token.getTranslateX() == (345 + OFFSET)) && token.getTranslateY() == 330) {
                if (token.getTranslateX() == (315 + OFFSET)) {
                    token.setTranslateY(315);
                } else if (token.getTranslateX() == (380 + OFFSET)) {
                    token.setTranslateY(380);
                } else if (token.getTranslateX() == (345 + OFFSET)) {
                    token.setTranslateY(345);
                }
                token.setTranslateX(320 + OFFSET);
            }
        }
    }
}
