
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
public class Food extends Item {

    char colour;

    public Food(char colour, int x, int y) {
        this.colour = colour;
        this.setPosition(x, y);
    }

    @Override
    public String toString() {
        return "[" + this.colour + "," + +this.xPosition + "," + this.yPosition + "]";

    }
    
    public JsonArray getFoodJsonArray() {
        JsonArrayBuilder playerBuilder = Json.createArrayBuilder();
        playerBuilder.add(this.colour+"");
        playerBuilder.add(this.xPosition);
        playerBuilder.add(this.yPosition);

        JsonArray playerJsonArray = playerBuilder.build();

        return playerJsonArray;
    }

}
