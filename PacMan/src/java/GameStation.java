
import java.util.ArrayList;
import java.util.Random;
import javax.json.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Irindu
 */
public class GameStation {

    static int GRIDSIZE = 45;

    // enum gameState = {"Ready"=2,"Play"=1;,"Stopped"};
    enum GameState {
        READY, PLAY
    }
    Item[][] board;
    ArrayList<Player> players;
    ArrayList<Food> food;
    GameState currentState = GameState.READY;
    short numberOfPlayers;

    String initPlayer() {
        if (numberOfPlayers == 3) {
            currentState = GameState.PLAY;
        }
        return players.get(numberOfPlayers++).id;
    }

    void updateGame(String player, int key) {
        //find the player
        System.out.println("player:" + player);
        Player thePlayer = players.get(0);
        switch (player) {
            case "P1":
                thePlayer = players.get(0);
                break;
            case "P2":
                thePlayer = players.get(1);
                break;
            case "P3":
                thePlayer = players.get(2);
                break;
            case "P4":
                thePlayer = players.get(3);
                break;
        }

        //find current position
        int xPosition;
        int yPosition;

        xPosition = thePlayer.xPosition;
        yPosition = thePlayer.yPosition;

        //take the player off the board
        this.board[xPosition][yPosition] = null;

        //find position to move
        switch (key) {
            case 37:
                xPosition = ((xPosition - 1) < 0) ? GRIDSIZE - 1 : (xPosition - 1);
                break;
            case 38: //moving up
                yPosition = ((yPosition - 1) < 0) ? GRIDSIZE - 1 : (yPosition - 1);

                break;
            case 39:
                xPosition = Math.abs((xPosition + 1) % GRIDSIZE);
                break;
            case 40://moving down
                yPosition = Math.abs((yPosition + 1) % GRIDSIZE);
                break;
        }

        //update the board
        //the item in the next  position to go
        Item theItem = board[xPosition][yPosition];
        //check weather the new position contains any players
        try {
            Player anotherPlayer = (Player) theItem;
            //then there is a player there
            if (this.players.contains(anotherPlayer)) {
                System.out.println("PLAYER");
                thePlayer.score -= 3;
                ((Player) theItem).score -= 3;

                //reset the players
                resetPlayer(thePlayer);
                resetPlayer(anotherPlayer);
            } else {
                //the next place to go is empty
                System.out.println("EMPTY POSITION");
                thePlayer.setPosition(xPosition, yPosition);
                this.board[xPosition][yPosition] = thePlayer;

            }

        } catch (ClassCastException e) {
            //then there is a food item there
            System.out.println("FOOD ITEM");
            Food thefood = (Food) theItem;
            int pointsToAdd = 0;
            switch (thefood.colour) {
                case 'R':
                    pointsToAdd = 1;
                    break;
                case 'G':
                    pointsToAdd = 2;
                    break;
                case 'B':
                    pointsToAdd = 3;
                    break;
            }
            thePlayer.score += pointsToAdd;
            thePlayer.setPosition(xPosition, yPosition);
            this.board[xPosition][yPosition] = thePlayer;
            this.food.remove(thefood);
        }

        /* if () {
        } else if (this.food.contains((Food)theItem)) {
           
        } else {
           
        } */
        //finally
    }

    void resetPlayer(Player player) {
        this.board[player.xPosition][player.yPosition] = null;
        switch (player.id) {
            case "P1":
                player.setPosition(0, 0);
                break;
            case "P2":
                player.setPosition(GRIDSIZE - 1, 0);
                break;
            case "P3":
                player.setPosition(0, GRIDSIZE - 1);
                break;
            case "P4":
                player.setPosition(GRIDSIZE - 1, GRIDSIZE - 1);
                break;
        }
        this.board[player.xPosition][player.yPosition] = player;
    }

    public GameStation() {
        board = new Item[GRIDSIZE][GRIDSIZE];
        players = new ArrayList<Player>();
        food = new ArrayList<Food>();
        currentState = GameState.READY;
        numberOfPlayers = 0;
        this.playersInit();
        this.generateFood();

    }

    void playersInit() {
        Player p1 = new Player("P1", 0);
        this.players.add(p1);
        Player p2 = new Player("P2", 0);
        this.players.add(p2);
        Player p3 = new Player("P3", 0);
        this.players.add(p3);
        Player p4 = new Player("P4", 0);
        this.players.add(p4);

        board[p1.xPosition][p1.yPosition] = p1;

        board[p2.xPosition][p2.yPosition] = p2;

        board[p3.xPosition][p3.yPosition] = p3;

        board[p4.xPosition][p4.yPosition] = p4;
    }

    void generateFood() {
        for (int i = 0; i < 30; i++) {
            Random rand = new Random();
            int randomColourNumber = rand.nextInt(3) + 1;
            char randomColour = 'G';
            switch (randomColourNumber) {
                case 1:
                    randomColour = 'R';
                    break;
                case 2:
                    randomColour = 'G';
                    break;
                case 3:
                    randomColour = 'B';
                    break;
            }
            int randomX = rand.nextInt(GRIDSIZE);
            int randomY = rand.nextInt(GRIDSIZE);
            Food generatedFood = new Food(randomColour, randomX, randomY);
            this.food.add(generatedFood);
            board[randomX][randomY] = generatedFood;
        }
    }

    JsonObject getResponse() {
        JsonArrayBuilder playersBuilder = Json.createArrayBuilder();

        JsonArrayBuilder dotsBuilder = Json.createArrayBuilder();

        for (Player player : players) {
            playersBuilder.add(player.getPlayerJsonArray());
        }

        for (Food dot : food) {
            dotsBuilder.add(dot.getFoodJsonArray());
        }

        JsonObjectBuilder responseBuilder = Json.createObjectBuilder();

        responseBuilder.add("DOTS", dotsBuilder);
        responseBuilder.add("PLAYERS", playersBuilder);

        JsonObject responseJsonObject = responseBuilder.build();

        //System.out.println("Employee JSON String\n" + responseJsonObject);
        return responseJsonObject;
    }
}
