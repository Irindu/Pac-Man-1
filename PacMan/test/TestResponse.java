/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Irindu
 */

public class TestResponse {

    public static void main(String[] args) {
          GameStation gs = new GameStation();
        gs.players.add(new Player("P1", 100));
        gs.players.add(new Player("P2", 150));
        gs.players.add(new Player("P3", 300));
        gs.players.add(new Player("P4", 200)); 
        
      /*  gs.generateFood();
        gs.playersInit();
        gs.generateFood(); */
      
      Food generatedFood = new Food('R', 10, 10);
            gs.food.add(generatedFood);
            gs.board[10][10] = generatedFood;
        
        System.out.println(gs.board[10][10]);
        
        System.out.println(gs.getResponse());
        
            gs.food.remove( gs.board[10][10]);
        
        System.out.println(gs.getResponse());
        
        System.out.println(gs.board[0][0]);
   

     
}
    
   

}
