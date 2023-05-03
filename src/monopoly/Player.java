/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monopoly;

import java.io.*;
import java.util.*;
import javafx.scene.image.ImageView;

/**
 *
 * @author ramez
 */
public class Player implements Serializable {

    final static public int startingBalance = 200;

    private int balance = startingBalance;

    private int id;
    private int position = 0;
    private int noOfGames = 0;
    private int wins = 0;
    private int tokenIndex;

    public int doubleRollsCount = 0;
    private ArrayList<Integer> propertiesOwnedIndex = new ArrayList<>();

    public boolean inJail = false;
    public int roundsInJail = 0;

    private String name;

    public Player() {

    }

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setTokenIndex(int tokenIndex) {
        this.tokenIndex = tokenIndex;
    }

    public int getTokenIndex() {
        return tokenIndex;
    }

    public int getID() {
        return id;
    }

    public String getPropertiesText(ArrayList<Tile> Board) {
        String text = "Properties Owned: \n";

        for (int i = 0; i < propertiesOwnedIndex.size(); i++) {
            text += Board.get(propertiesOwnedIndex.get(i)).getName() + "\n";
        }
        return text;
    }

    public static int roll() {
        // dice roll
        return (int) (Math.random() * 6 + 1);

    }

    public static int isNewPlayer(ArrayList<Player> player, String name) {
        // check if this player is a new player or not

        for (int i = 0; i < player.size(); i++) {
            if (player.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void goToJail(ImageView token) {
        inJail = true;
        roundsInJail = 0;

        int temp = balance;
        while (position != 10) {
            move(token, 1);
        }

        //subtract go value
        if (temp != balance) {
            balance -= 20;
        }

    }

    public void getOutOfJail(ImageView token, int steps) {
        inJail = false;
        roundsInJail = 0;
        move(token, steps);
        //position-=1;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        //return total balance
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public Boolean pay(int amount) {
        if (balance > amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public Boolean pay(int amount, Player otherplayer) {

        if (balance > amount) {
            balance -= amount;
            otherplayer.deposit(amount);
            return true;
        }
        return false;
    }

    public Boolean buyProperty(Property property) {
        if (pay(property.getPrice())) {
            property.setIsOwned(true, id);
            propertiesOwnedIndex.add(position);
            return true;
        }
        return false;
    }

    public void setPosition(int position) {
        //set position
        this.position = position;
    }

    public int getPosition() {
        //return current position
        return position % 40;
    }

    public void setTokenPosition(ImageView token) {
        for (int i = 0; i < position; i++) {
            moveToken(token);
        }
    }

    public void move(ImageView token, int steps) {
        for (int i = 0; i < steps; i++) {
            position++;
            
            moveToken(token);
            if (position == 40) {
                // if he passes by the GO, add money and make position 0
                position %= 40;
                //deposit(200);
                deposit(20);
            }
        }
    }

    private void moveToken(ImageView token) {
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

    public int getNoOfGames() {
        // return number of games the player has played
        return noOfGames;
    }

    public void setName(String name) {
        // set player's name
        this.name = name;
    }

    public String getName() {
        // return player's name
        return name;
    }

    public void winner() {
        //increment number of wins if players wins
        wins++;
        noOfGames++;
    }

    public int getWins() {
        // return number of wins of player
        return wins;
    }

    public void startNewGame() {
        //resets needed attributes to align with the beggining of a new game
        position = 0;
        balance = startingBalance;
        inJail = false;
        roundsInJail = 0;
        propertiesOwnedIndex.clear();
        doubleRollsCount = 0;
    }

    public void loser(ArrayList<Tile> Board, ArrayList<Player> player) {

        //increment number of losses
        noOfGames++;

        //resetting all properties owned by currentPlayer who just lost
        for (int i = 0; i < Board.size(); i++) {
            if (Board.get(i).getType().equals("property")) {
                Property property = (Property) Board.get(i);
                if (property.getIsOwned()) {
                    if (player.get(property.getOwnerID()).getName().equals(name)) {
                        property.setIsOwned(false, -1);
                        property.isFullSet(Board, player);
                    }
                }
            }
        }
    }

}
