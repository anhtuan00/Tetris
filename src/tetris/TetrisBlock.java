/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;

/**
 *
 * @author Admin
 */
public class TetrisBlock {
    
    private int [][] shape;
    private Color color;
    private int x,y;
    
    public TetrisBlock(int[][] shape, Color color ){
        this.shape = shape;
        this.color = color;
        
        
    }
    
    public void spawn(int gridWidth){
        y= -getHeight();
        x = (gridWidth - getWidth()) / 2;
    }

    /**
     * @return the shape
     */
    public int[][] getShape() {
        return shape;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    
    public int getHeight(){
        return shape.length;
    }
    
    public int getWidth(){
        return shape[0].length; 
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
    public void moveDown(){
        y++;
    }
    
    public void moveLeft(){
        x--;
    }
    
    public void moveRight(){
        x++;
    }
    
    public int getBottomEdge(){
        return y + getHeight();
    }
    
}
