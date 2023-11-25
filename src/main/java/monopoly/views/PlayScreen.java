package monopoly.views;

import monopoly.Queue;

import monopoly.models.Player;
import monopoly.models.Property;
import monopoly.models.Tile;

import monopoly.controllers.PlayerController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import monopoly.controllers.GameController;

/**
 *
 * @author ramez
 */

public class PlayScreen {

    int OFFSET = 0;

    // global stuff
    static StackPane pane = new StackPane();

    public static Image one, two, three, four, five, six;

    public Button rollBtn, endTurnBtn, buyPropertyBtn, backButton;
    public int roll1, roll2;

    public Image[] images;
    public ImageView[] tokens;
    public ImageView tokenIcon;

    private Image board;
    private ImageView biv;

    public ImageView firstIV;
    public ImageView secondIV;

    public Label playerName, balance, propertiesOwnedText;

    private Scene scene;

    public Player currentPlayer;

    private boolean lose = false;

    public static int playersCount;

    private boolean doubleRoll = false;

    Label[] info;
    int infoIndex = 0;
    VBox rightInfoBox;


    public int rollSum[][];

    GameController myGameController;
    GameView myGameView;

    private Queue playerQueue;

    private ArrayList<Player> players;
    private ArrayList<Tile> Board;

    public PlayScreen(GameController Game, GameView GameView, ArrayList<Player> players, Queue playerQueue, ArrayList<Tile> Board) {
        myGameController = Game;
        myGameView = GameView;

        this.playerQueue = playerQueue;
        this.players = players;
        this.Board = Board;

        playersCount = playerQueue.getSize();
        rollSum = new int[playersCount][2];

        

        initialize();
        figurePlayerOrder();
    }

    public Scene getScene() {
        return scene;
    }

    private void initializeTokens() {

        // loading tokens images
        tokens = new ImageView[8];
        // Image ali = new Image("file:Images\\Players\\aa.png");
        Image ali = new Image("file:assets\\imgs\\Players\\player1.png");
        tokens[0] = new ImageView(ali);
        tokens[0].setTranslateX(325 + OFFSET);
        tokens[0].setTranslateY(320);

        // Image cat = new Image("file:Images\\Players\\cc.png");
        Image cat = new Image("file:assets\\imgs\\Players\\player2.png");
        tokens[1] = new ImageView(cat);
        tokens[1].setTranslateX(325 + OFFSET);
        tokens[1].setTranslateY(320);

        // Image koki = new Image("file:Images\\Players\\kk.png");
        Image koki = new Image("file:assets\\imgs\\Players\\player3.png");
        tokens[2] = new ImageView(koki);
        tokens[2].setTranslateX(325 + OFFSET);
        tokens[2].setTranslateY(320);

        // Image ramez = new Image("file:Images\\Players\\rr.png");
        Image ramez = new Image("file:assets\\imgs\\Players\\player4.png");
        tokens[3] = new ImageView(ramez);
        tokens[3].setTranslateX(325 + OFFSET);
        tokens[3].setTranslateY(320);

        // Image juju = new Image("file:Images\\Players\\gg.png");
        Image juju = new Image("file:assets\\imgs\\Players\\player5.png");
        tokens[4] = new ImageView(juju);
        tokens[4].setTranslateX(325 + OFFSET);
        tokens[4].setTranslateY(320);

        // Image dodo = new Image("file:Images\\Players\\naa.png");
        Image dodo = new Image("file:assets\\imgs\\Players\\player6.png");
        tokens[5] = new ImageView(dodo);
        tokens[5].setTranslateX(325 + OFFSET);
        tokens[5].setTranslateY(320);

        // Image donia = new Image("file:Images\\Players\\nmm.png");
        Image donia = new Image("file:assets\\imgs\\Players\\player7.png");
        tokens[6] = new ImageView(donia);
        tokens[6].setTranslateX(325 + OFFSET);
        tokens[6].setTranslateY(320);

        // Image solsol = new Image("file:Images\\Players\\ss.png");
        Image solsol = new Image("file:assets\\imgs\\Players\\player8.png");
        tokens[7] = new ImageView(solsol);
        tokens[7].setTranslateX(325 + OFFSET);
        tokens[7].setTranslateY(320);

        tokenIcon = new ImageView();

    }

