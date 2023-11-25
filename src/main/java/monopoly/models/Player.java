/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monopoly.models;

import java.util.ArrayList;

import java.io.*;

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
