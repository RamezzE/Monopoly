package monopoly.models;

import monopoly.Queue;
import monopoly.controllers.PropertyController;

import monopoly.models.Tile;

import java.util.ArrayList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author ramez
 */

public class Game {

    private final String lastPlayerQueueFile = "assets\\files\\playerQueue.txt";
    private final String defaultBoardFile = "assets\\files\\defaultBoardData.ser";
    private final String playerDataFile = "assets\\files\\playerData.ser";
    private final String lastGameFile = "assets\\files\\lastBoardData.ser";

    private Queue playersQueue;

    private ArrayList<Tile> Board = new ArrayList();
    private ArrayList<Player> player = new ArrayList();
    // public static ImageView go = new ImageView(new Image("file:Images\\go.png"));

    private int playersNum;

    public Game() {
        reloadPlayerData();
    }

    public void setPlayersNum(int playersNum) {
        this.playersNum = playersNum;
    }

    public int getPlayersNum() {
        return playersNum;
    }

    public Queue getPlayersQueue() {
        Queue playerQueue = new Queue(playersNum);
        for (int i = 0; i < playersNum; i++) {
            playerQueue.enqueue(i);
        }
        return playerQueue;
    }

    public ArrayList<Tile> getBoard() {
        return Board;
    }

    public ArrayList<Player> getPlayers() {
        return player;
    }

    public void initializeVariables() {

        // back = new Button[6];
        // for (short i = 0; i < 6; i++) {
        //     back[i] = new Button("", new ImageView("file:assets\\imgs\\buttons\\back.png"));
        //     back[i].setStyle(
        //             "-fx-background-color: transparent;-fx-cursor: hand; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
        // }

        // go = new Button[2];
        // for (int i = 0; i < 2; i++) {
        //     go[i] = new Button("", new ImageView("file:assets\\imgs\\buttons\\go.png"));
        // }

        // for (int i = 0; i < 6; i++) {
        //     logo[i] = new ImageView(new Image("file:assets\\imgs\\logo.png"));
        // }

        // // back buttons actions
        // back[0].setOnAction((ActionEvent event) -> {
        //     primaryStage.setScene(welcomeScreen.getScene());
        // });

        // back[1].setOnAction((ActionEvent event) -> {
        //     primaryStage.setScene(menuScreen.getScene());
        // });

        // back[2].setOnAction((ActionEvent event) -> {
        //     primaryStage.setScene(newGameScreen.getScene());
        // });
        // back[3].setDefaultButton(false);
        // back[3].setOnAction((ActionEvent event) -> {
        //     saveGame();
        //     playScreen = new PlayScreen();
        //     primaryStage.setScene(menuScreen.getScene());
        // });

    }

    public void saveGame() {
        // save current game data

        // saving current Queue
        // saveCurrentPlayerQueue();

        // saving player ArrayList and Board ArrayList currentStates
        saveCurrentBoardData();
        savePlayerData();

        System.out.println("SAVED");
    }

    private void saveCurrentBoardData() {

        // save current Board ArrayList data of current Game
        try {
            FileOutputStream writeData = new FileOutputStream(lastGameFile);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(Board);
            // writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            System.out.println("Error saving Board data");
            // e.printStackTrace();
        }
    }

    private void savePlayerData() {

        // save general Players data
        try {
            FileOutputStream writeData = new FileOutputStream(playerDataFile);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(player);
            // writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            System.out.println("Error saving player data");
            e.printStackTrace();
        }
    }

    // private void saveCurrentPlayerQueue() {

    //     // saves Queue of current Game
    //     String data = "";
    //     for (int i = 0; i < playersCount; i++) {
    //         data += playerQueue.front() + " ";
    //         PlayScreen.playerQueue.cycle();
    //     }

    //     try {
    //         writeToFile(lastPlayerQueueFile, data);
    //     } catch (Exception e) {
    //         System.out.println("Error saving player Queue");
    //     }
    // }

    // public Boolean resumeGame()  {
    //     // reloads last game's data

