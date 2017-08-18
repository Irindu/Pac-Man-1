/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Irindu
 */
public class ResponseJUnitTest {
    
    public ResponseJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
         GameStation gs = new GameStation();
        gs.players.add(new Player("P1", 100));
        gs.players.add(new Player("P2", 150));
        gs.players.add(new Player("P3", 300));
        gs.players.add(new Player("P4", 200));

        gs.generateFood();
        
        System.out.println(gs.getResponse());
        System.out.println(gs.board[0][0]);
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