    private void initialize() {

        initializeTokens();

        pane = new StackPane();

        // dice images

        one = new Image("file:assets\\imgs\\Dice\\One.png");

        two = new Image("file:assets\\imgs\\Dice\\Two.png");

        three = new Image("file:assets\\imgs\\Dice\\Three.png");

        four = new Image("file:assets\\imgs\\Dice\\Four.png");

        five = new Image("file:assets\\imgs\\Dice\\Five.png");

        six = new Image("file:assets\\imgs\\Dice\\Six.png");

        images = new Image[6];
        images[0] = one;
        images[1] = two;
        images[2] = three;
        images[3] = four;
        images[4] = five;
        images[5] = six;

        // board

        board = new Image("file:assets\\imgs\\Board.png");
        biv = new ImageView(board);

        biv.setFitHeight(800);
        biv.setFitWidth(800);
        // biv.setTranslateX(OFFSET);

        rollBtn = new Button("Roll");
        rollBtn.setScaleX(4);
        rollBtn.setScaleY(3);
        rollBtn.setTranslateX(OFFSET);
        // rollBtn.setBorder(Border.stroke(Paint.valueOf("GOLD")));
        // rollBtn.setStyle("-fx-background-color: #708090; -fx-text-fill: #f5f5f5");
        rollBtn.setStyle(
                "-fx-background-color: #708090;-fx-cursor: hand; -fx-background-radius: 2px; -fx-text-fill: #f5f5f5");

        // rollBtn = new Button("Roll");
        // //rollBtn.setStyle("-fx-background-color: #708090; -fx-text-fill: #f5f5f5");
        // rollBtn.setScaleX(4);
        // rollBtn.setScaleY(3);
        // rollBtn.setTranslateX(600);
        // rollBtn.setTranslateY(300);

        endTurnBtn = new Button("End Turn");
        endTurnBtn.setDisable(true);

        buyPropertyBtn = new Button("Buy Property");
        buyPropertyBtn.setDisable(true);

        HBox hb = new HBox(10);
        VBox vb = new VBox(10);

        playerName = new Label("");
        playerName.setTextFill(Paint.valueOf("WHITE"));
        playerName.setScaleX(3);
        playerName.setScaleY(3);
        playerName.setTranslateX(150);
        playerName.setTranslateY(50);

        tokenIcon.setTranslateX(200);
        tokenIcon.setTranslateY(50);

        propertiesOwnedText = new Label();
        propertiesOwnedText.setTextFill(Paint.valueOf("WHITE"));
        propertiesOwnedText.setTranslateX(200);
        propertiesOwnedText.setTranslateY(50);

        HBox h = new HBox(10);
        h.getChildren().addAll(playerName, tokenIcon, propertiesOwnedText);
        vb.getChildren().add(h);

        firstIV = new ImageView();
        secondIV = new ImageView();

        backButton = new Button("", new ImageView("file:assets\\imgs\\buttons\\back.png"));

        pane.getChildren().addAll(biv, rollBtn, firstIV, secondIV, backButton);
        backButton.setTranslateX(900);
        backButton.setTranslateY(-300);
        balance = new Label();
        balance.setTextFill(Paint.valueOf("WHITE"));
        balance.setScaleX(1.7);
        balance.setScaleY(1.7);
        balance.setTranslateX(130);
        balance.setTranslateY(90);
        vb.getChildren().add(balance);

        rightInfoBox = new VBox(10);
        HBox buttonsBox = new HBox(5);

        buttonsBox.getChildren().addAll(endTurnBtn, buyPropertyBtn);

        rightInfoBox.getChildren().add(buttonsBox);
        rightInfoBox.setTranslateX(20);
        rightInfoBox.setTranslateY(300);

        info = new Label[12];

        for (int i = 0; i < 12; i++) {
            info[i] = new Label();
            info[i].setTextFill(Paint.valueOf("WHITE"));
            rightInfoBox.getChildren().add(info[i]);

        }

        //
        hb.getChildren().addAll(pane, vb, rightInfoBox);
        hb.setStyle("-fx-background-color: #002230;");
        scene = new Scene(hb, 1150, 800);
    }