    //     // getting last game's Queue
    //     try {
    //         // reloadPlayerQueue();

    //         // getting last Game's Board data
    //         reloadBoardData();
    //         return true;
    //     } catch (Exception e) {
    //         System.out.println("Error resuming game");
    //         e.printStackTrace();
    //         return false;
    //         // throw e;
    //     }
    // }

    // private void reloadPlayerQueue() throws Exception {

    //     Scanner myReader = null;
    //     PlayScreen.playerQueue = new Queue(8);
    //     try {
    //         File F = new File(lastPlayerQueueFile);
    //         myReader = new Scanner(F);

    //         while (myReader.hasNext()) {

    //             PlayScreen.playerQueue.enqueue(Integer.parseInt(myReader.next()));
    //         }
    //         System.out.println("reloaded playerQueue");
    //     } catch (Exception e) {
    //         System.out.println("An error occurred loading last game's Queue.");

    //         // e.printStackTrace();
    //         throw e;
    //     } finally {
    //         myReader.close();
    //     }
    // }

    private void reloadBoardData() throws Exception {

        // getting last Game's Board data
        try {
            FileInputStream readData = new FileInputStream(lastGameFile);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            Board = (ArrayList<Tile>) readStream.readObject();

            readStream.close();
            System.out.println("reloaded last game's Board Data");
        } catch (Exception e) {
            System.out.println("error reloading last Game's Board data");
            // e.printStackTrace();
            throw e;
        }
    }

    public void reloadPlayerData() {

        try {
            // reloading playerData if present
            FileInputStream readData = new FileInputStream(playerDataFile);
            ObjectInputStream readStream = new ObjectInputStream(readData);

            player = (ArrayList<Player>) readStream.readObject();
            readStream.close();
            System.out.println("reloaded player data");

        } catch (Exception e) {
            System.out.println("Error loading Player data // or no data was found");
            // e.printStackTrace();
        }
    }

    public static void writeToFile(String filename, String data) throws IOException {

        // write data to file
        FileWriter FR = null;
        try {
            FR = new FileWriter(filename);
            FR.write(data);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            FR.close();
        }
    }

