/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Irindu
 */
public class Item {
    
    int xPosition;
    int yPosition;
    
    final void setPosition(int x,int y){//cannot be overriden
        this.xPosition = x;
        this.yPosition = y;
    }
    
    @Override
    public String toString(){
        return "["+this.xPosition+","+this.yPosition+"]";
    }
    
}