    static int i = 0;

    public void figurePlayerOrder() {

        backButton.setDisable(false);

        playerName.setText(players.get(playerQueue.front()).getName());

        balance.setText(Integer.toString(players.get(playerQueue.front()).getBalance()));

        rollBtn.setOnAction((ActionEvent event) -> {

            roll1 = PlayerController.roll();
            roll2 = PlayerController.roll();

            addInfo(playerName.getText() + " rolled " + roll1 + " & " + roll2);

            firstIV.setImage(images[roll1 - 1]);
            secondIV.setImage(images[roll2 - 1]);

            firstIV.setTranslateX(-100 + OFFSET);
            firstIV.setTranslateY(0);

            secondIV.setTranslateX(100 + OFFSET);
            secondIV.setTranslateY(0);

            firstIV.setOpacity(1);
            secondIV.setOpacity(1);

            endTurnBtn.setDisable(false);

            rollBtn.setDisable(true);
            rollBtn.setOpacity(0);

            rollSum[i][0] = roll1 + roll2;
            rollSum[i][1] = players.get(playerQueue.front()).getID();

            endTurnBtn.setDefaultButton(true);

        });

        endTurnBtn.setOnAction((ActionEvent event) -> {

            try {
                playerQueue.cycle();
                currentPlayer = players.get(playerQueue.front());
                playerName.setText(currentPlayer.getName());
                
                balance.setText(Integer.toString(currentPlayer.getBalance()));

            } catch (Exception e) {
            }

            firstIV.setOpacity(0);
            secondIV.setOpacity(0);

            rollBtn.setDisable(false);
            rollBtn.setOpacity(1);

            endTurnBtn.setDisable(true);

            i++;

            if (i >= playersCount) {
                addInfo("Players order: ");

                sortbyColumn(rollSum, 0); // sorting by what players rolled

                Queue tempQueue = new Queue(playersCount);

                for (int j = playersCount - 1; j >= 0; j--) {
                    addInfo(players.get(rollSum[j][1]).getName());
                    tempQueue.enqueue(rollSum[j][1]);
                }

                playerQueue = tempQueue;
                
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = 0; i < playersCount; i++) {

                    int random = (int) Math.random() * 8;
                    boolean error = true;

                    while (error) {
                        error = false;
                        random = (int) (Math.random() * 8);
                        System.out.println(random);
                        for (int k = 0; k < temp.size(); k++) {
                            if (random == temp.get(k)) {
                                error = true;
                                break;
                            }
                        }
                    }

                    temp.add(random);

                    players.get(playerQueue.front()).setTokenIndex(random);

                    playerQueue.cycle();

                }

                myGameController.saveGame();

                gameLoop();

            }

        });
    }

    private void gameLoop() {

        for (int i = 0; i < playersCount; i++) {
            try {
                ImageView playerToken = tokens[players.get(playerQueue.front()).getTokenIndex()];
                pane.getChildren().add(playerToken);
                PlayerController.setTokenPosition(players.get(playerQueue.front()), playerToken);

                playerQueue.cycle();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        currentPlayer = players.get(playerQueue.front());

        playerName.setText(currentPlayer.getName());
        balance.setText(Integer.toString(currentPlayer.getBalance()));
        balance.setText(balance.getText() + "\nNo of games: " + currentPlayer.getNoOfGames());
        balance.setText(balance.getText() + "\nNo of wins: " + currentPlayer.getWins());
        tokenIcon.setImage(tokens[currentPlayer.getTokenIndex()].getImage());
        propertiesOwnedText.setText(currentPlayer.getPropertiesText(Board));

        rightInfoBox.setTranslateX(-70);

        rollBtn.setOnAction((ActionEvent event) -> {
            backButton.setDisable(true);

            roll1 = PlayerController.roll();
            roll2 = PlayerController.roll();

            addInfo(playerName.getText() + " rolled " + roll1 + " & " + roll2);

            firstIV.setImage(images[roll1 - 1]);
            secondIV.setImage(images[roll2 - 1]);

            firstIV.setTranslateX(-100 + OFFSET);
            firstIV.setTranslateY(0);

            secondIV.setTranslateX(100 + OFFSET);
            secondIV.setTranslateY(0);

            firstIV.setOpacity(1);
            secondIV.setOpacity(1);

            rollBtn.setDisable(true);
            rollBtn.setOpacity(0);

            endTurnBtn.setDisable(false);

            if (!currentPlayer.inJail) {

                if (roll1 == roll2) {
                    if (++currentPlayer.doubleRollsCount == 3) {
                        currentPlayer.doubleRollsCount = 0;
                        PlayerController.goToJail(currentPlayer, tokens[currentPlayer.getTokenIndex()]);
                        addInfo(currentPlayer.getName() + " rolled doubles 3 times in a row so they're GOING TO JAIL");
                    } else {
                        addInfo(currentPlayer.getName() + " ROLLED DOUBLES (Bonus Round)");
                        doubleRoll = true;
                    }

                    // do action
                } else
                    currentPlayer.doubleRollsCount = 0;

                ImageView playerToken = tokens[currentPlayer.getTokenIndex()];

                // for (int i = 0;i<roll1+roll2;i++) {
                // start = System.currentTimeMillis();
                // end = System.currentTimeMillis();
                // while ((end - start) / 1000.0 < 0.5) {
                // System.out.println((end - start) / 1000.0);
                // end = System.currentTimeMillis();
                // }
                // System.out.println("0.5 second passed");
                PlayerController.move(currentPlayer, playerToken, roll1 + roll2);
                // Game.primaryStage.show();
                // }

                switch (Board.get(currentPlayer.getPosition()).getType()) {

                    case "property":
                        Property property = (Property) Board.get(currentPlayer.getPosition());
                        addInfo(playerName.getText() + " is standing on: " + property.getName());

                        if (property.getIsOwned()) {
                            if (players.get(property.getOwnerID()).getName().equals(currentPlayer.getName())) { // check so player doesnt pay to himself                                                                                         
                                break;
                            }
                            if (!currentPlayer.pay(property.getBaseRent(), players.get(property.getOwnerID()))) {
                                // player cant pay -> player loses
                                addInfo("Failed to pay rent, so " + currentPlayer.getName() + " LOSES");
                                lose = true;
                            } else {
                                addInfo(playerName.getText() + " paid " + property.getBaseRent() + " rent to "
                                        + players.get(property.getOwnerID()).getName());

                            }
                        } else {

                            // player may buy property if it is not owned
                            buyPropertyBtn.setDisable(false);
                        }
                        break;

                    case "income tax":
                        if (!currentPlayer.pay(200)) {
                            // player cant pay -> player loses
                            addInfo("Failed to pay. so " + currentPlayer.getName() + " LOSES");
                            lose = true;
                        } else {
                            addInfo(playerName.getText() + " paid 200 income tax to Bank");

                        }
                        break;

                    case "luxury tax":
                        if (!currentPlayer.pay(220)) {
                            addInfo("Failed to pay 220, so " + currentPlayer.getName() + " LOSES");
                            lose = true;
                        } else {
                            addInfo(playerName.getText() + " paid 220 luxury tax to Bank");
                        }
                        break;

                    case "go to jail":
                        addInfo(currentPlayer.getName() + " IS GOING TO JAIL");
                        PlayerController.goToJail(currentPlayer, tokens[currentPlayer.getTokenIndex()]);
                        break;
                    case "jail":
                        addInfo(playerName.getText() + " is just visiting Jail");
                        break;
                    default:
                        addInfo(playerName.getText() + " is standing on: "
                                + Board.get(currentPlayer.getPosition()).getType());
                        break;
                }

            } else {
                // player in jail action:
                if (roll1 == roll2) {
                    addInfo("DOUBLE ROLL, SO " + currentPlayer.getName() + " IS OUT OF JAIL");
                    PlayerController.getOutOfJail(currentPlayer, tokens[currentPlayer.getTokenIndex()], roll1 + roll2);
                    return;
                } else if (currentPlayer.roundsInJail < 3) {
                    buyPropertyBtn.setText("Pay 50 to break free");
                    buyPropertyBtn.setDisable(false);
                    addInfo(playerName.getText() + " IS IMPRISONED IN JAIL");

                    currentPlayer.roundsInJail++;
                    addInfo(currentPlayer.getName() + " rounds in Jail: " + currentPlayer.roundsInJail);
                } else {
                    addInfo(currentPlayer.getName() + " rounds in Jail: " + currentPlayer.roundsInJail + 1);

                    if (!currentPlayer.pay(50)) {
                        lose = true;
                        addInfo(currentPlayer.getName() + " failed to pay, so LOSES");
                    } else {
                        addInfo(currentPlayer.getName() + " PAID 50 AND GOT OUT OF JAIL");
                        PlayerController.getOutOfJail(currentPlayer, tokens[currentPlayer.getTokenIndex()],
                                roll1 + roll2);

                    }
                }

            }
            balance.setText(Integer.toString(currentPlayer.getBalance()));
            balance.setText(balance.getText() + "\nNo of games: " + currentPlayer.getNoOfGames());
            balance.setText(balance.getText() + "\nNo of wins: " + currentPlayer.getWins());
            if (lose) {

                // actions if player loses

                currentPlayer.loser(Board, players);
                playerQueue.dequeue();
                playersCount--;
                lose = false;

                tokens[currentPlayer.getTokenIndex()].setOpacity(0.2);

                endTurnBtn.setDisable(false);

                rollBtn.setDisable(true);
                rollBtn.setOpacity(0);

                if (playersCount == 1) {

                    currentPlayer = players.get(playerQueue.front());
                    playerName.setText(currentPlayer.getName());
                    balance.setText(Integer.toString(currentPlayer.getBalance()));
                    balance.setText(balance.getText() + "\nNo of games: " + currentPlayer.getNoOfGames());
                    balance.setText(balance.getText() + "\nNo of wins: " + currentPlayer.getWins());

                    addInfo(currentPlayer.getName() + " IS THE WINNER!!!!");
                    currentPlayer.winner();
                    rollBtn.setDisable(true);
                    rollBtn.setOpacity(0);
                    endTurnBtn.setDisable(true);
                    buyPropertyBtn.setDisable(true);
                    balance.setText(Integer.toString(currentPlayer.getBalance()));
                    balance.setText(balance.getText() + "\nNo of games: " + currentPlayer.getNoOfGames());
                    balance.setText(balance.getText() + "\nNo of wins: " + currentPlayer.getWins());
                    myGameController.saveGame();
                    return;

                }

                if (roll1 == roll2) {
                    rollBtn.setDisable(false);
                    rollBtn.setOpacity(1);
                    buyPropertyBtn.setDisable(true);
                    endTurnBtn.setDisable(false);
                    // do action
                }

            }

        });

        endTurnBtn.setOnAction((ActionEvent event) -> {
            backButton.setDisable(false);

            buyPropertyBtn.setText("Buy Property");

            if (!doubleRoll) { // extra round if double roll
                playerQueue.cycle();

                currentPlayer = players.get(playerQueue.front());

                playerName.setText(currentPlayer.getName());

                balance.setText(Integer.toString(currentPlayer.getBalance()));
                balance.setText(balance.getText() + "\nNo of games: " + currentPlayer.getNoOfGames());
                balance.setText(balance.getText() + "\nNo of wins: " + currentPlayer.getWins());
                tokenIcon.setImage(tokens[currentPlayer.getTokenIndex()].getImage());
            } else {
                doubleRoll = false;
            }

            firstIV.setOpacity(0);
            secondIV.setOpacity(0);

            rollBtn.setDisable(false);
            rollBtn.setOpacity(1);

            endTurnBtn.setDisable(true);
            buyPropertyBtn.setDisable(true);

            propertiesOwnedText.setText(currentPlayer.getPropertiesText(Board));

        });

        buyPropertyBtn.setOnAction((ActionEvent event) -> {

            if (currentPlayer.inJail) {
                if (!currentPlayer.pay(50)) {
                    lose = true;
                    addInfo(currentPlayer.getName() + " failed to pay, so LOSES");
                } else {
                    addInfo(currentPlayer.getName() + " PAID 50 AND GOT OUT OF JAIL");
                    PlayerController.getOutOfJail(currentPlayer, tokens[currentPlayer.getTokenIndex()], roll1 + roll2);
                    buyPropertyBtn.setText("Buy Property");
                    buyPropertyBtn.setDisable(true);
                }
                return;
            }

            Property property = (Property) Board.get(currentPlayer.getPosition());

            if (!currentPlayer.buyProperty(property)) {
                addInfo(playerName.getText() + " couldn't buy property, not enough money! Price was "
                        + property.getPrice());

            } else {
                addInfo(playerName.getText() + " paid " + property.getPrice() + " & bought " + property.getName() + " ("
                        + property.getColor() + ")");
                if (property.isFullSet(Board, players)) {
                    addInfo(playerName.getText() + " now owns this full set of color " + property.getColor());
                }
            }

            buyPropertyBtn.setDisable(true);
            balance.setText(Integer.toString(currentPlayer.getBalance()));
            balance.setText(balance.getText() + "\nNo of games: " + currentPlayer.getNoOfGames());
            balance.setText(balance.getText() + "\nNo of wins: " + currentPlayer.getWins());
        });

    }

    // public void loadGame() {

    //     try {
    //         Game.resumeGame();

    //         Game.primaryStage.setOnCloseRequest(event -> {
    //             myGameController.saveGame();
    //             Game.primaryStage.close();
    //         });
    //         playersCount = playerQueue.getSize();
    //         if (players.isEmpty()) {
    //             Exception e = new Exception();
    //             throw e;
    //         }
    //         boolean error = true;

    //         for (int i = 0; i < playersCount; i++) {
    //             if (players.get(playerQueue.front()).getBalance() != Player.startingBalance
    //                     || players.get(playerQueue.getSize()).getPosition() != 0) {
    //                 error = false;
    //                 break;
    //             }
    //         }
    //         if (error) {
    //             Exception e = new Exception();
    //             throw e;
    //         }

    //     } catch (Exception e) {
    //         Game.menuScreen.alert
    //                 .setContentText("No Game Found // Error loading previous game " + "\nChoose new game!");
    //         Game.menuScreen.alert.show();
    //         return;
    //     }

    //     if (playersCount <= 1) {
    //         Game.menuScreen.alert.setContentText("Last game is over!! Choose new Game");
    //         Game.menuScreen.alert.show();
    //     } else {
    //         myGameController.changeScreen("PlayScreen");

    //         gameLoop();
    //     }
    // }

    private static void sortbyColumn(int arr[][], int col) {
        // sort

        // Using built-in sort function Arrays.sort
        Arrays.sort(arr, new Comparator<int[]>() {

            @Override
            // Compare values according to columns
            public int compare(final int[] entry1,
                    final int[] entry2) {

                // To sort in descending order revert
                // the '>' Operator
                if (entry1[col] > entry2[col])
                    return 1;
                else
                    return -1;
            }
        }); // End of function call sort().

    }

    public void addInfo(String text) {

        if (infoIndex < 12) {
            info[infoIndex++].setText(text);
        } else {
            for (int i = 0; i < 11; i++) {
                info[i].setText(info[i + 1].getText());
            }
            info[11].setText(text);
            infoIndex = 12;
        }
    }

}