    public void loadBoardData() {

        if (readDefaultBoardData())
            return;

        Board.add(0, new OtherTiles("go"));

        Board.add(1, new Property(1, "Mediterranean Avenue", "Brown", 60, 2, 10, 30, 90, 160, 250, 50, 30, 2));

        Board.add(2, new OtherTiles("chest"));

        Board.add(3, new Property(3, "Baltic Avenue", "Brown", 60, 4, 20, 60, 180, 320, 450, 50, 30, 2));

        Board.add(4, new OtherTiles("income tax")); // pays 200

        Board.add(5, new OtherTiles("railroads"));

        Board.add(6, new Property(6, "Oriental Avenue", "Light blue", 100, 6, 30, 90, 270, 400, 550, 50, 50, 3));

        Board.add(7, new OtherTiles("chance"));

        Board.add(8, new Property(8, "Vermont Avenue", "Light blue", 100, 6, 30, 90, 270, 400, 550, 50, 50, 3));

        Board.add(9, new Property(9, "Connecticut Avenue", "Light blue", 120, 8, 40, 100, 300, 450, 600, 50, 60, 3));

        Board.add(10, new OtherTiles("jail"));

        Board.add(11, new Property(11, "St. Charles Place", "Pink", 140, 10, 50, 150, 450, 625, 750, 100, 70, 3));

        Board.add(12, new OtherTiles("utilities"));

        Board.add(13, new Property(13, "States Avenue", "Pink", 140, 10, 50, 150, 450, 625, 750, 100, 70, 3));

        Board.add(14, new Property(14, "Virginia Avenue", "Pink", 160, 12, 60, 180, 500, 700, 900, 100, 80, 3));

        Board.add(15, new OtherTiles("railroads"));

        Board.add(16, new Property(16, "St. James Place", "Orange", 180, 14, 70, 200, 550, 750, 950, 100, 90, 3));

        Board.add(17, new OtherTiles("chest"));

        Board.add(18, new Property(18, "Tennessee Avenue", "Orange", 180, 14, 70, 200, 550, 750, 950, 100, 90, 3));

        Board.add(19, new Property(19, "New York Avenue", "Orange", 200, 16, 80, 220, 600, 800, 1000, 100, 100, 3));

        Board.add(20, new OtherTiles("parking"));

        Board.add(21, new Property(21, "Kentucky Avenue", "Red", 220, 18, 90, 250, 700, 875, 1050, 150, 110, 3));

        Board.add(22, new OtherTiles("chance"));

        Board.add(23, new Property(23, "IndianaAvenue", "Red", 220, 18, 90, 250, 700, 875, 1050, 150, 110, 3));

        Board.add(24, new Property(24, "Illinois Avenue", "Red", 240, 20, 100, 300, 750, 925, 1100, 150, 120, 3));

        Board.add(25, new OtherTiles("railroads"));

        Board.add(26, new Property(26, "Atlantic Avenue", "Yellow", 260, 22, 110, 330, 800, 975, 1150, 150, 130, 3));

        Board.add(27, new Property(27, "Ventnor Avenue", "Yellow", 260, 22, 110, 330, 800, 975, 1150, 150, 130, 3));

        Board.add(28, new OtherTiles("utilities"));

        Board.add(29, new Property(29, "Marvin Gardens", "Yellow", 280, 24, 120, 360, 850, 1025, 1200, 150, 140, 3));

        Board.add(30, new OtherTiles("go to jail"));

        Board.add(31, new Property(31, "Pacific Avenue", "Green", 300, 26, 130, 390, 900, 1100, 1275, 200, 150, 3));

        Board.add(32,
                new Property(32, "North Carolina Avenue", "Green", 300, 26, 130, 390, 900, 1100, 1275, 200, 150, 3));

        Board.add(33, new OtherTiles("chest"));

        Board.add(34,
                new Property(34, "Pennsylvania Avenue", "Green", 320, 28, 150, 450, 1000, 1200, 1400, 200, 160, 3));

        Board.add(35, new OtherTiles("railroads"));

        Board.add(36, new OtherTiles("chance"));

        Board.add(37, new Property(37, "Park Place", "Dark blue", 350, 35, 175, 500, 1100, 1300, 1500, 200, 175, 2));

        Board.add(38, new OtherTiles("luxury tax"));

        Board.add(39, new Property(39, "Boardwalk", "Dark blue", 400, 50, 200, 600, 1400, 1700, 2000, 200, 200, 2));

        // linking sets to each other
        PropertyController.linkProperties(Board, 1, 3);

        PropertyController.linkProperties(Board, 6, 8, 9);

        PropertyController.linkProperties(Board, 11, 13, 14);

        PropertyController.linkProperties(Board, 16, 18, 19);

        PropertyController.linkProperties(Board, 21, 23, 24);

        PropertyController.linkProperties(Board, 26, 27, 29);

        PropertyController.linkProperties(Board, 31, 32, 34);

        PropertyController.linkProperties(Board, 37, 39);

        saveDefaultBoard();
    }

    private Boolean saveDefaultBoard() {
        try {
            FileOutputStream writeData = new FileOutputStream(defaultBoardFile);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(Board);
            writeStream.flush();
            writeStream.close();
            return true;

        } catch (IOException e) {
            System.out.println("Error writing defaultBoardData to file");
            e.printStackTrace();
            return false;
        }
    }

    private Boolean readDefaultBoardData() {
        try {
            FileInputStream readData = new FileInputStream(defaultBoardFile);
            try (ObjectInputStream readStream = new ObjectInputStream(readData)) {
                Board = (ArrayList<Tile>) readStream.readObject();
            }
            System.out.println("Successfully loaded default Board Data");
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading the default Board");
            e.printStackTrace();
            return false;
        }
    }

}