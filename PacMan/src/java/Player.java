
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Irindu
 */
public class Player extends Item {

    String id;
    int score;

    @Override
    public String toString() {
        JsonArrayBuilder playerBuilder = Json.createArrayBuilder();
        playerBuilder.add(this.id);
        playerBuilder.add(this.score);
        playerBuilder.add(this.xPosition);
        playerBuilder.add(this.yPosition);

        JsonArray playerJsonArray = playerBuilder.build();
        //    for();
        //   return "[" + this.id + "," + this.score + "," + this.xPosition + "," + this.yPosition + "]";
        return playerJsonArray.toString();
    }

    public JsonArray getPlayerJsonArray() {
        JsonArrayBuilder playerBuilder = Json.createArrayBuilder();
        playerBuilder.add(this.id);
        playerBuilder.add(this.score);
        playerBuilder.add(this.xPosition);
        playerBuilder.add(this.yPosition);

        JsonArray playerJsonArray = playerBuilder.build();

        return playerJsonArray;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player(String id, int score) {
        this.id = id;
        this.score = score;
        switch (id) {
            case "P1":
                this.setPosition(0, 0);
                break;
            case "P2":
                this.setPosition(44, 0);
                break;
            case "P3":
                this.setPosition(0, 44);
                break;
            case "P4":
                this.setPosition(44, 44);
                break;
        }
    }

}
